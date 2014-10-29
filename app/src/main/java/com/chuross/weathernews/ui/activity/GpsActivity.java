package com.chuross.weathernews.ui.activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.MenuItem;
import android.widget.Toast;
import com.chuross.common.android.library.ui.dialog.DialogFragmentBuilder;
import com.chuross.weathernews.R;
import com.chuross.weathernews.api.GeoLookupResult;
import com.chuross.weathernews.api.WeatherUndergroundApi;
import com.chuross.weathernews.geometrics.GeometricsConverter;
import com.chuross.weathernews.util.DateProvider;
import com.google.inject.Inject;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.android.AndroidExecutionScope;

import java.util.Date;
import java.util.concurrent.Future;

public class GpsActivity extends Activity implements LocationListener {

    @Inject
    private DateProvider provider;
    private LocationManager locationManager;
    private WeatherUndergroundApi weatherUndergroundApi = new WeatherUndergroundApi();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_gps);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            showConfirmGpsSettingDialog();
        }
        locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, this, Looper.getMainLooper());
    }

    private void showConfirmGpsSettingDialog() {
        DialogFragment dialogFragment = new DialogFragmentBuilder()
                .setMessage("GPSを利用するにはGoogle位置情報サービスのを有効にする必要があります。設定画面に移動しますか？")
                .setPositiveText("はい")
                .setNegativeText("いいえ")
                .build();
        setOnPositiveClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                finish();
            }
        });
        setOnNegativeClickListener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, final int which) {
                finish();
            }
        });
        dialogFragment.show(getFragmentManager(), null);
    }

    @Override
    public void onLocationChanged(Location location) {
        Future<GeoLookupResult> future = weatherUndergroundApi.geoLookup(AsyncTask.THREAD_POOL_EXECUTOR, GeometricsConverter.convertLocation(location));
        execute(AsyncTask.SERIAL_EXECUTOR, future, AndroidExecutionScope.UI).done(new DoneCallback<GeoLookupResult>() {
            @Override
            public void onDone(final GeoLookupResult result) {
                onGeoLookupDone(result);
            }
        }).fail(new FailCallback<Throwable>() {
            @Override
            public void onFail(final Throwable result) {
                showToast("地域の追加に失敗しました。時間を置いて再度お試しください。", Toast.LENGTH_LONG);
                finish();
            }
        });
    }

    @Override
    public void onStatusChanged(final String provider, final int status, final Bundle extras) {
    }

    @Override
    public void onProviderEnabled(final String provider) {
    }

    @Override
    public void onProviderDisabled(final String provider) {
    }

    private void onGeoLookupDone(GeoLookupResult result) {
        com.chuross.weathernews.api.Location location = result.getResult().getLocation();
        Date now = provider.now();
        com.chuross.weathernews.db.Location model = new com.chuross.weathernews.db.Location(location.getName(), location.getLatitude(), location.getLongitude(), now, now);
        if(model.save() > 0) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            showToast("地域の追加に失敗しました。既に選択した市町村が追加されている可能性があります。", Toast.LENGTH_LONG);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        if(locationManager != null) {
            locationManager.removeUpdates(this);
        }
        super.onStop();
    }
}
