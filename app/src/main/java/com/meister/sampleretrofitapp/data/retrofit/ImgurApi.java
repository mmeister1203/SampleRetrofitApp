package com.meister.sampleretrofitapp.data.retrofit;

import com.meister.sampleretrofitapp.data.retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.data.retrofit.Models.GalleryModelGetResponse;
import com.meister.sampleretrofitapp.data.dagger.DataModule;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * ImgurApi
 * Created by markmeister on 10/17/16.
 */

public interface ImgurApi {

    // ******************* GET

    @Headers("Authorization: Client-ID " + DataModule.IMGUR_CLIENT_ID)
    @GET("gallery/hot")
    Observable<GalleryModelGetResponse> getImgurGallery();

    // ******************** POST

    @Headers("Authorization: Client-ID " + DataModule.IMGUR_CLIENT_ID)
    @POST("album")
    Observable<BasicPostResponse> create(@Body CreateAlbumBody request);

    // ******************** DELETE

    @Headers("Authorization: Client-ID " + DataModule.IMGUR_CLIENT_ID)
    @DELETE("album/{albumId}")
    Observable<BasePostResponse> deleteGallery(@Path("albumId") String albumId);
}
