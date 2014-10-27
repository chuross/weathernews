package com.chuross.weathernews.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import com.chuross.common.android.library.ui.adapter.AbstractArrayAdapter;
import com.chuross.weathernews.R;

import java.util.List;

public abstract class SimpleListAdapter<T> extends AbstractArrayAdapter<T> {

    public SimpleListAdapter(final Context context) {
        super(context);
    }

    public SimpleListAdapter(final Context context, final List<T> items) {
        super(context, items);
    }

    public SimpleListAdapter(final Context context, final T[] items) {
        super(context, items);
    }

    protected abstract String getString(T item);

    @Override
    protected int getResourceId() {
        return R.layout.adapter_simple_list;
    }

    @Override
    protected void setItemView(final int index, final T item, final ViewParent parent, final View view) {
        ((TextView) view.findViewById(R.id.text)).setText(getString(item));
    }
}
