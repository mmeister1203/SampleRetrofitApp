package com.meister.sampleretrofitapp.Retrofit.Models;

/**
 * BaseResponse class. Contains the status of our request in the form of a boolean and status code.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class BaseGetResponse {

    private boolean success;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
