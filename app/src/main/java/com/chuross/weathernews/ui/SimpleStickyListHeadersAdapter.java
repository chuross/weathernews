package com.chuross.weathernews.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.chuross.weathernews.R;
import com.google.common.collect.ListMultimap;

public abstract class SimpleStickyListHeadersAdapter<H, C> extends AbstractHeaderArrayAdapter<H, C> {

    public SimpleStickyListHeadersAdapter(final Context context) {
        super(context);
    }

    public SimpleStickyListHeadersAdapter(final Context context, final ListMultimap<H, C> map) {
        super(context, map);
    }

    protected abstract String getHeaderString(H item);

    protected abstract String getChildString(C item);

    @Override
    protected int getHeaderViewResourceId() {
        return R.layout.adapter_simple_header_list;
    }

    @Override
    protected int getChildViewResourceId() {
        return R.layout.adapter_simple_list;
    }

    @Override
    protected void setHeaderView(final View view, final int position, H item, final ViewGroup parent) {
        ((TextView) view.findViewById(R.id.text)).setText(getHeaderString(item));
    }

    @Override
    protected void setChildView(final View view, final int position, C item, final ViewGroup parent) {
        ((TextView) view.findViewById(R.id.text)).setText(getChildString(item));
    }
}
