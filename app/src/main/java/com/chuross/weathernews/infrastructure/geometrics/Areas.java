package com.chuross.weathernews.infrastructure.geometrics;

import android.content.Context;
import com.chuross.common.library.util.FutureUtils;
import com.google.common.base.Function;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public class Areas {

    private Areas() {
    }

    public static Future<List<String>> getAllAreas(Executor executor, final Context context) {
        return FutureUtils.executeOrNull(executor, new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return getAllAreas(context);
            }
        });
    }

    public static List<String> getAllAreas(Context context) throws Exception {
        return GeometricsUtils.getResources(context, Area.class, "areas.xml", new Function<Area, List<String>>() {
            @Override
            public List<String> apply(final Area input) {
                return input.getAreas();
            }
        });
    }
}
