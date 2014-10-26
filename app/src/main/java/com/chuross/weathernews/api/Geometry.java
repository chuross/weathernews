package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

public class Geometry {

    private GeometryLocation location;

    @JSONHint(name = "location")
    public GeometryLocation getLocation() {
        return location;
    }

    @JSONHint(name = "location")
    public void setLocation(final GeometryLocation location) {
        this.location = location;
    }
}
