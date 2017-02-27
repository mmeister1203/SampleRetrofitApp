package com.meister.sampleretrofitapp.presentation.subscriber;

import rx.Subscriber;

/**
 * BaseSubscriber
 * Created by markmeister on 2/27/17.
 */

public class BaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {}

    @Override
    public void onError(Throwable e) {}

    @Override
    public void onNext(T t) {}
}
