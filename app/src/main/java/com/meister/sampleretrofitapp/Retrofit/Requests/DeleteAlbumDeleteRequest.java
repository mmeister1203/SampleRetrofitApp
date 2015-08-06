package com.meister.sampleretrofitapp.Retrofit.Requests;

import com.meister.sampleretrofitapp.Retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.Retrofit.ServiceClient;

import retrofit.Callback;
import retrofit.http.DELETE;
import retrofit.http.Path;

/**
 * Request used to delete an album from imgur.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class DeleteAlbumDeleteRequest {

    public interface GalleryClient {
        @DELETE("/album/{album}")
        BasePostResponse create(@Path("album") String album);

        @DELETE("/album/{album}")
        void createAsync(@Path("album") String album, Callback<BasePostResponse> callback);
    }

    public static BasePostResponse deleteGallery(String albumId) {
        final GalleryClient client = ServiceClient.getInstance().getClient(GalleryClient.class);

        try {
            return client.create(albumId);
        } catch (Exception e) {
            return null;
        }
    }

    public static void deleteGalleryAsync(String albumId, Callback<BasePostResponse> callback) {
        ServiceClient.getInstance().getClient(GalleryClient.class).createAsync(albumId, callback);
    }
}
