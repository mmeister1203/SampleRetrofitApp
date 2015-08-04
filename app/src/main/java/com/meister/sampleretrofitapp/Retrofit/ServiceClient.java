package com.meister.sampleretrofitapp.Retrofit;

import android.util.Log;

import com.meister.sampleretrofitapp.BuildConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * ServiceClient. We create a singleton for our service client because creating it each time we
 * would like to make a request is too cumbersome.
 * Created by mark.meister on 8/3/15.
 */
public class ServiceClient {
    private static ServiceClient instance;

    private static final String BASE_URL = "https://api.imgur.com/3/";
    private static final String IMGUR_CLIENT_ID = "e3284cee56b7b4b";

    private RestAdapter mRestAdapter;
    private Map<String, Object> mClients = new HashMap<>();

    public static ServiceClient getInstance() {
        if (null == instance) {
            instance = new ServiceClient();
        }
        return instance;
    }

    // Configure our RestAdapter. Basically the same except for logging.
    public void configureRestAdapter() {
        if (BuildConfig.DEBUG) {
            mRestAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint(BASE_URL)
                    .setRequestInterceptor(new NewApiInterceptor())
                    .setLog(LOGGER)
                    .build();
        } else {
            mRestAdapter = new RestAdapter.Builder()
                    .setEndpoint(BASE_URL)
                    .setRequestInterceptor(new NewApiInterceptor())
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

    // Interceptor allows us to add headers to our request.
    private static final class NewApiInterceptor implements RequestInterceptor {
        private NewApiInterceptor() {}

        @Override
        public void intercept(RequestFacade request) {
            request.addHeader("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
        }
    }

    private static final RestAdapter.Log LOGGER = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            final List<String> messages = splitEqually(message, 256);

            for (String s : messages) {
                Log.i("RETROFIT", s);
            }
        }

        private List<String> splitEqually(String text, int size) {
            final List<String> ret = new ArrayList<>((text.length() + size - 1) / size);

            for (int start = 0; start < text.length(); start += size) {
                ret.add(text.substring(start, Math.min(text.length(), start + size)));
            }
            return ret;
        }
    };
}
