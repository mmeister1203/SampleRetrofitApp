package com.meister.sampleretrofitapp.data.repo;

import com.meister.sampleretrofitapp.data.retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.data.retrofit.Models.GalleryModelGetResponse;

import rx.Observable;

/**
 * Repository
 * Created by markmeister on 2/27/17.
 */

public interface Repository {
    Observable<GalleryModelGetResponse> getImgurGallery();
    Observable<BasicPostResponse> create(CreateAlbumBody request);
    Observable<BasePostResponse> deleteGallery(String albumId);
}
