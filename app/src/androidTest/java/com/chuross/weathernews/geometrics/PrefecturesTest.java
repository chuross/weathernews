package com.chuross.weathernews.geometrics;

import com.uphyca.testing.AndroidTestCase;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PrefecturesTest extends AndroidTestCase {

    @Test
    public void 都道府県を取得できる() throws Exception {
        List<String> prefectures = Prefectures.getAllPrefectures(getContext());
        assertThat(prefectures.size(), is(47));
        assertThat(prefectures.get(0), is("北海道"));
        assertThat(prefectures.get(1), is("青森県"));
        assertThat(prefectures.get(2), is("岩手県"));
        assertThat(prefectures.get(3), is("宮城県"));
        assertThat(prefectures.get(4), is("秋田県"));
        assertThat(prefectures.get(5), is("山形県"));
        assertThat(prefectures.get(6), is("福島県"));
        assertThat(prefectures.get(7), is("茨城県"));
        assertThat(prefectures.get(8), is("栃木県"));
        assertThat(prefectures.get(9), is("群馬県"));
        assertThat(prefectures.get(10), is("埼玉県"));
        assertThat(prefectures.get(11), is("千葉県"));
        assertThat(prefectures.get(12), is("東京都"));
        assertThat(prefectures.get(13), is("神奈川県"));
        assertThat(prefectures.get(14), is("新潟県"));
        assertThat(prefectures.get(15), is("富山県"));
        assertThat(prefectures.get(16), is("石川県"));
        assertThat(prefectures.get(17), is("福井県"));
        assertThat(prefectures.get(18), is("山梨県"));
        assertThat(prefectures.get(19), is("長野県"));
        assertThat(prefectures.get(20), is("岐阜県"));
        assertThat(prefectures.get(21), is("静岡県"));
        assertThat(prefectures.get(22), is("愛知県"));
        assertThat(prefectures.get(23), is("三重県"));
        assertThat(prefectures.get(24), is("滋賀県"));
        assertThat(prefectures.get(25), is("京都府"));
        assertThat(prefectures.get(26), is("大阪府"));
        assertThat(prefectures.get(27), is("兵庫県"));
        assertThat(prefectures.get(28), is("奈良県"));
        assertThat(prefectures.get(29), is("和歌山県"));
        assertThat(prefectures.get(30), is("鳥取県"));
        assertThat(prefectures.get(31), is("島根県"));
        assertThat(prefectures.get(32), is("岡山県"));
        assertThat(prefectures.get(33), is("広島県"));
        assertThat(prefectures.get(34), is("山口県"));
        assertThat(prefectures.get(35), is("徳島県"));
        assertThat(prefectures.get(36), is("香川県"));
        assertThat(prefectures.get(37), is("愛媛県"));
        assertThat(prefectures.get(38), is("高知県"));
        assertThat(prefectures.get(39), is("福岡県"));
        assertThat(prefectures.get(40), is("佐賀県"));
        assertThat(prefectures.get(41), is("長崎県"));
        assertThat(prefectures.get(42), is("熊本県"));
        assertThat(prefectures.get(43), is("大分県"));
        assertThat(prefectures.get(44), is("宮崎県"));
        assertThat(prefectures.get(45), is("鹿児島県"));
        assertThat(prefectures.get(46), is("沖縄県"));
    }

    @Test
    public void 地域を指定して都道府県を取得できる() throws Exception {
        List<String> prefectures = Prefectures.getPrefectures(getContext(), Area.KANTO);
        assertThat(prefectures.size(), is(7));
        assertThat(prefectures.get(0), is("茨城県"));
        assertThat(prefectures.get(1), is("栃木県"));
        assertThat(prefectures.get(2), is("群馬県"));
        assertThat(prefectures.get(3), is("埼玉県"));
        assertThat(prefectures.get(4), is("千葉県"));
        assertThat(prefectures.get(5), is("東京都"));
        assertThat(prefectures.get(6), is("神奈川県"));
    }
}
