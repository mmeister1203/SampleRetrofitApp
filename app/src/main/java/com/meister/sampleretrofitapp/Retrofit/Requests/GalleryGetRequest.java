package com.meister.sampleretrofitapp.Retrofit.Requests;

import com.meister.sampleretrofitapp.Retrofit.Models.GalleryModelGetResponse;
import com.meister.sampleretrofitapp.Retrofit.ServiceClient;

import retrofit.http.GET;

/**
 * Retrieves the current imgur gallery image data.
 * Created by mark.meister on 8/3/15.
 */
public class GalleryGetRequest {

    public interface GalleryClient {
        @GET("/gallery")
        GalleryModelGetResponse create();
    }

    public static GalleryModelGetResponse getGallery() {
        final GalleryClient client = ServiceClient.getInstance().getClient(GalleryClient.class);

        try {
            return client.create();
        } catch (Exception e) {
            return null;
        }
    }
}
