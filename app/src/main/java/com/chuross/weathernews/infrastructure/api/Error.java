package com.chuross.weathernews.infrastructure.api;

public class Error {

    private String type;
    private String description;

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
