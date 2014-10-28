package com.chuross.weathernews.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.chuross.weathernews.R;
import com.chuross.weathernews.api.*;
import com.chuross.weathernews.geometrics.GeometricsConverter;
import com.chuross.weathernews.ui.adapter.SimpleStickyListHeadersAdapter;
import com.chuross.weathernews.util.DateProvider;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import org.jdeferred.AlwaysCallback;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.android.AndroidExecutionScope;
import roboguice.inject.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

public class CityChooseActivity extends Activity {

    private static final String EXTRA_KEY_PREFECTURE = "extra_key_prefecture";
    @InjectView(R.id.progress)
    private View progress;
    @InjectView(R.id.list)
    private StickyListHeadersListView listView;
    @Inject
    private DateProvider provider;
    private SimpleStickyListHeadersAdapter<String, City> adapter;
    private GeometricsApi geometricsApi = new GeometricsApi();
    private WeatherUndergroundApi weatherUndergroundApi = new WeatherUndergroundApi();

    public static void startActivity(android.app.Activity activity, String prefecture) {
        Intent intent = new Intent(activity, CityChooseActivity.class);
        intent.putExtra(EXTRA_KEY_PREFECTURE, prefecture);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
        final String prefecture = getIntent().getStringExtra(EXTRA_KEY_PREFECTURE);
        adapter = new SimpleStickyListHeadersAdapter<String, City>(getApplicationContext()) {
            @Override
            protected String getHeaderString(final String item) {
                return item;
            }

            @Override
            protected String getChildString(final City item) {
                return item.getName();
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                registerLocation(adapter.getItem(position), prefecture);
            }
        });
        Future<CityResult> future = geometricsApi.cities(AsyncTask.THREAD_POOL_EXECUTOR, prefecture);
        execute(AsyncTask.SERIAL_EXECUTOR, future, AndroidExecutionScope.UI).done(new DoneCallback<CityResult>() {
            @Override
            public void onDone(final CityResult result) {
                onCitiesDone(result);
            }
        }).fail(new FailCallback<Throwable>() {
            @Override
            public void onFail(final Throwable result) {
            }
        }).always(new AlwaysCallback<CityResult, Throwable>() {
            @Override
            public void onAlways(final Promise.State state, final CityResult resolved, final Throwable rejected) {
                progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void onCitiesDone(CityResult result) {
        if(result == null || !result.isSuccess()) {
            return;
        }
        List<City> cities = result.getResult().getList();
        ListMultimap<String, City> map = LinkedListMultimap.create();
        map.putAll("あ", filterCities(cities, Lists.newArrayList('あ', 'い', 'う', 'え', 'お')));
        map.putAll("か", filterCities(cities, Lists.newArrayList('か', 'き', 'く', 'け', 'こ')));
        map.putAll("さ", filterCities(cities, Lists.newArrayList('さ', 'し', 'す', 'せ', 'そ')));
        map.putAll("た", filterCities(cities, Lists.newArrayList('た', 'ち', 'つ', 'て', 'と')));
        map.putAll("な", filterCities(cities, Lists.newArrayList('な', 'に', 'ぬ', 'ね', 'の')));
        map.putAll("は", filterCities(cities, Lists.newArrayList('は', 'ひ', 'ふ', 'へ', 'ほ')));
        map.putAll("ま", filterCities(cities, Lists.newArrayList('ま', 'み', 'む', 'め', 'も')));
        map.putAll("や", filterCities(cities, Lists.newArrayList('や', 'ゆ', 'よ')));
        map.putAll("ら", filterCities(cities, Lists.newArrayList('ら', 'り', 'る', 'れ', 'ろ')));
        map.putAll("わ", filterCities(cities, Lists.newArrayList('わ', 'を', 'ん')));
        adapter.setListMap(map);
    }

    private List<City> filterCities(List<City> cities, final List<Character> patterns) {
        return Lists.newArrayList(Collections2.filter(cities, new Predicate<City>() {
            @Override
            public boolean apply(final City input) {
                return patterns.contains(input.getNameKana().charAt(0));
            }
        }));
    }

    private void registerLocation(City city, String prefecture) {
        progress.setVisibility(View.VISIBLE);
        Future<GeocodeResult> future = geometricsApi.geocode(AsyncTask.THREAD_POOL_EXECUTOR, StringUtils.join(prefecture, city.getName()));
        execute(AsyncTask.SERIAL_EXECUTOR, future, AndroidExecutionScope.UI).done(new DoneCallback<GeocodeResult>() {
            @Override
            public void onDone(final GeocodeResult result) {
                onGeocodeDone(result);
            }
        }).fail(new FailCallback<Throwable>() {
            @Override
            public void onFail(final Throwable result) {
                progress.setVisibility(View.INVISIBLE);
                showToast("市町村の取得に失敗しました。時間を置いて再度お試しください。", Toast.LENGTH_LONG);
            }
        });
    }

    private void onGeocodeDone(GeocodeResult result) {
        List<GeometryResult> list = result.getResult().getGeometryResults();
        if(list.isEmpty()) {
            showToast("観測地点の追加に失敗しました。時間を置いて再度お試しいただくか、違う市町村を選択してください。", Toast.LENGTH_LONG);
        } else {
            executeGeoLookup(GeometryResult.getLocation(list.get(0)));
        }
    }

    private void executeGeoLookup(GeometryLocation location) {
        Future<GeoLookupResult> future = weatherUndergroundApi.geoLookup(AsyncTask.THREAD_POOL_EXECUTOR, GeometricsConverter.convertLocation(location));
        execute(AsyncTask.SERIAL_EXECUTOR, future, AndroidExecutionScope.UI).done(new DoneCallback<GeoLookupResult>() {
            @Override
            public void onDone(final GeoLookupResult result) {
                onGeoLookupDone(result);
            }
        }).fail(new FailCallback<Throwable>() {
            @Override
            public void onFail(final Throwable result) {
                showToast("観測地点の追加に失敗しました。時間を置いて再度お試しください。", Toast.LENGTH_LONG);
            }
        });
    }

    private void onGeoLookupDone(GeoLookupResult result) {
        Location location = result.getResult().getLocation();
        Date now = provider.now();
        com.chuross.weathernews.db.Location model = new com.chuross.weathernews.db.Location(location.getName(), location.getLatitude(), location.getLongitude(), now, now);
        if(model.save() > 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            showToast("観測地点の追加に失敗しました。既に選択した市町村が追加されている可能性があります。", Toast.LENGTH_LONG);
        }
    }
}
