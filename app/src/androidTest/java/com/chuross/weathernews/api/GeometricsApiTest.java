package com.chuross.weathernews.api;

import com.chuross.common.library.util.FutureUtils;
import com.google.common.util.concurrent.MoreExecutors;
import com.uphyca.testing.AndroidTestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class GeometricsApiTest extends AndroidTestCase {

    private GeometricsApi api;

    @Before
    public void before() {
        api = new GeometricsApi();
    }

    @Test
    public void 都道府県から市町村が取得できる() {
        CityResult result = FutureUtils.getOrNull(api.cities(MoreExecutors.sameThreadExecutor(), "東京都"));
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getResult().getError(), nullValue());
        List<City> cities = result.getResult().getList();
        assertThat(cities.size() > 0, is(true));
    }

    @Test
    public void 住所から緯度経度が取得できる() {
        GeocodeResult result = FutureUtils.getOrNull(api.geocode(MoreExecutors.sameThreadExecutor(), "東京都千代田区丸の内1丁目"));
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getResult().getStatus(), is("OK"));
        List<GeometryResult> results = result.getResult().getGeometryResults();
        assertThat(results.size(), is(1));
        GeometryLocation location = results.get(0).getGeometry().getLocation();
        assertThat(location.getLatitude(), is("35.6836182"));
        assertThat(location.getLongitude(), is("139.766453"));
    }
}
