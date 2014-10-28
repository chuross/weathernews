package com.chuross.weathernews.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.chuross.common.android.library.utils.ViewUtils;
import com.chuross.weathernews.R;
import com.chuross.weathernews.api.ForecastListResult;
import com.chuross.weathernews.api.Forecastday;
import com.chuross.weathernews.api.WeatherUndergroundApi;
import com.chuross.weathernews.db.Location;
import com.chuross.weathernews.geometrics.GeometricsConverter;
import com.squareup.picasso.Picasso;
import org.jdeferred.AlwaysCallback;
import org.jdeferred.DoneCallback;
import org.jdeferred.FailCallback;
import org.jdeferred.Promise;
import org.jdeferred.android.AndroidExecutionScope;
import roboguice.inject.InjectView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

public class ForecastFragment extends Fragment {

    private static final String ARGUMENT_KEY_IDENTITY = "ARGUMENT_key_identity";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd(E)");
    @InjectView(R.id.progress)
    private View progress;
    @InjectView(R.id.today_date_text)
    private TextView todayDateTextView;
    @InjectView(R.id.weather_icon)
    private ImageView todayWeatherIcon;
    @InjectView(R.id.temperature_max)
    private TextView temperatureMaxView;
    @InjectView(R.id.temperature_min)
    private TextView temperatureMinView;
    @InjectView(R.id.pop_text)
    private TextView popTextView;
    @InjectView(R.id.conditions)
    private TextView conditionsView;
    @InjectView(R.id.forecast_table)
    private TableLayout forecastTable;

    public static ForecastFragment create(long id) {
        Bundle arguments = new Bundle();
        arguments.putLong(ARGUMENT_KEY_IDENTITY, id);
        ForecastFragment fragment = new ForecastFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        long id = getArguments().getLong(ARGUMENT_KEY_IDENTITY, -1);
        if(id == -1) {
            // TODO error
            return;
        }
        Location location = Location.load(Location.class, id);
        Future<ForecastListResult> future = new WeatherUndergroundApi().forecastList(AsyncTask.THREAD_POOL_EXECUTOR, GeometricsConverter.convertLocation(location));
        getActivity(this).execute(AsyncTask.SERIAL_EXECUTOR, future, AndroidExecutionScope.UI).done(new DoneCallback<ForecastListResult>() {
            @Override
            public void onDone(final ForecastListResult result) {
                onForecastListDone(result);
            }
        }).fail(new FailCallback<Throwable>() {
            @Override
            public void onFail(final Throwable result) {
                //TODO error
            }
        }).always(new AlwaysCallback<ForecastListResult, Throwable>() {
            @Override
            public void onAlways(final Promise.State state, final ForecastListResult resolved, final Throwable rejected) {
                progress.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void onForecastListDone(ForecastListResult result) {
        List<Forecastday> forecastdays = result.getResult().getForecast().getSimpleForecast().getForecastdays();
        Forecastday today = forecastdays.get(0);
        todayDateTextView.setText(String.format(Locale.JAPAN, getString(R.string.format_today), DATE_FORMAT.format(today.getDate().getEpoch())));
        Picasso.with(getActivity().getApplicationContext()).load(today.getIconUrl()).into(todayWeatherIcon);
        temperatureMaxView.setText(String.valueOf(today.getHigh().getCelsius()));
        temperatureMinView.setText(String.valueOf(today.getLow().getCelsius()));
        popTextView.setText(String.format(Locale.JAPAN, getString(R.string.format_pop), String.valueOf(today.getPop())) + "%");
        conditionsView.setText(today.getConditions());
        forecastTable.removeAllViews();
        for(int i = 1; i < forecastdays.size(); i += 3) {
            addTableRow(forecastdays.subList(i, i + 3));
        }
    }

    private void addTableRow(List<Forecastday> forecastdays) {
        TableRow row = new TableRow(getActivity().getApplicationContext());
        for(Forecastday forecastday : forecastdays) {
            row.addView(getTableView(row, forecastday));
        }
        forecastTable.addView(row);
    }

    private View getTableView(TableRow row, Forecastday forecastday) {
        View view = ViewUtils.inflate(getActivity().getApplicationContext(), R.layout.parts_other_forecast, row, false);
        ((TextView) view.findViewById(R.id.date_text)).setText(DATE_FORMAT.format(forecastday.getDate().getEpoch()));
        ImageView imageView = (ImageView) view.findViewById(R.id.weather_icon);
        Picasso.with(getActivity().getApplicationContext()).load(forecastday.getIconUrl()).into(imageView);
        ((TextView) view.findViewById(R.id.temperature_max)).setText(String.valueOf(forecastday.getHigh().getCelsius()));
        ((TextView) view.findViewById(R.id.temperature_min)).setText(String.valueOf(forecastday.getLow().getCelsius()));
        ((TextView) view.findViewById(R.id.pop_text)).setText(String.valueOf(forecastday.getPop()));
        return view;
    }

    private void initView() {
        todayDateTextView.setText(null);
        temperatureMaxView.setText(null);
        temperatureMinView.setText(null);
        popTextView.setText(null);
    }
}
