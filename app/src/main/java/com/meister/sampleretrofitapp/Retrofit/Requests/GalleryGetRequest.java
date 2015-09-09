package com.meister.sampleretrofitapp.Retrofit.Requests;

import android.support.annotation.NonNull;

import com.meister.sampleretrofitapp.Retrofit.Models.GalleryModelGetResponse;
import com.meister.sampleretrofitapp.Retrofit.ServiceClient;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Retrieves the current imgur gallery image data.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class GalleryGetRequest {

    public interface GalleryService {
        @Headers("Authorization: Client-ID " + ServiceClient.IMGUR_CLIENT_ID)
        @GET("gallery")
        Call<GalleryModelGetResponse> getImgurGallery();
    }

    @NonNull
    public static Call<GalleryModelGetResponse> getGalleryService() {
        return ServiceClient.getInstance().getClient(GalleryService.class).getImgurGallery();
    }
}
