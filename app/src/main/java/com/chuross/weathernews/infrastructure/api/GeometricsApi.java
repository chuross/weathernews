package com.chuross.weathernews.infrastructure.api;

import org.apache.http.client.config.RequestConfig;

import java.util.concurrent.TimeUnit;

public class GeometricsApi {

    private static final int TIME_OUT = (int) TimeUnit.SECONDS.toMillis(10);
    private static final RequestConfig CONFIG = RequestConfig.custom().setConnectTimeout(TIME_OUT).setSocketTimeout(TIME_OUT).build();
    private static final int RETRY_COUNT = 3;
}
