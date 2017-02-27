package com.meister.sampleretrofitapp.presentation.presenter;

import com.meister.sampleretrofitapp.domain.view.BaseView;

/**
 * Presenter
 * Created by markmeister on 2/27/17.
 */

public interface Presenter<T extends BaseView> {
    void attachView(T view);
    void destroy();
}
