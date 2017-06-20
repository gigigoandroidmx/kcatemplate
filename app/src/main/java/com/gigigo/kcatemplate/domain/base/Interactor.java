package com.gigigo.kcatemplate.domain.base;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Omar on 6/20/17.
 */

public abstract class Interactor<T> {

    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler uiThread;

    public Interactor(Scheduler executorThread, Scheduler uiThread) {
        compositeDisposable = new CompositeDisposable();
        this.executorThread = executorThread;
        this.uiThread = uiThread;
    }

    public void execute(DisposableObserver<T> disposableObserver) {
        if (disposableObserver == null) {
            throw new IllegalArgumentException("disposableObserver must not be null");
        }

        final Observable<T> observable = createObservable()
                .subscribeOn(executorThread).observeOn(uiThread);
        DisposableObserver observer = observable.subscribeWith(disposableObserver);
        compositeDisposable.add(observer);
    }

    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

    protected abstract Observable<T> createObservable();
}
