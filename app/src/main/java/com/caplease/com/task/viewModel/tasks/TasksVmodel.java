package com.caplease.com.task.viewModel.tasks;

import com.caplease.com.task.viewModel.base.BaseViewModel;
import com.caplease.com.task.viewModel.base.BaseVmodel;
import com.caplease.com.task.views.TasksView;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

public interface TasksVmodel<v extends TasksView> extends BaseVmodel<v> {
    void reqTasks();
}