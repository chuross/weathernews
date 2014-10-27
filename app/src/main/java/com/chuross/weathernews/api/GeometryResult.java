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

    public static GeometryLocation getLocation(GeometryResult geometryResult) {
        return geometryResult != null && geometryResult.getGeometry() != null ? geometryResult.getGeometry().getLocation() : null;
    }
}
