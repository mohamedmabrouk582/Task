package com.caplease.com.task.di.components;

import com.caplease.com.task.di.modules.TasksModule;
import com.caplease.com.task.di.scopes.TasksScope;
import com.caplease.com.task.ui.fragment.TasksFragment;

import dagger.Component;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */
@TasksScope
@Component(dependencies = AppComponent.class,modules = TasksModule.class)
public interface TasksComponent {
    void inject(TasksFragment fragment);
}
