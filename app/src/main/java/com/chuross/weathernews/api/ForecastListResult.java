package com.chuross.weathernews.api;

import com.chuross.common.library.api.result.AbstractResult;
import org.apache.http.Header;

import java.net.HttpURLConnection;
import java.util.List;

public class ForecastListResult extends AbstractResult<ForecastList> {

    public ForecastListResult(final int status, final List<Header> headers, final ForecastList result) {
        super(status, headers, result);
    }

    @Override
    public boolean isSuccess() {
        return getStatus() == HttpURLConnection.HTTP_OK && getResult() != null && getResult().getResponse().getError() == null;
    }
}
