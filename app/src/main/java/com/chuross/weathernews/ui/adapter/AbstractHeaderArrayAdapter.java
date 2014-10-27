package com.chuross.weathernews.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.chuross.common.android.library.utils.ViewUtils;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Range;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

import java.util.Collection;

public abstract class AbstractHeaderArrayAdapter<H, C> extends BaseAdapter implements StickyListHeadersAdapter {

    private Context context;
    private ListMultimap<H, C> map;

    public AbstractHeaderArrayAdapter(Context context) {
        init(context, LinkedListMultimap.<H, C>create());
    }

    public AbstractHeaderArrayAdapter(Context context, ListMultimap<H, C> map) {
        init(context, map);
    }

    private void init(Context context, ListMultimap<H, C> map) {
        this.context = context;
        this.map = map;
    }

    protected abstract int getHeaderViewResourceId();

    protected abstract int getChildViewResourceId();

    protected abstract void setHeaderView(View view, int position, H item, ViewGroup parent);

    protected abstract void setChildView(View view, int position, C item, ViewGroup parent);

    @Override
    public View getHeaderView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView == null ? ViewUtils.inflate(context, getHeaderViewResourceId(), parent, false) : convertView;
        setHeaderView(view, position, getHeaderItem(position), parent);
        return view;
    }

    @Override
    public long getHeaderId(final int position) {
        return getHeaderPosition(position);
    }

    public int getHeaderCount() {
        return map.size();
    }

    @Override
    public int getCount() {
        return map.values().size();
    }

    @Override
    public C getItem(final int position) {
        H key = getHeaderItem(position);
        return map.get(key).get(getChildPosition(position));
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView == null ? ViewUtils.inflate(context, getChildViewResourceId(), parent, false) : convertView;
        setChildView(view, position, getItem(position), parent);
        return view;
    }

    private H getHeaderItem(int position) {
        return (H) map.keySet().toArray()[getHeaderPosition(position)];
    }

    private int getHeaderPosition(int currentPosition) {
        int headerCount = 0;
        int position = 0;
        for(Collection<C> collection : map.asMap().values()) {
            if(Range.closedOpen(position, position + collection.size()).contains(currentPosition)) {
                return headerCount;
            }
            position += collection.size();
            headerCount++;
        }
        return headerCount;
    }

    private int getChildPosition(int currentPosition) {
        int position = 0;
        for(Collection<C> collection : map.asMap().values()) {
            if(Range.closedOpen(position, position + collection.size()).contains(currentPosition)) {
                return currentPosition - position;
            }
            position += collection.size();
        }
        return position;
    }

    public void setListMap(ListMultimap<H, C> map) {
        this.map = map;
        notifyDataSetChanged();
    }
}
