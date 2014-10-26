package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

import java.util.List;

public class Geocode {

    private String status;
    private List<GeometryResult> results;

    @JSONHint(name = "status")
    public String getStatus() {
        return status;
    }

    @JSONHint(name = "status")
    public void setStatus(final String status) {
        this.status = status;
    }

    @JSONHint(name = "results")
    public List<GeometryResult> getGeometryResults() {
        return results;
    }

    @JSONHint(name = "results")
    public void setGeometryResults(final List<GeometryResult> results) {
        this.results = results;
    }
}
