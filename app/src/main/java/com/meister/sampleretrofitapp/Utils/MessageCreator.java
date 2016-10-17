package com.meister.sampleretrofitapp.Utils;

import android.os.Bundle;
import android.os.Message;

import com.meister.sampleretrofitapp.MainActivity;

import static com.meister.sampleretrofitapp.MainActivity.CreateAlbum;
import static com.meister.sampleretrofitapp.MainActivity.DeleteAlbum;
import static com.meister.sampleretrofitapp.MainActivity.LeftImage;
import static com.meister.sampleretrofitapp.MainActivity.RightImage;
import static com.meister.sampleretrofitapp.MainActivity.UpdateType;

/**
 * MessageCreator. Allows use to create messages for handler based on type
 * Created by mark.meister on 8/3/15.
 */
public class MessageCreator {
    public static final String IMAGE_URL_KEY = "image_url";
    public static final String ALBUM_ID_KEY = "album_id";
    public static final String DELETE_RESULT_KEY = "delete_result";

    public static Message createLeftImageMessage(String url) {
        return createMessage(MainActivity.LeftImage, url, false);
    }

    public static Message createRightImageMessage(String url) {
        return createMessage(MainActivity.RightImage, url, false);
    }

    public static Message createAlbumMessage(String id) {
        return createMessage(MainActivity.CreateAlbum, id, false);
    }

    public static Message createDeleteMessage(boolean result) {
        return createMessage(MainActivity.DeleteAlbum, "", result);
    }

    public static Message createMessage(@UpdateType int type, String idOrUrl, boolean success) {
        final Bundle args = new Bundle();

        final Message msg = new Message();
        msg.what = type;

        switch (type) {
            case LeftImage:
            case RightImage:
                args.putString(IMAGE_URL_KEY, idOrUrl);
                break;

            case CreateAlbum:
                args.putString(ALBUM_ID_KEY, idOrUrl);
                break;

            case DeleteAlbum:
                args.putBoolean(DELETE_RESULT_KEY, success);
                break;
        }

        msg.setData(args);

        return msg;
    }
}
