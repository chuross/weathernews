package com.chuross.weathernews.api;

import net.arnx.jsonic.JSONHint;

public class Forecastday {

    private ForecastdayDate date;
    private String conditions;
    private String iconUrl;
    private int pop;
    private ForecastdayTemperature high;
    private ForecastdayTemperature low;

    @JSONHint(name = "date")
    public ForecastdayDate getDate() {
        return date;
    }

    @JSONHint(name = "date")
    public void setDate(final ForecastdayDate date) {
        this.date = date;
    }

    @JSONHint(name = "conditions")
    public String getConditions() {
        return conditions;
    }

    @JSONHint(name = "conditions")
    public void setConditions(final String conditions) {
        this.conditions = conditions;
    }

    @JSONHint(name = "icon_url")
    public String getIconUrl() {
        return iconUrl;
    }

    @JSONHint(name = "icon_url")
    public void setIconUrl(final String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @JSONHint(name = "pop")
    public int getPop() {
        return pop;
    }

    @JSONHint(name = "pop")
    public void setPop(final int pop) {
        this.pop = pop;
    }

    @JSONHint(name = "high")
    public ForecastdayTemperature getHigh() {
        return high;
    }

    @JSONHint(name = "high")
    public void setHigh(final ForecastdayTemperature high) {
        this.high = high;
    }

    @JSONHint(name = "low")
    public ForecastdayTemperature getLow() {
        return low;
    }

    @JSONHint(name = "low")
    public void setLow(final ForecastdayTemperature low) {
        this.low = low;
    }
}
