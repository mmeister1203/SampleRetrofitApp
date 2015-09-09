package com.meister.sampleretrofitapp.Retrofit.Requests;

import com.meister.sampleretrofitapp.Retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.Retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.Retrofit.ServiceClient;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Creates an album with specified parameters or generic.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class CreateAlbumPostRequest {

    public interface CreateAlbumClient {
        @Headers("Authorization: Client-ID " + ServiceClient.IMGUR_CLIENT_ID)
        @POST("album")
        Call<BasicPostResponse> create(@Body CreateAlbumBody request);
    }

    /**
     * Creates anonymous album.
     */
    public static Call<BasicPostResponse> createAlbum() {
        return ServiceClient.getInstance().getClient(CreateAlbumClient.class).create(CreateAlbumBody.newInstance());
    }

    /**
     * Creates album with specified title.
     */
    public static Call<BasicPostResponse> createAlbum(String title) {
        final CreateAlbumClient client = ServiceClient.getInstance().getClient(CreateAlbumClient.class);

        final CreateAlbumBody body = CreateAlbumBody.newInstance();
        body.setTitle(title);

        return client.create(body);
    }

    /**
     * Creates album with specified title & images.
     */
    public static Call<BasicPostResponse> createAlbum(String title, String[] imageIds) {
        final CreateAlbumClient client = ServiceClient.getInstance().getClient(CreateAlbumClient.class);

        final CreateAlbumBody body = CreateAlbumBody.newInstance();
        body.setTitle(title);
        body.setIds(imageIds);

        return client.create(body);
    }
}
