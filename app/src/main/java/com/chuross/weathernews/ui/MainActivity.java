package com.chuross.weathernews.ui;

import android.content.Intent;
import android.os.Bundle;
import com.activeandroid.query.Select;
import com.chuross.weathernews.R;
import com.chuross.weathernews.db.Location;
import roboguice.activity.RoboActivity;

import java.util.List;

public class MainActivity extends RoboActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Location> locations = new Select().from(Location.class).execute();
        if(locations.isEmpty()) {
            startActivity(new Intent(this, LocationAddActivity.class));
            return;
        }
    }
}
