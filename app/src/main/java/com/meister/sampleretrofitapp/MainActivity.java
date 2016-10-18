package com.meister.sampleretrofitapp;

import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.meister.sampleretrofitapp.Handlers.ResultsHandler;
import com.meister.sampleretrofitapp.Retrofit.ImgurApi;
import com.meister.sampleretrofitapp.Retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.Retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.Retrofit.Models.CreateAlbumBody;
import com.meister.sampleretrofitapp.Retrofit.Models.GalleryModelGetResponse;
import com.meister.sampleretrofitapp.Utils.MessageCreator;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * MainActivity.
 * Created by mark.meister on 8/3/15.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        ResultsHandler.ResultsHandlerListener {

    public static final int LeftImage = 0;
    public static final int RightImage = 1;
    public static final int CreateAlbum = 2;
    public static final int DeleteAlbum = 3;
    public static final int DisplayProgress = 4;

    @IntDef({LeftImage, RightImage, CreateAlbum, DeleteAlbum, DisplayProgress})
    public @interface UpdateType {}

    private ImageView mLeftView;
    private ImageView mRightView;
    private ResultsHandler mHandler;
    private String albumDeleteHash;

    private GalleryModelGetResponse.MyGallery mLeft;
    private GalleryModelGetResponse.MyGallery mRight;
    private ProgressBar mLeftProgress;
    private ProgressBar mRightProgress;

    private TextView mUrlTextView;
    private TextView mDeleteTextView;

    @Inject
    public ImgurApi mImgurApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mHandler = new ResultsHandler(this);

        ((MyApplication) getApplication()).getApplicationComponent().inject(this);

        mLeftView = (ImageView) findViewById(R.id.left_photo);
        mRightView = (ImageView) findViewById(R.id.right_photo);
        mLeftProgress = (ProgressBar) findViewById(R.id.left_progress);
        mRightProgress = (ProgressBar) findViewById(R.id.right_progress);
        mUrlTextView = (TextView) findViewById(R.id.url_for_album);
        mDeleteTextView = (TextView) findViewById(R.id.result_of_delete);

        findViewById(R.id.grab_photos).setOnClickListener(this);
        findViewById(R.id.create_album).setOnClickListener(this);
        findViewById(R.id.delete_album_btn).setOnClickListener(this);
    }

    // ***************** View.OnClickListener Interface

    @Override
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.grab_photos:
                GrabPhotos();
                break;

            case R.id.delete_album_btn:
                DeleteAlbum();
                break;

            case R.id.create_album:
                CreateAlbum();
                break;
        }
    }

    // ***************** ResultsHandler.ResultsHandlerListener Interface

    @Override
    public void handleUpdateResult(@UpdateType int type, Bundle data) {
        switch(type) {
            case LeftImage:
                updateImage(mLeftView, mLeftProgress, data.getString(MessageCreator.IMAGE_URL_KEY));
                break;

            case RightImage:
                updateImage(mRightView, mRightProgress, data.getString(MessageCreator.IMAGE_URL_KEY));
                break;

            case CreateAlbum:
                final String id = data.getString(MessageCreator.ALBUM_ID_KEY);
                mUrlTextView.setText(String.format("%s http://imgur.com/a/%s", getString(R.string.empty_default_url_text), id));
                break;

            case DeleteAlbum:
                final boolean result = data.getBoolean(MessageCreator.DELETE_RESULT_KEY);
                mDeleteTextView.setText(String.format(getString(R.string.delete_album_result_text) + " %s", (result ? " Success! May take a min for website to update." : " Failed")));
                break;

            case DisplayProgress:
                showProgressBars();
                break;
        }
    }

    // ******************* Private Methods

    private void updateImage(final ImageView imageView, final ProgressBar progressBar, String url) {
        Glide
            .with(imageView
            .getContext())
            .load(url)
            .fitCenter()
            .into(new SimpleTarget<GlideDrawable>() {
                @Override
                public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                    progressBar.setVisibility(View.GONE);
                    imageView.setImageDrawable(resource);
                }
            });
    }



    private void showProgressBars() {
        mLeftProgress.setVisibility(View.VISIBLE);
        mRightProgress.setVisibility(View.VISIBLE);
    }

    // Sets our left MyGallery model to be an image and not an album of images.
    private void getLeftGalleryModel(GalleryModelGetResponse imgurGallery) {
        for(GalleryModelGetResponse.MyGallery r : imgurGallery.getData()) {
            if (!r.is_album()) {
                mLeft = r;
            }
        }
    }

    // Sets our right MyGallery model to be an image and not an album of images.
    private void getRightGalleryModel(GalleryModelGetResponse imgurGallery) {
        for(GalleryModelGetResponse.MyGallery r : imgurGallery.getData()) {
            if (!r.is_album() && mLeft != null && !mLeft.getId().equalsIgnoreCase(r.getId())) {
                mRight = r;
            }
        }
    }

    private void CreateAlbum() {
        if (mLeft == null || mRight == null) {
            return;
        }

        final String[] images = new String[2];
        images[0] = mLeft.getId();
        images[1] = mRight.getId();

        new Thread(() -> {
            // Perform our POST request, which creates an Imgur album using our two saved images.
            BasicPostResponse response = null;

            try {
                final CreateAlbumBody body = CreateAlbumBody.newInstance();
                body.setTitle("Our new album!");
                body.setIds(images);

                response = mImgurApi.create(body).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (response == null) {
                return;
            }

            // Since we're not logged in, we need to save the album deletehash so we
            // can modify the album in the future.
            albumDeleteHash = response.getData().getDeletehash();

            // Send handler message to update our url so that a user can navigate to it.
            mHandler.sendMessage(MessageCreator.createAlbumMessage(response.getData().getId()));
        }).start();
    }

    private void DeleteAlbum() {
        if (albumDeleteHash == null) {
            return;
        }

        mImgurApi.deleteGallery(albumDeleteHash).enqueue(new Callback<BasePostResponse>() {
            @Override
            public void onResponse(Call<BasePostResponse> call, Response<BasePostResponse> response) {
                if (response == null || response.body() == null) {
                    return;
                }

                // Send handler message to update UI with the result status of our delete operation.
                mHandler.sendMessage(MessageCreator.createDeleteMessage(response.isSuccessful()));
            }

            @Override
            public void onFailure(Call<BasePostResponse> call, Throwable t) {
                t.printStackTrace();
                mHandler.sendMessage(MessageCreator.createDeleteMessage(false));
            }
        });
    }

    private void GrabPhotos() {
        showProgressBars();

        mImgurApi.getImgurGallery().enqueue(new Callback<GalleryModelGetResponse>() {
            @Override
            public void onResponse(Call<GalleryModelGetResponse> call, Response<GalleryModelGetResponse> response) {
                if (response == null || response.body() == null) {
                    return;
                }

                // We store members for two gallery image objects (MyGallery object type)
                getLeftGalleryModel(response.body());
                getRightGalleryModel(response.body());

                // Send message to handler that we have our images and should load them to our ImageViews.
                mHandler.sendMessage(MessageCreator.createLeftImageMessage(mLeft.getLink()));
                mHandler.sendMessage(MessageCreator.createRightImageMessage(mRight.getLink()));
            }

            @Override
            public void onFailure(Call<GalleryModelGetResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
