package com.chuross.weathernews.infrastructure.geometrics;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response")
public class Area {

    @ElementList(entry = "area", inline = true, empty = false)
    private List<String> areas;
    @Element(name = "error", required = false)
    private String error;

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(final List<String> areas) {
        this.areas = areas;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }
}
