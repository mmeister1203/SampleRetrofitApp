package com.meister.sampleretrofitapp.data.dagger;

import com.meister.sampleretrofitapp.data.repo.Repository;
import com.meister.sampleretrofitapp.data.repo.SessionRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ApplicationModule
 * Created by markmeister on 10/17/16.
 */
@Module(includes = DataModule.class)
public class ApplicationModule {

    @Singleton
    @Provides
    public Repository provideSessionRepository(SessionRepository sessionRepository) {
        return sessionRepository;
    }
}
