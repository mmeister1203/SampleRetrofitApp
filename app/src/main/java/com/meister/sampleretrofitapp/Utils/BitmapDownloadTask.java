package com.meister.sampleretrofitapp.Utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * AsyncTask used to download a bitmap at a specified url then invoke a callback on the UI thread.
 * Created by mark.meister on 8/3/15.
 */
public class BitmapDownloadTask extends AsyncTask<String, Void, Bitmap> {
    public interface RetrieveBitmap {
        void onBitmapRetrieved(Bitmap bitmap);
    }

    private final RetrieveBitmap mCallback;
    private final String mUri;

    public BitmapDownloadTask(RetrieveBitmap callback, String uri) {
        mCallback = callback;
        mUri = uri;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return ImageUtils.downloadBitmap(mUri != null ? mUri : params[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (mCallback != null) {
            if (isCancelled()) {
                mCallback.onBitmapRetrieved(null);
            } else {
                mCallback.onBitmapRetrieved(bitmap);
            }
        }
    }
}
