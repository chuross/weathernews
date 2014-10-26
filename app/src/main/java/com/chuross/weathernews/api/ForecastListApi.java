package com.chuross.weathernews.api;

import com.chuross.common.library.api.GetApi;
import com.chuross.common.library.http.HttpResponse;
import com.chuross.common.library.util.JsonUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Locale;

public class ForecastListApi extends GetApi<ForecastListResult> {

    private Location location;

    public ForecastListApi(Location location) {
        this.location = location;
    }

    @Override
    protected String getUrl() {
        String baseUrl = "http://api.wunderground.com/api/b3c52ad70a84caa3/forecast10day/lang:JP/q/%s.json";
        String query = String.format(Locale.JAPAN, "%s,%s", location.getLatitude(), location.getLongitude());
        return String.format(Locale.JAPAN, baseUrl, query);
    }

    @Override
    protected void setRequestHeaders(final List<Header> requestHeaders) {
    }

    @Override
    protected void setParameters(final List<NameValuePair> parameters) {
    }

    @Override
    protected ForecastListResult convert(final HttpResponse response) throws Exception {
        ForecastList list = JsonUtils.decode(response.getContentsAsString(), ForecastList.class);
        return new ForecastListResult(response.getStatus(), response.getHeaders(), list);
    }
}
