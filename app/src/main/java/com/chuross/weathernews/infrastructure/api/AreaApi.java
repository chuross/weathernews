package com.chuross.weathernews.infrastructure.api;

import com.chuross.common.library.api.GetApi;
import com.chuross.common.library.http.HttpResponse;
import com.chuross.common.library.util.XmlUtils;
import com.chuross.weathernews.infrastructure.geometrics.Area;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.List;

public class AreaApi extends GetApi<AreaResult> {

    @Override
    protected String getUrl() {
        return "http://geoapi.heartrails.com/api/xml?method=getAreas";
    }

    @Override
    protected void setRequestHeaders(final List<Header> requestHeaders) {
    }

    @Override
    protected void setParameters(final List<NameValuePair> parameters) {
    }

    @Override
    protected AreaResult convert(final HttpResponse response) throws Exception {
        Area area = XmlUtils.read(Area.class, response.getContentsAsString(), false);
        return new AreaResult(response.getStatus(), response.getHeaders(), area);
    }
}
