package com.chuross.weathernews.api;

import org.apache.http.client.config.RequestConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class WeatherUndergroundApi {

    private static final int TIME_OUT = (int) TimeUnit.SECONDS.toMillis(10);
    private static final RequestConfig CONFIG = RequestConfig.custom().setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
    private static final int RETRY_COUNT = 3;

    public Future<GeoLookupResult> geoLookup(Executor executor, Location location) {
        return new GeoLookupApi(location).execute(executor, CONFIG, RETRY_COUNT);
    }

    public Future<ForecastListResult> forecastList(Executor executor, Location location) {
        return new ForecastListApi(location).execute(executor, CONFIG, RETRY_COUNT);
    }
}
