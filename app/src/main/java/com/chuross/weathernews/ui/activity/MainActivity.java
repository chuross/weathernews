package com.chuross.weathernews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.activeandroid.query.Select;
import com.chuross.weathernews.R;
import com.chuross.weathernews.db.Location;
import com.chuross.weathernews.ui.adapter.TitleFragmentPagerAdapter;
import com.chuross.weathernews.ui.fragment.ForecastFragment;
import com.viewpagerindicator.TitlePageIndicator;
import roboguice.inject.InjectView;

import java.util.List;

public class MainActivity extends Activity {

    @InjectView(R.id.titlepage_indicator)
    private TitlePageIndicator titlePageIndicator;
    @InjectView(R.id.viewpager)
    private ViewPager viewPager;
    private TitleFragmentPagerAdapter adapter;
    @InjectView(R.id.empty)
    private View empty;
    @InjectView(R.id.location_add_button)
    private Button locationAddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(5);
        titlePageIndicator.setViewPager(viewPager);
        locationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(MainActivity.this, LocationAddActivity.class));
            }
        });
        if(!new Select().from(Location.class).exists()) {
            startActivity(new Intent(this, LocationAddActivity.class));
            return;
        }
        empty.setVisibility(View.INVISIBLE);
        refresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
        if(!new Select().from(Location.class).exists()) {
            empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if(item.getItemId() == R.id.menu_list) {
            startActivity(new Intent(this, LocationListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void refresh() {
        adapter.clear();
        titlePageIndicator.notifyDataSetChanged();
        List<Location> locations = new Select().from(Location.class).execute();
        for(Location location : locations) {
            adapter.put(location.getName(), ForecastFragment.create(location.getId()));
        }
    }
}
