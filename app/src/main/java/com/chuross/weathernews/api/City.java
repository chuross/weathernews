package com.chuross.weathernews.api;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "location")
public class City {

    @Element(name = "city")
    private String name;
    @Element(name = "city-kana")
    private String nameKana;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNameKana() {
        return nameKana;
    }

    public void setNameKana(final String nameKana) {
        this.nameKana = nameKana;
    }
}
