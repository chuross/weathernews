package com.chuross.weathernews.api;

import com.chuross.common.library.api.GetApi;
import com.chuross.common.library.http.HttpResponse;
import com.chuross.common.library.util.XmlUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.List;

public class CityApi extends GetApi<CityResult> {

    private String prefecture;

    public CityApi(String prefecture) {
        this.prefecture = prefecture;
    }

    @Override
    protected String getUrl() {
        return "http://geoapi.heartrails.com/api/xml?method=getCities";
    }

    @Override
    protected void setRequestHeaders(final List<Header> requestHeaders) {
    }

    @Override
    protected void setParameters(final List<NameValuePair> parameters) {
        addParameterIfNotNull(parameters, "prefecture", prefecture);
    }

    @Override
    protected CityResult convert(final HttpResponse response) throws Exception {
        Cities cities = XmlUtils.read(Cities.class, response.getContentsAsString(), false);
        return new CityResult(response.getStatus(), response.getHeaders(), cities);
    }
}
