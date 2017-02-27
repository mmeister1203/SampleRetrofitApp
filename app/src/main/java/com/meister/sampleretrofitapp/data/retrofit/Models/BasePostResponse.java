package com.meister.sampleretrofitapp.data.retrofit.Models;

/**
 * Base class for all non-Post method requests.
 * Created by mark.meister on 8/3/15.
 */
@SuppressWarnings("unused")
public class BasePostResponse {
    private int status;

    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
