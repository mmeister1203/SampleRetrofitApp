package com.meister.sampleretrofitapp.domain.usecase;

import com.meister.sampleretrofitapp.data.repo.Repository;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * SessionUseCase
 * Created by markmeister on 2/27/17.
 */

public abstract class SessionUseCase<T> {
    final Repository sessionRepository;

    private Subscription subscription = Subscriptions.empty();

    SessionUseCase(Repository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void execute(Subscriber<T> subscriber) {
        subscription = buildUseCaseObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.newThread())
                .subscribe(subscriber);
    }

    public void execute(Action1<T> subscriber) {
        subscription = buildUseCaseObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.newThread())
                .subscribe(subscriber);
    }

    public void unsubscribe() {
        if (subscription != null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    protected abstract Observable<T> buildUseCaseObservable();
}
