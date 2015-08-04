package com.meister.sampleretrofitapp.Retrofit.Models;

/**
 * CreateAlbumPostRequest body.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class CreateAlbumBody {

    public static CreateAlbumBody newInstance() {
        return new CreateAlbumBody();
    }

    // Image id's that should be included in album
    private String[] ids;

    // Title of the album
    private String title;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
