package com.meister.sampleretrofitapp.domain.usecase;

import com.meister.sampleretrofitapp.data.repo.Repository;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasePostResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * ImgurDeleteAlbumUseCase
 * Created by markmeister on 2/27/17.
 */

public class ImgurDeleteAlbumUseCase extends SessionUseCase<BasePostResponse> {

    private String albumId;

    @Inject
    public ImgurDeleteAlbumUseCase(Repository sessionRepository) {
        super(sessionRepository);
    }

    @Override
    protected Observable<BasePostResponse> buildUseCaseObservable() {
        return sessionRepository.deleteGallery(albumId);
    }

    public ImgurDeleteAlbumUseCase setAlbumId(String albumId) {
        this.albumId = albumId;
        return this;
    }
}
