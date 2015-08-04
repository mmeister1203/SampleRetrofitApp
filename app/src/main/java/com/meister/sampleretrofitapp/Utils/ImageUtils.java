package com.meister.sampleretrofitapp.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.http.AndroidHttpClient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import java.io.InputStream;

/**
 * ImageUtils. Provides a static method to download a bitmap at a url. Can handle redirects.
 * Created by mark.meister on 8/3/15.
 */
public class ImageUtils {

    public static Bitmap downloadBitmap(String url) {
        final AndroidHttpClient client = AndroidHttpClient.newInstance("Android");
        final HttpGet getRequest = new HttpGet(url);

        try {
            HttpResponse response = client.execute(getRequest);
            final int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != HttpStatus.SC_OK) {
                final Header[] headers = response.getHeaders("Location");

                if (headers != null && headers.length != 0) {
                    final String newUrl = headers[headers.length - 1].getValue();
                    return downloadBitmap(newUrl);
                } else {
                    return null;
                }
            }

            final HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream inputStream = null;
                try {
                    inputStream = entity.getContent();
                    return BitmapFactory.decodeStream(inputStream);
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    entity.consumeContent();
                }
            }
        } catch (Exception e) {
            getRequest.abort();
        } finally {
            client.close();
        }

        return null;
    }
}
