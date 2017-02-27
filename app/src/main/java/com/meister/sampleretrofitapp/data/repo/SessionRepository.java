package com.meister.sampleretrofitapp.data.repo;

import com.meister.sampleretrofitapp.data.retrofit.ImgurApi;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.data.retrofit.Models.GalleryModelGetResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * SessionRepository
 * Created by markmeister on 2/27/17.
 */

public class SessionRepository implements Repository {

    private ImgurApi imgurApi;

    @Inject
    public SessionRepository(ImgurApi imgurApi) {
        this.imgurApi = imgurApi;
    }

    @Override
    public Observable<GalleryModelGetResponse> getImgurGallery() {
        return imgurApi.getImgurGallery();
    }

    @Override
    public Observable<BasicPostResponse> create(CreateAlbumBody request) {
        return imgurApi.create(request);
    }

    @Override
    public Observable<BasePostResponse> deleteGallery(String albumId) {
        return imgurApi.deleteGallery(albumId);
    }
}
