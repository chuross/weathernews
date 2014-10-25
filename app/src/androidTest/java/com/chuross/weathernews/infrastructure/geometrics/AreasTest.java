package com.chuross.weathernews.infrastructure.geometrics;

import com.uphyca.testing.AndroidTestCase;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AreasTest extends AndroidTestCase {

    @Test
    public void 地域を取得できる() throws Exception {
        List<String> areas = Areas.getAllAreas(getContext());
        assertThat(areas.size(), is(8));
        assertThat(areas.get(0), is("北海道"));
        assertThat(areas.get(1), is("東北"));
        assertThat(areas.get(2), is("関東"));
        assertThat(areas.get(3), is("中部"));
        assertThat(areas.get(4), is("近畿"));
        assertThat(areas.get(5), is("中国"));
        assertThat(areas.get(6), is("四国"));
        assertThat(areas.get(7), is("九州"));
    }
}
