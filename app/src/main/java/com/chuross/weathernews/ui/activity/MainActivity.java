package com.chuross.weathernews.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.activeandroid.query.Select;
import com.chuross.weathernews.R;
import com.chuross.weathernews.db.Location;
import com.chuross.weathernews.ui.ForecastFragment;
import com.chuross.weathernews.ui.adapter.TitleFragmentPagerAdapter;
import com.viewpagerindicator.TitlePageIndicator;
import roboguice.inject.InjectView;

import java.util.List;

public class MainActivity extends Activity {

    @InjectView(R.id.titlepage_indicator)
    private TitlePageIndicator titlePageIndicator;
    @InjectView(R.id.viewpager)
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Location> locations = new Select().from(Location.class).execute();
        if(locations.isEmpty()) {
            startActivity(new Intent(this, LocationAddActivity.class));
            return;
        }
        TitleFragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager());
        for(Location location : locations) {
            adapter.put(location.getName(), ForecastFragment.create(location.getId()));
        }
        viewPager.setAdapter(adapter);
        titlePageIndicator.setViewPager(viewPager);
    }
}
