package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

import java.util.List;

public class SimpleForecast {

    private List<Forecastday> forecastdays;

    @JSONHint(name = "forecastday")
    public List<Forecastday> getForecastdays() {
        return forecastdays;
    }

    @JSONHint(name = "forecastday")
    public void setForecastdays(final List<Forecastday> forecastdays) {
        this.forecastdays = forecastdays;
    }
}
