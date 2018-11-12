package com.caplease.com.task.viewModel.base;

import com.caplease.com.task.views.BaseView;

public interface BaseVmodel<v extends BaseView> {
    void attachView(v view);
}
