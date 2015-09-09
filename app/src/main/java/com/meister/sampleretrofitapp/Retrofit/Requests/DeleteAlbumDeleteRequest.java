package com.meister.sampleretrofitapp.Retrofit.Requests;

import com.meister.sampleretrofitapp.Retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.Retrofit.ServiceClient;

import retrofit.Call;
import retrofit.http.DELETE;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Request used to delete an album from imgur.
 * Created by mark.meister on 8/3/15.
 */
public class DeleteAlbumDeleteRequest {

    public interface GalleryService {
        @Headers("Authorization: Client-ID " + ServiceClient.IMGUR_CLIENT_ID)
        @DELETE("album/{album}")
        Call<BasePostResponse> create(@Path("album") String album);
    }

    public static Call<BasePostResponse> deleteGallery(String albumId) {
        return ServiceClient.getInstance().getClient(GalleryService.class).create(albumId);
    }
}
