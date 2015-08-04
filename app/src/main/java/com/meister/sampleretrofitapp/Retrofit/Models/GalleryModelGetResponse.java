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
    // Here I've implemented all properties, however usually we'd only implement those we use.
    public class MyGallery {

        // ID for the image
        private String id;

        // Title of image
        private String title;

        // Description of image
        private String description;

        // Image MIME type
        private String type;

        // deletehash used to reference images
        private String deletehash;

        // Direct link to image
        private String link;

        // Direct .gifv link (if available)
        private String gifv;

        // Direct .mp4 link (if available)
        private String mp4;

        // Direct .webm link (if available)
        private String webm;

        // Current users vote on the album
        private String vote;

        // Topic of the gallery image
        private String topic;

        // Categorized section
        private String section;

        // Username of account that uploaded image
        private String account_url;

        // Time image was inserted in gallery (epoch time)
        private int datetime;

        // Width of image [px]
        private int width;

        // Height of image [px]
        private int height;

        // Size of image [bytes]
        private int size;

        // Number of image views
        private int views;



        // Number of comments on image
        private int comment_count;

        // Topic ID of image
        private int topic_id;

        // Account ID of the account that uploaded the image
        private int account_id;

        // Upvotes for the image
        private int ups;

        // Downvotes for the image
        private int downs;

        // Popularity score
        private int score;

        // Is the image animated
        private boolean animated;

        // Image has looping animation
        private boolean looping;

        // Has current user favorited the image
        private boolean favorite;

        // Has image been marked nsfw
        private boolean nsfw;

        // If this represents an album
        private boolean is_album;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDeletehash() {
            return deletehash;
        }

        public void setDeletehash(String deletehash) {
            this.deletehash = deletehash;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getGifv() {
            return gifv;
        }

        public void setGifv(String gifv) {
            this.gifv = gifv;
        }

        public String getMp4() {
            return mp4;
        }

        public void setMp4(String mp4) {
            this.mp4 = mp4;
        }

        public String getWebm() {
            return webm;
        }

        public void setWebm(String webm) {
            this.webm = webm;
        }

        public String getVote() {
            return vote;
        }

        public void setVote(String vote) {
            this.vote = vote;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getSection() {
            return section;
        }

        public void setSection(String section) {
            this.section = section;
        }

        public String getAccount_url() {
            return account_url;
        }

        public void setAccount_url(String account_url) {
            this.account_url = account_url;
        }

        public int getDatetime() {
            return datetime;
        }

        public void setDatetime(int datetime) {
            this.datetime = datetime;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public int getTopic_id() {
            return topic_id;
        }

        public void setTopic_id(int topic_id) {
            this.topic_id = topic_id;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public int getUps() {
            return ups;
        }

        public void setUps(int ups) {
            this.ups = ups;
        }

        public int getDowns() {
            return downs;
        }

        public void setDowns(int downs) {
            this.downs = downs;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public boolean isAnimated() {
            return animated;
        }

        public void setAnimated(boolean animated) {
            this.animated = animated;
        }

        public boolean isLooping() {
            return looping;
        }

        public void setLooping(boolean looping) {
            this.looping = looping;
        }

        public boolean isFavorite() {
            return favorite;
        }

        public void setFavorite(boolean favorite) {
            this.favorite = favorite;
        }

        public boolean isNsfw() {
            return nsfw;
        }

        public void setNsfw(boolean nsfw) {
            this.nsfw = nsfw;
        }

        public boolean is_album() {
            return is_album;
        }

        public void setIs_album(boolean is_album) {
            this.is_album = is_album;
        }
    }
}
