package com.meister.sampleretrofitapp.Retrofit.Models;

/**
 * Basic imgur response for Post requests.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class BasicPostResponse extends BasePostResponse {

    private PostData data;

    public PostData getData() {
        return data;
    }

    public void setData(PostData data) {
        this.data = data;
    }

    public class PostData {
        private String id;
        private String deletehash;

        public String getDeletehash() {
            return deletehash;
        }

        public void setDeletehash(String deletehash) {
            this.deletehash = deletehash;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
