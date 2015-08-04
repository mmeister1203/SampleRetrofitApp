package com.meister.sampleretrofitapp.Handlers;

import android.os.Handler;
import android.os.Message;

import com.meister.sampleretrofitapp.MainActivity;
import com.meister.sampleretrofitapp.Utils.MessageCreator;

import java.lang.ref.WeakReference;

import static com.meister.sampleretrofitapp.MainActivity.UpdateType;

/**
 * Handler used to update the UI from a background thread.
 * Created by mark.meister on 8/3/15.
 */
public class ResultsHandler extends Handler {
    private WeakReference<MainActivity> mActivity;

    public ResultsHandler (MainActivity activity) {
        mActivity = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mActivity == null || mActivity.get() == null) {
            return;
        }

        final UpdateType type = UpdateType.values()[msg.what];
        switch(type) {
            case LeftImage:
                mActivity.get().updateLeftImage(msg.getData().getString(MessageCreator.IMAGE_URL_KEY));
                break;

            case RightImage:
                mActivity.get().updateRightImage(msg.getData().getString(MessageCreator.IMAGE_URL_KEY));
                break;

            case CreateAlbum:
                mActivity.get().updateAlbumUrl(msg.getData().getString(MessageCreator.ALBUM_ID_KEY));
                break;

            case DeleteAlbum:
                mActivity.get().updateDeleteAlbumText(msg.getData().getBoolean(MessageCreator.DELETE_RESULT_KEY));
                break;

            case DisplayProgress:
                mActivity.get().showProgressBars();
                break;
        }
    }
}
