package com.meister.sampleretrofitapp.presentation;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.meister.sampleretrofitapp.R;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.data.retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.data.retrofit.Models.GalleryModelGetResponse;
import com.meister.sampleretrofitapp.domain.view.ImgurSampleView;
import com.meister.sampleretrofitapp.presentation.model.MainModel;
import com.meister.sampleretrofitapp.presentation.presenter.ImgurSamplePresenter;

import javax.inject.Inject;

/**
 * MainActivity.
 * Created by mark.meister on 8/3/15.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener, ImgurSampleView {

    private ImageView leftView;
    private ImageView rightView;
    private ProgressBar leftProgress;
    private ProgressBar rightProgress;
    private TextView urlTextView;
    private TextView deleteTextView;

    private String albumDeleteHash;
    private MainModel mainModel;

    @Inject
    ImgurSamplePresenter imgurSamplePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainModel = new MainModel();

        ((MyApplication) getApplication()).getApplicationComponent().inject(this);

        leftView = (ImageView) findViewById(R.id.left_photo);
        rightView = (ImageView) findViewById(R.id.right_photo);
        leftProgress = (ProgressBar) findViewById(R.id.left_progress);
        rightProgress = (ProgressBar) findViewById(R.id.right_progress);
        urlTextView = (TextView) findViewById(R.id.url_for_album);
        deleteTextView = (TextView) findViewById(R.id.result_of_delete);

        findViewById(R.id.grab_photos).setOnClickListener(this);
        findViewById(R.id.create_album).setOnClickListener(this);
        findViewById(R.id.delete_album_btn).setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        imgurSamplePresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        imgurSamplePresenter.destroy();
    }

    // ***************** View.OnClickListener Interface

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.grab_photos:
                grabPhotos();
                break;

            case R.id.delete_album_btn:
                if (albumDeleteHash == null) {
                    return;
                }

                imgurSamplePresenter.deleteAlbum(albumDeleteHash);
                break;

            case R.id.create_album:
                if (mainModel.getLeftImageId() == null || mainModel.getRightImageId() == null) {
                    return;
                }

                final CreateAlbumBody body = CreateAlbumBody.newInstance();
                body.setTitle("Our new album!");
                body.setIds(mainModel.getImageArray());

                imgurSamplePresenter.createAlbum(body);
                break;
        }
    }

    // ********************* ImgurSampleView Implementation

    @Override
    public void onGalleryReceived(GalleryModelGetResponse response) {
        final GalleryModelGetResponse.MyGallery leftGallery = getRandomGalleryModelLink(response, null);
        mainModel.setLeftImageId(leftGallery.getId());
        leftProgress.setVisibility(View.GONE);
        Glide
                .with(leftView.getContext())
                .load(leftGallery.getLink())
                .fitCenter()
                .into(leftView);

        final GalleryModelGetResponse.MyGallery rightGallery = getRandomGalleryModelLink(response, leftGallery.getLink());
        mainModel.setRightImageId(rightGallery.getId());
        rightProgress.setVisibility(View.GONE);
        Glide
                .with(rightView.getContext())
                .load(rightGallery.getLink())
                .fitCenter()
                .into(rightView);
    }

    @Override
    public void onGalleryFetchError() {
        Snackbar.make(getWindow().getDecorView(), R.string.gallery_error_msg, Snackbar.LENGTH_SHORT).setAction(R.string.error_msg_retry, v -> {
            grabPhotos();
        }).show();
    }

    @Override
    public void onAlbumCreated(BasicPostResponse basicPostResponse) {
        albumDeleteHash = basicPostResponse.getData().getDeletehash();
        urlTextView.setText(String.format("%s http://imgur.com/a/%s", getString(R.string.empty_default_url_text), basicPostResponse.getData().getId()));
    }

    @Override
    public void onAlbumCreationError() {
        Snackbar.make(getWindow().getDecorView(), R.string.album_error_msg, Snackbar.LENGTH_SHORT).setAction(R.string.error_msg_retry, v -> {
            grabPhotos();
        }).show();
    }

    @Override
    public void onAlbumDeleted(BasePostResponse basePostResponse) {
        deleteTextView.setText(String.format(getString(R.string.delete_album_result_text) + " %s", (basePostResponse.isSuccess() ? " Success! May take a min for website to update." : " Failed")));
    }

    @Override
    public void onAlbumDeletionError() {
        Snackbar.make(getWindow().getDecorView(), R.string.delete_album_error_msg, Snackbar.LENGTH_SHORT).setAction(R.string.error_msg_retry, v -> {
            grabPhotos();
        }).show();
    }

    // ******************* Private Methods

    private GalleryModelGetResponse.MyGallery getRandomGalleryModelLink(GalleryModelGetResponse imgurGallery, String optionalLink) {
        for (GalleryModelGetResponse.MyGallery gallery : imgurGallery.getData()) {
            if (!gallery.is_album() && !gallery.getLink().equalsIgnoreCase(optionalLink) && !gallery.getLink().contains(".gif")) {
                return gallery;
            }
        }

        return new GalleryModelGetResponse().newInstance();
    }

    private void grabPhotos() {
        leftProgress.setVisibility(View.VISIBLE);
        rightProgress.setVisibility(View.VISIBLE);
        imgurSamplePresenter.getImgurGallery();
    }
}
