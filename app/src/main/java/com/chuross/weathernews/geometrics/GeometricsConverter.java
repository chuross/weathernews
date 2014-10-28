package com.chuross.weathernews.geometrics;

import com.chuross.weathernews.api.GeometryLocation;
import com.chuross.weathernews.api.Location;

public class GeometricsConverter {

    private GeometricsConverter() {
    }

    public static Location convertLocation(GeometryLocation location) {
        return new Location(location.getLatitude(), location.getLongitude());
    }

    public static Location convertLocation(com.chuross.weathernews.db.Location location) {
        return new Location(location.getLatitude(), location.getLongitude());
    }

    public static Location convertLocation(android.location.Location location) {
        return new Location(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()));
    }
}
