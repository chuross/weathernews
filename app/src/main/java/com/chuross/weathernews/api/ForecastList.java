package com.chuross.weathernews.api;

import org.simpleframework.xml.Element;

public class ForecastList {

    @Element(name = "response")
    private Response response;
    @Element(name = "forecast")
    private Forecast forecast;

    public Response getResponse() {
        return response;
    }

    public void setResponse(final Response response) {
        this.response = response;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(final Forecast forecast) {
        this.forecast = forecast;
    }
}
