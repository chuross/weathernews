package com.chuross.weathernews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.chuross.weathernews.R;
import com.chuross.weathernews.ui.adapter.SimpleListAdapter;
import roboguice.inject.InjectView;

public class LocationAddActivity extends Activity {

    private static final int ACTION_CHOOSE_AREA = 0;
    private static final int ACTION_GPS = 1;
    @InjectView(R.id.list)
    private ListView listView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_add);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        final String[] menu = getResources().getStringArray(R.array.location_add_menu);
        listView.setAdapter(new SimpleListAdapter<String>(getApplicationContext(), menu) {
            @Override
            protected String getString(final String item) {
                return item;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                onAddActionItemClicked(position);
            }
        });
    }

    private void onAddActionItemClicked(int position) {
        if(position == ACTION_CHOOSE_AREA) {
            startActivity(new Intent(this, PrefectureChooseActivity.class));
        } else if(position == ACTION_GPS) {
            startActivity(new Intent(this, GpsActivity.class));
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
