package com.meister.sampleretrofitapp.domain.usecase;

import com.meister.sampleretrofitapp.data.repo.Repository;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.CreateAlbumBody;

import javax.inject.Inject;

import rx.Observable;

/**
 * ImgurCreateAlbumUseCase
 * Created by markmeister on 2/27/17.
 */

public class ImgurCreateAlbumUseCase extends SessionUseCase<BasicPostResponse> {

    private CreateAlbumBody createAlbumBody;

    @Inject
    public ImgurCreateAlbumUseCase(Repository sessionRepository) {
        super(sessionRepository);
    }

    @Override
    protected Observable<BasicPostResponse> buildUseCaseObservable() {
        return sessionRepository.create(createAlbumBody);
    }

    public ImgurCreateAlbumUseCase setCreateAlbumBody(CreateAlbumBody createAlbumBody) {
        this.createAlbumBody = createAlbumBody;
        return this;
    }
}
