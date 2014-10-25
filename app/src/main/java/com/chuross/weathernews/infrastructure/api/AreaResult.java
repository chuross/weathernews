package com.chuross.weathernews.infrastructure.api;

import com.chuross.common.library.api.result.AbstractResult;
import com.chuross.weathernews.infrastructure.geometrics.Area;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;

import java.util.List;

public class AreaResult extends AbstractResult<Area> {

    public AreaResult(final int status, final List<Header> headers, final Area result) {
        super(status, headers, result);
    }

    @Override
    public boolean isSuccess() {
        return getStatus() == 200 && getResult() != null && StringUtils.isBlank(getResult().getError());
    }
}
