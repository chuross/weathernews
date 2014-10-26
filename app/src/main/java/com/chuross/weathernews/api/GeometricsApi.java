package com.chuross.weathernews.api;

import org.apache.http.client.config.RequestConfig;

import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class GeometricsApi {

    private static final int TIME_OUT = (int) TimeUnit.SECONDS.toMillis(10);
    private static final RequestConfig CONFIG = RequestConfig.custom().setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
    private static final int RETRY_COUNT = 3;

    public Future<CityResult> cities(Executor executor, String prefecture) {
        return new CityApi(prefecture).execute(executor, CONFIG, RETRY_COUNT);
    }

    public Future<GeocodeResult> geocode(Executor executor, String address) {
        return new GeocodeApi(address).execute(executor, CONFIG, RETRY_COUNT);
    }
}
