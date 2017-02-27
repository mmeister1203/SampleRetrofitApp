package com.meister.sampleretrofitapp.domain.view;

import com.meister.sampleretrofitapp.data.retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.GalleryModelGetResponse;

/**
 * ImgurSampleView
 * Created by markmeister on 2/27/17.
 */

public interface ImgurSampleView extends BaseView {
    void onGalleryReceived(GalleryModelGetResponse response);
    void onGalleryFetchError();

    void onAlbumCreated(BasicPostResponse basicPostResponse);
    void onAlbumCreationError();

    void onAlbumDeleted(BasePostResponse basePostResponse);
    void onAlbumDeletionError();
}
