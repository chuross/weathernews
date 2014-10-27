package com.chuross.weathernews.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import com.chuross.weathernews.R;
import com.chuross.weathernews.geometrics.Area;
import com.chuross.weathernews.geometrics.Prefectures;
import com.chuross.weathernews.ui.adapter.SimpleStickyListHeadersAdapter;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import roboguice.inject.InjectView;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class PrefectureChooseActivity extends Activity {

    @InjectView(R.id.list)
    private StickyListHeadersListView listView;
    private SimpleStickyListHeadersAdapter<Area, String> adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefecture);
        adapter = new SimpleStickyListHeadersAdapter<Area, String>(getApplicationContext(), getPrefectureMap()) {
            @Override
            protected String getHeaderString(final Area item) {
                return item.getName();
            }

            @Override
            protected String getChildString(final String item) {
                return item;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {
                CityChooseActivity.startActivity(PrefectureChooseActivity.this, adapter.getItem(position));
            }
        });
    }

    private ListMultimap<Area, String> getPrefectureMap() {
        ListMultimap<Area, String> map = LinkedListMultimap.create();
        map.putAll(Area.HOKKAIDO, Prefectures.getPrefectures(getApplicationContext(), Area.HOKKAIDO));
        map.putAll(Area.TOHOKU, Prefectures.getPrefectures(getApplicationContext(), Area.TOHOKU));
        map.putAll(Area.KANTO, Prefectures.getPrefectures(getApplicationContext(), Area.KANTO));
        map.putAll(Area.CHUBU, Prefectures.getPrefectures(getApplicationContext(), Area.CHUBU));
        map.putAll(Area.KINKI, Prefectures.getPrefectures(getApplicationContext(), Area.KINKI));
        map.putAll(Area.CHUGOKU, Prefectures.getPrefectures(getApplicationContext(), Area.CHUGOKU));
        map.putAll(Area.SHIKOKU, Prefectures.getPrefectures(getApplicationContext(), Area.SHIKOKU));
        map.putAll(Area.KYUSHU, Prefectures.getPrefectures(getApplicationContext(), Area.KYUSHU));
        return map;
    }
}
