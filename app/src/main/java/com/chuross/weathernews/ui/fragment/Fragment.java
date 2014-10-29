package com.chuross.weathernews.ui.fragment;

import com.chuross.weathernews.ui.activity.Activity;
import roboguice.fragment.RoboFragment;

public class Fragment extends RoboFragment {

    public Activity getActivity(android.support.v4.app.Fragment fragment) {
        return (Activity) fragment.getActivity();
    }
}
