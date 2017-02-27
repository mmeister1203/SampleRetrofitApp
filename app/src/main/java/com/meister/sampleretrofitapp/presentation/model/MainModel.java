package com.meister.sampleretrofitapp.presentation.model;

/**
 * MainModel
 * Created by markmeister on 2/27/17.
 */

public class MainModel {
    private String leftImageId;
    private String rightImageId;

    public String getLeftImageId() {
        return leftImageId;
    }

    public void setLeftImageId(String leftImageId) {
        this.leftImageId = leftImageId;
    }

    public String getRightImageId() {
        return rightImageId;
    }

    public void setRightImageId(String rightImageId) {
        this.rightImageId = rightImageId;
    }

    public String[] getImageArray() {
        return new String[] {leftImageId, rightImageId};
    }
}
