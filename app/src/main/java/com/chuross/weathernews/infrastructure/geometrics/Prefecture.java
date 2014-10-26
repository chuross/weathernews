package com.chuross.weathernews.infrastructure.geometrics;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "response")
public class Prefecture {

    @ElementList(entry = "prefecture", inline = true, empty = false)
    private List<String> prefectures;

    public List<String> getPrefectures() {
        return prefectures;
    }

    public void setPrefectures(final List<String> prefectures) {
        this.prefectures = prefectures;
    }
}
