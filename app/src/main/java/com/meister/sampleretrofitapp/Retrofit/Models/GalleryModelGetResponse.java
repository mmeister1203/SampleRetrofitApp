package com.meister.sampleretrofitapp.Retrofit.Models;

import java.util.List;

/**
 * GalleryModel. Represents a list of MyGallery objects. Each MyGallery object represents a single
 * image on the Imgur gallery page.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class GalleryModelGetResponse extends BaseGetResponse {

    private List<MyGallery> data;

    public List<MyGallery> getData() {
        return data;
    }

    public void setData(List<MyGallery> data) {
        this.data = data;
    }

    // Data model for our gallery images. Defined in https://api.imgur.com/models/gallery_image
    public class MyGallery {

        // ID for the image
        private String id;

        // Direct link to image
        private String link;

        // If this represents an album
        private boolean is_album;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public boolean is_album() {
            return is_album;
        }

        public void setIs_album(boolean is_album) {
            this.is_album = is_album;
        }
    }
}
