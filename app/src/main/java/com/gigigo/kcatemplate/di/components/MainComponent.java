package com.gigigo.kcatemplate.di.components;

import android.content.Context;

import com.gigigo.kcatemplate.di.modules.MainModule;
import com.gigigo.kcatemplate.presentation.ui.fragment.HomeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Omar on 6/26/17.
 */

@Singleton @Component(modules = MainModule.class)
public interface MainComponent {

    void inject(HomeFragment homeFragment);

    Context context();
}
