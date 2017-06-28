package com.gigigo.kcatemplate.presentation;

import android.app.Application;

import com.gigigo.kcatemplate.BuildConfig;
import com.gigigo.kcatemplate.di.components.DaggerMainComponent;
import com.gigigo.kcatemplate.di.components.MainComponent;
import com.gigigo.kcatemplate.di.modules.MainModule;
import com.gigigo.kcatemplate.presentation.utils.Connectivity;
import com.gigigo.kcatemplate.presentation.utils.RequestInterceptor;
import com.gigigo.kretrofitmanager.CallAdapterFactory;
import com.gigigo.kretrofitmanager.ServiceClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Interfaz base para la vista
 *
 * @author [nombre] - 4/18/2017
 * @version 1.0.0
 * @since 1.0.0
 */
public class App extends Application {

    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
        initializeInjector();
    }

    private void initialize() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();

        LoggingInterceptor loggerInterceptor = new LoggingInterceptor.Builder()
                .loggable(BuildConfig.DEBUG)
                .setLevel(Level.BASIC)
                .log(Platform.INFO)
                .request("Request")
                .response("Response")
                .build();

        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .create();

        RequestInterceptor requestInterceptor = new RequestInterceptor(
                new Connectivity(this));

        ServiceClient.init()
                .setLoggingInterceptor(loggerInterceptor)
                .setCallAdapterFactory(new CallAdapterFactory())
//                .setCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .setConnectivityInterceptor(requestInterceptor)
                .addEndpoint(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create(gson));
    }

    private void initializeInjector() {
        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
