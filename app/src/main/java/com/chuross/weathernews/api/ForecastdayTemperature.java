package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

public class ForecastdayTemperature {

    private int fahrenheit;
    private int celsius;

    @JSONHint(name = "fahrenheit")
    public int getFahrenheit() {
        return fahrenheit;
    }

    @JSONHint(name = "fahrenheit")
    public void setFahrenheit(final int fahrenheit) {
        this.fahrenheit = fahrenheit;
    }

    @JSONHint(name = "celsius")
    public int getCelsius() {
        return celsius;
    }

    @JSONHint(name = "celsius")
    public void setCelsius(final int celsius) {
        this.celsius = celsius;
    }
}
