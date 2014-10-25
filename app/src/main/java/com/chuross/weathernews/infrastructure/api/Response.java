package com.chuross.weathernews.infrastructure.api;

public class Response {

    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(final Error error) {
        this.error = error;
    }
}
