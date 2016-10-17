package com.meister.sampleretrofitapp.Handlers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import static com.meister.sampleretrofitapp.MainActivity.UpdateType;

/**
 * Handler used to update the UI from a background thread.
 * Created by mark.meister on 8/3/15.
 */
public class ResultsHandler extends Handler {

    public interface ResultsHandlerListener {
        void handleUpdateResult(@UpdateType int updateType, Bundle data);
    }

    private WeakReference<ResultsHandlerListener> mResultsListener;

    public ResultsHandler (ResultsHandlerListener resultsHandlerListener) {
        mResultsListener = new WeakReference<>(resultsHandlerListener);
    }

    @Override
    public void handleMessage(Message msg) {
        if (mResultsListener == null || mResultsListener.get() == null) {
            return;
        }

        mResultsListener.get().handleUpdateResult(msg.what, msg.getData());
    }
}
