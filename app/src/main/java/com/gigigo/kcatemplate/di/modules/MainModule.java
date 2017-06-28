package com.gigigo.kcatemplate.di.modules;

import android.content.Context;

import com.gigigo.kcatemplate.data.repository.UsersRepository;
import com.gigigo.kcatemplate.data.repository.UsersRepositoryImpl;
import com.gigigo.kcatemplate.presentation.App;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Omar on 6/26/17.
 */

@Module
public class MainModule {
    private final App app;

    public MainModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return app;
    }

    @Provides
    @Singleton
    UsersRepository privateRepository(UsersRepositoryImpl usersRepository) {
        return usersRepository;
    }

    @Provides
    @Named("executor_thread")
    Scheduler provideExecutorThread() {
        return Schedulers.io();
    }

    @Provides
    @Named("ui_thread")
    Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}
