package com.chuross.weathernews.api;

import com.chuross.common.library.api.result.AbstractResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;

import java.net.HttpURLConnection;
import java.util.List;

public class CityResult extends AbstractResult<Cities> {

    public CityResult(final int status, final List<Header> headers, final Cities result) {
        super(status, headers, result);
    }

    @Override
    public boolean isSuccess() {
        return getStatus() == HttpURLConnection.HTTP_OK && getResult() != null && StringUtils.isBlank(getResult().getError());
    }
}
