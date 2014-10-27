package com.chuross.weathernews.api;

public class GeometricsUtils {

    private GeometricsUtils() {
    }

    public static Location convertLocation(GeometryLocation location) {
        return new Location(location.getLatitude(), location.getLongitude());
    }
}
