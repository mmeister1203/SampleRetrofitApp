package com.meister.sampleretrofitapp;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.meister.sampleretrofitapp.Handlers.ResultsHandler;
import com.meister.sampleretrofitapp.Retrofit.Models.BasePostResponse;
import com.meister.sampleretrofitapp.Retrofit.Models.BasicPostResponse;
import com.meister.sampleretrofitapp.Retrofit.Models.GalleryModelGetResponse;
import com.meister.sampleretrofitapp.Retrofit.Requests.CreateAlbumPostRequest;
import com.meister.sampleretrofitapp.Retrofit.Requests.DeleteAlbumDeleteRequest;
import com.meister.sampleretrofitapp.Retrofit.Requests.GalleryGetRequest;
import com.meister.sampleretrofitapp.Retrofit.ServiceClient;
import com.meister.sampleretrofitapp.Utils.BitmapDownloadTask;
import com.meister.sampleretrofitapp.Utils.MessageCreator;

/**
 * MainActivity.
 * Created by mark.meister on 8/3/15.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public enum UpdateType {
        LeftImage, RightImage, CreateAlbum, DeleteAlbum, DisplayProgress
    }

    private ImageView mLeftView;
    private ImageView mRightView;
    private ResultsHandler mHandler;
    private String albumDeleteHash;

    private GalleryModelGetResponse.MyGallery mLeft;
    private GalleryModelGetResponse.MyGallery mRight;
    private ProgressBar mLeftProgress;
    private ProgressBar mRightProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mHandler = new ResultsHandler(this);

        // Sets up our RestAdapter.
        ServiceClient.getInstance().configureRestAdapter();

        mLeftView = (ImageView) findViewById(R.id.left_photo);
        mRightView = (ImageView) findViewById(R.id.right_photo);
        mLeftProgress = (ProgressBar) findViewById(R.id.left_progress);
        mRightProgress = (ProgressBar) findViewById(R.id.right_progress);

        findViewById(R.id.grab_photos).setOnClickListener(this);
        findViewById(R.id.create_album).setOnClickListener(this);
        findViewById(R.id.delete_album_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(final View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (view.getId()) {
                    case R.id.grab_photos:
                        // Send a message to our handler to display our progress bars.
                        mHandler.sendEmptyMessage(UpdateType.DisplayProgress.ordinal());

                        // Perform our GET request to grab gallery images.
                        final GalleryModelGetResponse imgurGallery = GalleryGetRequest.getGallery();
                        if (imgurGallery == null) {
                            return;
                        }

                        // We store members for two gallery image objects (MyGallery object type)
                        getLeftGalleryModel(imgurGallery);
                        getRightGalleryModel(imgurGallery);

                        // Send message to handler that we have our images and should load them to our ImageViews.
                        mHandler.sendMessage(MessageCreator.createLeftImageMessage(mLeft.getLink()));
                        mHandler.sendMessage(MessageCreator.createRightImageMessage(mRight.getLink()));
                        break;

                    case R.id.create_album:
                        if (mLeft == null || mRight == null) {
                            return;
                        }

                        final String[] images = new String[2];
                        images[0] = mLeft.getId();
                        images[1] = mRight.getId();

                        // Perform our POST request, which creates an Imgur album using our two saved images.
                        final BasicPostResponse response = CreateAlbumPostRequest.createAlbum("Our new album!", images);
                        if (response == null) {
                            return;
                        }

                        // Since we're not logged in, we need to save the album deletehash so we
                        // can modify the album in the future.
                        albumDeleteHash = response.getData().getDeletehash();

                        // Send handler message to update our url so that a user can navigate to it.
                        mHandler.sendMessage(MessageCreator.createAlbumMessage(response.getData().getId()));
                        break;

                    case R.id.delete_album_btn:
                        if (albumDeleteHash == null) {
                            return;
                        }

                        // Perform our DELETE request to remove our previously created album from
                        // imgur.com
                        final BasePostResponse deleteResponse = DeleteAlbumDeleteRequest.deleteGallery(albumDeleteHash);
                        if (deleteResponse == null) {
                            return;
                        }

                        // Send handler message to update UI with the result status of our delete operation.
                        mHandler.sendMessage(MessageCreator.createDeleteMessage(deleteResponse.isSuccess()));
                        break;
                }
            }
        }).start();
    }

    public void updateLeftImage(String url) {
        updateImage(mLeftView, mLeftProgress, url);
    }

    public void updateRightImage(String url) {
        updateImage(mRightView, mRightProgress, url);
    }

    public static void updateImage(final ImageView imageView, final ProgressBar progressBar, String url) {
        new BitmapDownloadTask(new BitmapDownloadTask.RetrieveBitmap() {
            @Override
            public void onBitmapRetrieved(Bitmap bitmap) {
                if (bitmap != null) {
                    progressBar.setVisibility(View.GONE);
                    imageView.setImageBitmap(bitmap);
                }
            }
        }, url).execute();
    }

    public void updateDeleteAlbumText(boolean result) {
        final TextView deleteText = (TextView) findViewById(R.id.result_of_delete);
        deleteText.setText(getString(R.string.delete_album_result_text) + (result ? " Success! May take a min for website to update." : " Failed"));
    }

    public void updateAlbumUrl(String id) {
        final TextView urlText = (TextView) findViewById(R.id.url_for_album);
        urlText.setText(getString(R.string.empty_default_url_text) + " http://imgur.com/a/" + id);
    }

    public void showProgressBars() {
        mLeftProgress.setVisibility(View.VISIBLE);
        mRightProgress.setVisibility(View.VISIBLE);
    }

    // Sets our left MyGallery model to be an image and not an album of images.
    public void getLeftGalleryModel(GalleryModelGetResponse imgurGallery) {
        for(GalleryModelGetResponse.MyGallery r : imgurGallery.getData()) {
            if (!r.is_album()) {
                mLeft = r;
            }
        }
    }

    // Sets our right MyGallery model to be an image and not an album of images.
    public void getRightGalleryModel(GalleryModelGetResponse imgurGallery) {
        for(GalleryModelGetResponse.MyGallery r : imgurGallery.getData()) {
            if (!r.is_album() && mLeft != null && !mLeft.getId().equalsIgnoreCase(r.getId())) {
                mRight = r;
            }
        }
    }
}
