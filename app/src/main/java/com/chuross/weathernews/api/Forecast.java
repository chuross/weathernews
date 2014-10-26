package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

public class Forecast {

    private SimpleForecast simpleForecast;

    @JSONHint(name = "simpleforecast")
    public SimpleForecast getSimpleForecast() {
        return simpleForecast;
    }

    @JSONHint(name = "simpleforecast")
    public void setSimpleForecast(final SimpleForecast simpleForecast) {
        this.simpleForecast = simpleForecast;
    }
}
