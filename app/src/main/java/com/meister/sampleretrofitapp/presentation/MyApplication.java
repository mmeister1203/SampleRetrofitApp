package com.meister.sampleretrofitapp.presentation;

import android.app.Application;

import com.meister.sampleretrofitapp.data.dagger.ApplicationComponent;
import com.meister.sampleretrofitapp.data.dagger.ApplicationModule;
import com.meister.sampleretrofitapp.data.dagger.DaggerApplicationComponent;
import com.meister.sampleretrofitapp.data.dagger.DataModule;

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
