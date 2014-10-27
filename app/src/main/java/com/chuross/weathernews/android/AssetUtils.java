package com.chuross.weathernews.android;

import android.content.Context;
import com.chuross.common.library.util.IOUtils;
import com.chuross.common.library.util.MethodCallUtils;
import com.chuross.common.library.util.XmlUtils;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.Callable;

public class AssetUtils {

    private AssetUtils() {
    }

    public static <T> List<String> readXmlResourcesOrNull(final Context context, final Class<T> clazz, final String filePath, final Function<T, List<String>> convertFunction) {
        return MethodCallUtils.callOrNull(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                return readXmlResources(context, clazz, filePath, convertFunction);
            }
        });
    }

    private static <T> List<String> readXmlResources(Context context, Class<T> clazz, String filePath, Function<T, List<String>> convertFunction) throws Exception {
        if(context == null || clazz == null || StringUtils.isBlank(filePath) || convertFunction == null) {
            return Lists.newArrayList();
        }
        String xml = IOUtils.toString(context.getAssets().open(filePath), Charset.forName("UTF-8"));
        T element = XmlUtils.read(clazz, xml, false);
        return convertFunction.apply(element);
    }
}
