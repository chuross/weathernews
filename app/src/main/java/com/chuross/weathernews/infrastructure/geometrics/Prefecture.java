package com.chuross.weathernews.infrastructure.geometrics;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Prefecture {

    @ElementList(entry = "prefecture", inline = true, empty = false)
    private List<String> prefectures;
    @Element(name = "error", required = false)
    private String error;

    public List<String> getPrefectures() {
        return prefectures;
    }

    public void setPrefectures(final List<String> prefectures) {
        this.prefectures = prefectures;
    }

    public String getError() {
        return error;
    }

    public void setError(final String error) {
        this.error = error;
    }
}
