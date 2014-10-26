package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

public class GeometryLocation {

    private String latitude;
    private String longitude;

    @JSONHint(name = "lat")
    public String getLatitude() {
        return latitude;
    }

    @JSONHint(name = "lat")
    public void setLatitude(final String latitude) {
        this.latitude = latitude;
    }

    @JSONHint(name = "lng")
    public String getLongitude() {
        return longitude;
    }

    @JSONHint(name = "lng")
    public void setLongitude(final String longitude) {
        this.longitude = longitude;
    }
}
