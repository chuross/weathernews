package com.chuross.weathernews.api;

import com.chuross.common.library.api.result.AbstractResult;
import org.apache.http.Header;

import java.util.List;

public class GeoLookupResult extends AbstractResult<GeoLookup> {

    public GeoLookupResult(final int status, final List<Header> headers, final GeoLookup result) {
        super(status, headers, result);
    }

    @Override
    public boolean isSuccess() {
        return getStatus() == 200 && getResult() != null && getResult().getResponse().getError() == null;
    }
}
