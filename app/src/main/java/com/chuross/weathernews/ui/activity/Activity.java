package com.chuross.weathernews.ui.activity;

import android.content.DialogInterface;
import android.widget.Toast;
import com.chuross.common.android.library.ui.dialog.DialogFragmentCallback;
import com.chuross.common.library.util.FutureUtils;
import com.chuross.common.library.util.PromiseUtils;
import com.google.common.collect.Lists;
import org.jdeferred.AlwaysCallback;
import org.jdeferred.Promise;
import org.jdeferred.android.AndroidDeferredManager;
import org.jdeferred.android.AndroidExecutionScope;
import roboguice.activity.RoboFragmentActivity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

public class Activity extends RoboFragmentActivity implements DialogFragmentCallback {

    private List<Future<?>> futures = Lists.newArrayList();
    private AndroidDeferredManager deferredManager = new AndroidDeferredManager();
    private DialogInterface.OnClickListener positiveClickListener;
    private DialogInterface.OnClickListener neutralClickListener;
    private DialogInterface.OnClickListener negativeClickListener;

    public <D> Promise<D, Throwable, Void> execute(Executor executor, final Future<D> future, AndroidExecutionScope scope) {
        Promise<D, Throwable, Void> promise = PromiseUtils.promise(executor, future);
        return deferredManager.when(promise, scope).always(new AlwaysCallback<D, Throwable>() {
            @Override
            public void onAlways(final Promise.State state, final D result, final Throwable throwable) {
                finish(future);
            }
        });
    }

    public synchronized boolean cancel(Future<?> future) {
        if(future == null) {
            return false;
        }
        if(!future.isCancelled()) {
            FutureUtils.cancel(future, true);
        }
        return finish(future);
    }

    public synchronized boolean finish(Future<?> future) {
        if(future == null) {
            return false;
        }
        return futures.remove(future);
    }

    private synchronized void cancelAllFutures() {
        for(Future<?> future : futures) {
            cancel(future);
        }
        futures.clear();
    }

    public void showToast(String message, int length) {
        Toast.makeText(this, message, length).show();
    }

    @Override
    protected void onDestroy() {
        cancelAllFutures();
        super.onDestroy();
    }

    @Override
    public void onPositiveClick(final String s, final DialogInterface anInterface, final int i) {
        if(positiveClickListener != null) {
            positiveClickListener.onClick(anInterface, i);
        }
    }

    @Override
    public void onNeutralClick(final String s, final DialogInterface anInterface, final int i) {
        if(neutralClickListener != null) {
            neutralClickListener.onClick(anInterface, i);
        }
    }

    @Override
    public void onNegativeClick(final String s, final DialogInterface anInterface, final int i) {
        if(negativeClickListener != null) {
            negativeClickListener.onClick(anInterface, i);
        }
    }

    @Override
    public void onDismiss(final String s, final DialogInterface anInterface) {
    }

    public void setOnPositiveClickListener(DialogInterface.OnClickListener positiveClickListener) {
        this.positiveClickListener = positiveClickListener;
    }

    public void setOnNeutralClickListener(DialogInterface.OnClickListener neutralClickListener) {
        this.neutralClickListener = neutralClickListener;
    }

    public void setOnNegativeClickListener(DialogInterface.OnClickListener negativeClickListener) {
        this.negativeClickListener = negativeClickListener;
    }
}
