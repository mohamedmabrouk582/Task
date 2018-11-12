package com.caplease.com.task.views;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import com.caplease.com.task.data.model.Task;

import java.util.List;

public interface TasksView extends BaseView {
    void loadTasks(List<Task> tasks);
}
