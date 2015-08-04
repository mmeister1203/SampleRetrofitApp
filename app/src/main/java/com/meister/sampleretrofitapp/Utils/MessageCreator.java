package com.meister.sampleretrofitapp.Utils;

import android.os.Bundle;
import android.os.Message;

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
        return createMessage(UpdateType.LeftImage, url, false);
    }

    public static Message createRightImageMessage(String url) {
        return createMessage(UpdateType.RightImage, url, false);
    }

    public static Message createAlbumMessage(String id) {
        return createMessage(UpdateType.CreateAlbum, id, false);
    }

    public static Message createDeleteMessage(boolean result) {
        return createMessage(UpdateType.DeleteAlbum, "", result);
    }

    public static Message createMessage(UpdateType type, String idOrUrl, boolean success) {
        final Bundle args = new Bundle();

        final Message msg = new Message();
        msg.what = type.ordinal();

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
