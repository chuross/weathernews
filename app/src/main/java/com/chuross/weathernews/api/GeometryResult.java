package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

public class GeometryResult {

    private Geometry geometry;

    @JSONHint(name = "geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    @JSONHint(name = "geometry")
    public void setGeometry(final Geometry geometry) {
        this.geometry = geometry;
    }
}
