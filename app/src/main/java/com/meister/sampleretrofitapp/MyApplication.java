package com.meister.sampleretrofitapp;

import android.app.Application;

import com.meister.sampleretrofitapp.dagger.ApplicationComponent;
import com.meister.sampleretrofitapp.dagger.ApplicationModule;
import com.meister.sampleretrofitapp.dagger.DaggerApplicationComponent;
import com.meister.sampleretrofitapp.dagger.DataModule;

/**
 * MyApplication
 * Created by markmeister on 10/17/16.
 */

public class MyApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .dataModule(new DataModule())
                .applicationModule(new ApplicationModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
