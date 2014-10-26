package com.chuross.weathernews.api;

import com.chuross.common.library.util.FutureUtils;
import com.google.common.util.concurrent.MoreExecutors;
import com.uphyca.testing.AndroidTestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.assertThat;

public class WeatherUndergroundApiTest extends AndroidTestCase {

    private WeatherUndergroundApi api;

    @Before
    public void before() {
        api = new WeatherUndergroundApi();
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

    @Test
    public void 緯度経度から天気の情報リストを取得できる() {
        Location location = new Location("35.54999924", "139.77999878");
        ForecastListResult result = FutureUtils.getOrNull(api.forecastList(MoreExecutors.sameThreadExecutor(), location));
        assertThat(result.isSuccess(), is(true));
        assertThat(result.getResult().getResponse().getError(), nullValue());
        List<Forecastday> forecastdays = result.getResult().getForecast().getSimpleForecast().getForecastdays();
        assertThat(forecastdays.size(), greaterThan(0));
        Forecastday forecastday = forecastdays.get(0);
        assertThat(forecastday.getConditions(), notNullValue());
        assertThat(forecastday.getDate(), notNullValue());
        assertThat(forecastday.getIconUrl(), notNullValue());
        assertThat(forecastday.getHigh(), notNullValue());
        assertThat(forecastday.getLow(), notNullValue());
        assertThat(forecastday.getPop(), greaterThanOrEqualTo(0));
    }
}
