package com.chuross.weathernews.ui;

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

public class Activity extends RoboFragmentActivity {

    private List<Future<?>> futures = Lists.newArrayList();
    private AndroidDeferredManager deferredManager = new AndroidDeferredManager();

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

    @Override
    protected void onDestroy() {
        cancelAllFutures();
        super.onDestroy();
    }
}
