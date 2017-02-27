package com.meister.sampleretrofitapp.presentation.presenter;

import com.meister.sampleretrofitapp.data.retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.data.retrofit.Models.GalleryModelGetResponse;
import com.meister.sampleretrofitapp.domain.usecase.ImgurCreateAlbumUseCase;
import com.meister.sampleretrofitapp.domain.usecase.ImgurDeleteAlbumUseCase;
import com.meister.sampleretrofitapp.domain.usecase.ImgurGalleryUseCase;
import com.meister.sampleretrofitapp.domain.view.ImgurSampleView;
import com.meister.sampleretrofitapp.presentation.subscriber.BaseSubscriber;

import javax.inject.Inject;

/**
 * ImgurSamplePresenter
 * Created by markmeister on 2/27/17.
 */

public class ImgurSamplePresenter implements Presenter<ImgurSampleView> {

    private ImgurSampleView imgurSampleView;
    private ImgurGalleryUseCase imgurGalleryUseCase;
    private ImgurCreateAlbumUseCase imgurCreateAlbumUseCase;
    private ImgurDeleteAlbumUseCase imgurDeleteAlbumUseCase;

    @Inject
    public ImgurSamplePresenter(ImgurGalleryUseCase imgurGalleryUseCase, ImgurCreateAlbumUseCase imgurCreateAlbumUseCase, ImgurDeleteAlbumUseCase imgurDeleteAlbumUseCase) {
        this.imgurGalleryUseCase = imgurGalleryUseCase;
        this.imgurCreateAlbumUseCase = imgurCreateAlbumUseCase;
        this.imgurDeleteAlbumUseCase = imgurDeleteAlbumUseCase;
    }

    @Override
    public void attachView(ImgurSampleView view) {
        this.imgurSampleView = view;
    }

    @Override
    public void destroy() {
        this.imgurSampleView = null;
        this.imgurGalleryUseCase.unsubscribe();
        this.imgurCreateAlbumUseCase.unsubscribe();
        this.imgurDeleteAlbumUseCase.unsubscribe();
    }

    public void getImgurGallery() {
        this.imgurGalleryUseCase.execute(new BaseSubscriber<GalleryModelGetResponse>() {
            @Override
            public void onError(Throwable e) {
                if (imgurSampleView != null) {
                    imgurSampleView.onGalleryFetchError();
                }
            }

            @Override
            public void onNext(GalleryModelGetResponse galleryModelGetResponse) {
                if (imgurSampleView != null) {
                    imgurSampleView.onGalleryReceived(galleryModelGetResponse);
                }
            }
        });
    }

    public void createAlbum(CreateAlbumBody createAlbumBody) {
        this.imgurCreateAlbumUseCase
                .setCreateAlbumBody(createAlbumBody)
                .execute(new BaseSubscriber<BasicPostResponse>() {
                    @Override
                    public void onError(Throwable e) {
                        if (imgurSampleView != null) {
                            imgurSampleView.onAlbumCreationError();
                        }
                    }

                    @Override
                    public void onNext(BasicPostResponse basicPostResponse) {
                        if (imgurSampleView != null) {
                            imgurSampleView.onAlbumCreated(basicPostResponse);
                        }
                    }
                });
    }

    public void deleteAlbum(String albumId) {
        this.imgurDeleteAlbumUseCase.setAlbumId(albumId).execute(new BaseSubscriber<BasePostResponse>(){
            @Override
            public void onError(Throwable e) {
                if (imgurSampleView != null) {
                    imgurSampleView.onAlbumDeletionError();
                }
            }

            @Override
            public void onNext(BasePostResponse basePostResponse) {
                if (imgurSampleView != null) {
                    imgurSampleView.onAlbumDeleted(basePostResponse);
                }
            }
        });
    }
}
