package com.meister.sampleretrofitapp.Retrofit.Requests;

import com.meister.sampleretrofitapp.Retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.Retrofit.ServiceClient;

import retrofit.http.DELETE;
import retrofit.http.Path;

/**
 * Request used to delete an album from imgur.
 * Created by mark.meister on 8/3/15.
 */
public class DeleteAlbumDeleteRequest {
    public interface GalleryClient {
        @DELETE("/album/{album}")
        BasePostResponse create(@Path("album") String album);
    }

    public static BasePostResponse deleteGallery(String albumId) {
        final GalleryClient client = ServiceClient.getInstance().getClient(GalleryClient.class);

        try {
            return client.create(albumId);
        } catch (Exception e) {
            return null;
        }
    }
}
