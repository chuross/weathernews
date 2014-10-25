package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

public class Location {

    private String name;
    private String latitude;
    private String longitude;

    public Location() {
    }

    public Location(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @JSONHint(name = "city")
    public String getName() {
        return name;
    }

    @JSONHint(name = "city")
    public void setName(final String name) {
        this.name = name;
    }

    @JSONHint(name = "lat")
    public String getLatitude() {
        return latitude;
    }

    @JSONHint(name = "lat")
    public void setLatitude(final String latitude) {
        this.latitude = latitude;
    }

    @JSONHint(name = "lon")
    public String getLongitude() {
        return longitude;
    }

    @JSONHint(name = "lon")
    public void setLongitude(final String longitude) {
        this.longitude = longitude;
    }
}
