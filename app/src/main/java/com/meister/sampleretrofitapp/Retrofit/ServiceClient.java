package com.meister.sampleretrofitapp.Retrofit;

import com.meister.sampleretrofitapp.BuildConfig;

import java.util.HashMap;
import java.util.Map;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * ServiceClient. We create a singleton for our service client because creating it each time we
 * would like to make a request is too cumbersome.
 * Created by mark.meister on 8/3/15.
 */
public class ServiceClient {
    private static ServiceClient instance;

    public static final String BASE_URL = "https://api.imgur.com/3/";
    public static final String IMGUR_CLIENT_ID = "e3284cee56b7b4b";

    private Retrofit mRestAdapter;
    private Map<String, Object> mClients = new HashMap<>();

    public static ServiceClient getInstance() {
        if (null == instance) {
            instance = new ServiceClient();
        }
        return instance;
    }

    // Configure our Retrofit object.
    public void configureRestAdapter() {

        if (BuildConfig.DEBUG) {
            mRestAdapter = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else {
            mRestAdapter = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getClient(Class<T> clazz) {
        if (mRestAdapter == null) {
            return null;
        }
        T client;
        if ((client = (T) mClients.get(clazz.getCanonicalName())) != null) {
            return client;
        }
        client = mRestAdapter.create(clazz);

        mClients.put(clazz.getCanonicalName(), client);
        return client;
    }
}
