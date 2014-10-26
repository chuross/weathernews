package com.chuross.weathernews.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response")
public class Cities {

    @ElementList(entry = "location", inline = true, empty = false)
    private List<City> list;
    @Element(name = "error", required = false)
    private String error;

    public List<City> getList() {
        return list;
    }

    public void setList(final List<City> list) {
        this.list = list;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }
}
