package com.chuross.weathernews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.*;
import com.activeandroid.query.Select;
import com.chuross.common.android.library.ui.adapter.AbstractArrayAdapter;
import com.chuross.weathernews.R;
import com.chuross.weathernews.db.Location;
import roboguice.inject.InjectView;

import java.util.List;

public class LocationListActivity extends Activity {

    @InjectView(R.id.list)
    private ListView listView;
    @InjectView(R.id.location_add_button)
    private Button locationAddButton;
    private ArrayAdapter<Location> adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);
        List<Location> locations = new Select().from(Location.class).execute();
        adapter = new AbstractArrayAdapter<Location>(getApplicationContext(), locations) {
            @Override
            protected int getResourceId() {
                return R.layout.adapter_location_list;
            }

            @Override
            protected void setItemView(final int position, final Location location, final ViewParent parent, final View view) {
                ((TextView) view.findViewById(R.id.name)).setText(location.getName());
                view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(final View v) {
                        onDeleteButtonClick(location);
                    }
                });
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                ((CheckedTextView) view).toggle();
            }
        });
        locationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(LocationListActivity.this, LocationAddActivity.class));
            }
        });
    }

    private void onDeleteButtonClick(Location location) {
        if(location == null) {
            return;
        }
        location.delete();
        adapter.remove(location);
    }
}
