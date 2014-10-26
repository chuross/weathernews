package com.chuross.weathernews.api;

import com.chuross.common.library.api.GetApi;
import com.chuross.common.library.http.HttpResponse;
import com.chuross.common.library.util.JsonUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.List;

public class GeocodeApi extends GetApi<GeocodeResult> {

    private String address;

    public GeocodeApi(String address) {
        this.address = address;
    }

    @Override
    protected String getUrl() {
        return "http://maps.googleapis.com/maps/api/geocode/json";
    }

    @Override
    protected void setRequestHeaders(final List<Header> requestHeaders) {
    }

    @Override
    protected void setParameters(final List<NameValuePair> parameters) {
        addParameter(parameters, "language", "ja");
        addParameterIfNotNull(parameters, "address", address);
    }

    @Override
    protected GeocodeResult convert(final HttpResponse response) throws Exception {
        Geocode geocode = JsonUtils.decode(response.getContentsAsString(), Geocode.class);
        return new GeocodeResult(response.getStatus(), response.getHeaders(), geocode);
    }
}
