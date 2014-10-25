package com.chuross.weathernews.infrastructure.geometrics;

import android.content.Context;
import com.chuross.common.library.util.FutureUtils;
import com.google.common.base.Function;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import static com.chuross.weathernews.infrastructure.geometrics.GeometricsUtils.getResources;

public class Prefectures {

    private Prefectures() {
    }

    public static enum Type {
        HOKKAIDO("北海道"),
        TOHOKU("東北"),
        KANTO("関東"),
        CHUBU("中部"),
        KINKI("近畿"),
        CHUGOKU("中国"),
        SHIKOKU("四国"),
        KYUSHU("九州");
        private String name;

        private Type(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
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
        prefectures.addAll(getPrefectures(context, Type.HOKKAIDO));
        prefectures.addAll(getPrefectures(context, Type.TOHOKU));
        prefectures.addAll(getPrefectures(context, Type.KANTO));
        prefectures.addAll(getPrefectures(context, Type.CHUBU));
        prefectures.addAll(getPrefectures(context, Type.KINKI));
        prefectures.addAll(getPrefectures(context, Type.CHUGOKU));
        prefectures.addAll(getPrefectures(context, Type.SHIKOKU));
        prefectures.addAll(getPrefectures(context, Type.KYUSHU));
        return prefectures;
    }

    public static Future<List<String>> getPrefectures(Executor executor, final Context context, final Type type) {
        return FutureUtils.executeOrNull(executor, new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return getPrefectures(context, type);
            }
        });
    }

    public static List<String> getPrefectures(Context context, Type type) throws Exception {
        switch(type) {
            case HOKKAIDO:
                return getResources(context, Prefecture.class, "prefecture_hokkaido.xml", getPrefectureConvertFunction());
            case TOHOKU:
                return getResources(context, Prefecture.class, "prefecture_tohoku.xml", getPrefectureConvertFunction());
            case KANTO:
                return getResources(context, Prefecture.class, "prefecture_kanto.xml", getPrefectureConvertFunction());
            case CHUBU:
                return getResources(context, Prefecture.class, "prefecture_chubu.xml", getPrefectureConvertFunction());
            case KINKI:
                return getResources(context, Prefecture.class, "prefecture_kinki.xml", getPrefectureConvertFunction());
            case CHUGOKU:
                return getResources(context, Prefecture.class, "prefecture_chugoku.xml", getPrefectureConvertFunction());
            case SHIKOKU:
                return getResources(context, Prefecture.class, "prefecture_shikoku.xml", getPrefectureConvertFunction());
            case KYUSHU:
                return getResources(context, Prefecture.class, "prefecture_kyushu.xml", getPrefectureConvertFunction());
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
