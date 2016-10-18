package com.meister.sampleretrofitapp.Retrofit;

import com.meister.sampleretrofitapp.Retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.Retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.Retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.Retrofit.Models.GalleryModelGetResponse;
import com.meister.sampleretrofitapp.dagger.DataModule;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * ImgurApi
 * Created by markmeister on 10/17/16.
 */

public interface ImgurApi {

    // ******************* GET

    @Headers("Authorization: Client-ID " + DataModule.IMGUR_CLIENT_ID)
    @GET("gallery/hot")
    Call<GalleryModelGetResponse> getImgurGallery();

    // ******************** POST

    @Headers("Authorization: Client-ID " + DataModule.IMGUR_CLIENT_ID)
    @POST("album")
    Call<BasicPostResponse> create(@Body CreateAlbumBody request);

    // ******************** DELETE

    @Headers("Authorization: Client-ID " + DataModule.IMGUR_CLIENT_ID)
    @DELETE("album/{albumId}")
    Call<BasePostResponse> deleteGallery(@Path("albumId") String albumId);
}
