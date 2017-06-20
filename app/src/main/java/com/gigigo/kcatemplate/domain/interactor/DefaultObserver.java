package com.gigigo.kcatemplate.domain.interactor;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Omar on 6/20/17.
 */

public abstract class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onComplete() { }

    @Override
    public void onError(@NonNull Throwable e) { }

    @Override
    public void onNext(@NonNull T t) { }
}
