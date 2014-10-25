package com.chuross.weathernews.infrastructure.api;

import com.chuross.common.library.util.FutureUtils;
import com.google.common.util.concurrent.MoreExecutors;
import com.uphyca.testing.AndroidTestCase;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class WeatherUndergroundApiTest extends AndroidTestCase {

    private WeatherUndergroundApi api;

    @Before
    public void before() {
        api = new WeatherUndergroundApi();
    }

    @Test
    public void 都市の名前から座標を取得できる() {
        GeoLookupResult result = FutureUtils.getOrNull(api.geoLookup(MoreExecutors.sameThreadExecutor(), "Tokyo"));
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getResult().getResponse().getError(), nullValue());
        Location location = result.getResult().getLocation();
        assertThat(location.getName(), is("Tokyo"));
        assertThat(location.getLatitude(), is("35.54999924"));
        assertThat(location.getLongitude(), is("139.77999878"));
    }

    @Test
    public void 緯度経度から都市名と座標を取得できる() {
        // 渡した緯度・経度の値が削られて処理されるため精度は低い
        Location location = new Location("35.54999924", "139.77999878");
        GeoLookupResult result = FutureUtils.getOrNull(api.geoLookup(MoreExecutors.sameThreadExecutor(), location));
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getResult().getResponse().getError(), nullValue());
        Location resultLocation = result.getResult().getLocation();
        assertThat(resultLocation.getName(), is("Ota"));
        assertThat(resultLocation.getLatitude(), is("35.540000"));
        assertThat(resultLocation.getLongitude(), is("139.770000"));
    }
}
