package com.meister.sampleretrofitapp.dagger;

import com.meister.sampleretrofitapp.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * ApplicationComponent
 * Created by markmeister on 10/17/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
}
