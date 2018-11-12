package com.caplease.com.task.viewModel.addTask;

import android.view.View;

import com.caplease.com.task.viewModel.base.BaseVmodel;
import com.caplease.com.task.views.AddTaskView;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

public interface AddTaskVmodel<v extends AddTaskView> extends BaseVmodel<v> {
    void addTask(String des);
}