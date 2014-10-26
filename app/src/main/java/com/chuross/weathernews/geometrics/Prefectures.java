package com.chuross.weathernews.geometrics;

import android.content.Context;
import com.chuross.common.library.util.FutureUtils;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import static com.chuross.weathernews.android.AssetUtils.readXmlResources;

public class Prefectures {

    private Prefectures() {
    }

    public static Future<List<String>> getAllPrefectures(Executor executor, final Context context) {
        return FutureUtils.executeOrNull(executor, new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return getAllPrefectures(context);
            }
        });
    }

    public static List<String> getAllPrefectures(Context context) throws Exception {
        List<String> prefectures = Lists.newArrayList();
        for(Area area : Area.values()) {
            prefectures.addAll(getPrefectures(context, area));
        }
        return prefectures;
    }

    public static Future<List<String>> getPrefectures(Executor executor, final Context context, final Area area) {
        return FutureUtils.executeOrNull(executor, new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return getPrefectures(context, area);
            }
        });
    }

    public static List<String> getPrefectures(Context context, Area area) throws Exception {
        switch(area) {
            case HOKKAIDO:
                return readXmlResources(context, Prefecture.class, "prefecture_hokkaido.xml", getPrefectureConvertFunction());
            case TOHOKU:
                return readXmlResources(context, Prefecture.class, "prefecture_tohoku.xml", getPrefectureConvertFunction());
            case KANTO:
                return readXmlResources(context, Prefecture.class, "prefecture_kanto.xml", getPrefectureConvertFunction());
            case CHUBU:
                return readXmlResources(context, Prefecture.class, "prefecture_chubu.xml", getPrefectureConvertFunction());
            case KINKI:
                return readXmlResources(context, Prefecture.class, "prefecture_kinki.xml", getPrefectureConvertFunction());
            case CHUGOKU:
                return readXmlResources(context, Prefecture.class, "prefecture_chugoku.xml", getPrefectureConvertFunction());
            case SHIKOKU:
                return readXmlResources(context, Prefecture.class, "prefecture_shikoku.xml", getPrefectureConvertFunction());
            case KYUSHU:
                return readXmlResources(context, Prefecture.class, "prefecture_kyushu.xml", getPrefectureConvertFunction());
            default:
                throw new IllegalArgumentException("invalid type.");
        }
    }

    private static Function<Prefecture, List<String>> getPrefectureConvertFunction() {
        return new Function<Prefecture, List<String>>() {
            @Override
            public List<String> apply(final Prefecture input) {
                return input.getPrefectures();
            }
        };
    }
}
