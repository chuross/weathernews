package com.chuross.weathernews.api;

import com.chuross.common.library.api.result.AbstractResult;
import org.apache.http.Header;

import java.net.HttpURLConnection;
import java.util.List;

public class GeocodeResult extends AbstractResult<Geocode> {

    public GeocodeResult(final int status, final List<Header> headers, final Geocode result) {
        super(status, headers, result);
    }

    @Override
    public boolean isSuccess() {
        return getStatus() == HttpURLConnection.HTTP_OK && getResult() != null && getResult().getStatus().equals("OK");
    }
}
