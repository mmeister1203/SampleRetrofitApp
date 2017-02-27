package com.meister.sampleretrofitapp.data.dagger;

import com.meister.sampleretrofitapp.BuildConfig;
import com.meister.sampleretrofitapp.data.retrofit.ImgurApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * DataModule
 * Created by markmeister on 10/17/16.
 */
@Module
public class DataModule {

    private static final String BASE_URL = "https://api.imgur.com/3/";

    public static final String IMGUR_CLIENT_ID = "32c86510f6dfe12";

    @Singleton
    @Provides
    ImgurApi provideApi() {
        final OkHttpClient.Builder okClient = new OkHttpClient.Builder();
        okClient.readTimeout(10, TimeUnit.SECONDS);
        okClient.writeTimeout(10, TimeUnit.SECONDS);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor iLog = new HttpLoggingInterceptor();
            iLog.setLevel(HttpLoggingInterceptor.Level.BODY);

            okClient.addInterceptor(iLog);
        }

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okClient.build())
                .build().create(ImgurApi.class);
    }
}
