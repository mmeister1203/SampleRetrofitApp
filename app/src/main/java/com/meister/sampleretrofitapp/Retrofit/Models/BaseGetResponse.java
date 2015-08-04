package com.meister.sampleretrofitapp.Retrofit.Models;

import com.google.gson.annotations.SerializedName;

/**
 * BaseResponse class. Contains the status of our request in the form of a boolean and status code.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class BaseGetResponse {

    // If we'd like to name our member something else, we can specify the serialized name here.
    @SerializedName("success")
    private boolean requestSuccess;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return requestSuccess;
    }

    public void setSuccess(boolean success) {
        this.requestSuccess = success;
    }
}
