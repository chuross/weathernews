package com.chuross.weathernews.api;

import com.chuross.common.library.api.GetApi;
import com.chuross.common.library.http.HttpResponse;
import com.chuross.common.library.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.List;
import java.util.Locale;

public class GeoLookupApi extends GetApi<GeoLookupResult> {

    private String query;
    private boolean isLocationQuery = false;

    public GeoLookupApi(String query) {
        this.query = query;
    }

    public GeoLookupApi(Location location) {
        this.isLocationQuery = true;
        query = String.format(Locale.JAPAN, "%s,%s", location.getLatitude(), location.getLongitude());
    }

    @Override
    protected String getUrl() {
        String baseUrl = "http://api.wunderground.com/api/b3c52ad70a84caa3/geolookup";
        String path = isLocationQuery ? "/q/%s.json" : "/q/japan/%s.json";
        String url = StringUtils.join(baseUrl, path);
        return String.format(Locale.JAPAN, url, query);
    }

    @Override
    protected void setRequestHeaders(final List<Header> requestHeaders) {
    }

    @Override
    protected void setParameters(final List<NameValuePair> parameters) {
    }

    @Override
    protected GeoLookupResult convert(final HttpResponse response) throws Exception {
        GeoLookup geoLookup = JsonUtils.decode(response.getContentsAsString(), GeoLookup.class);
        return new GeoLookupResult(response.getStatus(), response.getHeaders(), geoLookup);
    }
}
