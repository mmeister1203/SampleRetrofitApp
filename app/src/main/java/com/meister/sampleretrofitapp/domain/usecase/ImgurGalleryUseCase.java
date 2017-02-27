package com.meister.sampleretrofitapp.domain.usecase;

import com.meister.sampleretrofitapp.data.repo.Repository;
import com.meister.sampleretrofitapp.data.retrofit.Models.GalleryModelGetResponse;

import javax.inject.Inject;

import rx.Observable;

/**
 * ImgurGalleryUseCase
 * Created by markmeister on 2/27/17.
 */

public class ImgurGalleryUseCase extends SessionUseCase<GalleryModelGetResponse> {

    @Inject
    public ImgurGalleryUseCase(Repository sessionRepository) {
        super(sessionRepository);
    }

    @Override
    protected Observable<GalleryModelGetResponse> buildUseCaseObservable() {
        return sessionRepository.getImgurGallery();
    }
}
