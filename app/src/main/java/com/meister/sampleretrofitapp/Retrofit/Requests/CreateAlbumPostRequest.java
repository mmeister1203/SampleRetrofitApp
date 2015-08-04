package com.meister.sampleretrofitapp.Retrofit.Requests;

import com.meister.sampleretrofitapp.Retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.Retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.Retrofit.ServiceClient;

import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Creates an album with specified parameters or generic.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class CreateAlbumPostRequest {

    public interface CreateAlbumClient {
        @POST("/album")
        BasicPostResponse create(@Body CreateAlbumBody request);
    }

    /**
     * Creates anonymous album.
     */
    public static BasicPostResponse createAlbum() {
        final CreateAlbumClient client = ServiceClient.getInstance().getClient(CreateAlbumClient.class);

        try {
            return client.create(CreateAlbumBody.newInstance());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates album with specified title.
     */
    public static BasicPostResponse createAlbum(String title) {
        final CreateAlbumClient client = ServiceClient.getInstance().getClient(CreateAlbumClient.class);

        final CreateAlbumBody body = CreateAlbumBody.newInstance();
        body.setTitle(title);

        try {
            return client.create(body);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Creates album with specified title & images.
     */
    public static BasicPostResponse createAlbum(String title, String[] imageIds) {
        final CreateAlbumClient client = ServiceClient.getInstance().getClient(CreateAlbumClient.class);

        final CreateAlbumBody body = CreateAlbumBody.newInstance();
        body.setTitle(title);
        body.setIds(imageIds);

        try {
            return client.create(body);
        } catch (Exception e) {
            return null;
        }
    }
}
