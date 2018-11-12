package com.caplease.com.task.di.modules;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.arch.lifecycle.ViewModelProvider;

import com.caplease.com.task.data.db.TaskDbOperation;
import com.caplease.com.task.di.scopes.TasksScope;
import com.caplease.com.task.ui.fragment.TasksFragment;
import com.caplease.com.task.viewModel.tasks.TasksViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class TasksModule {
    private TasksFragment fragment;

    public TasksModule(TasksFragment fragment) {
        this.fragment = fragment;
    }

    @TasksScope
    @Provides
    public TasksFragment getFragment() {
        return fragment;
    }

    @TasksScope
    @Provides
    public ViewModelProvider.Factory getFactory(TaskDbOperation dbOperation){
        return new TasksViewModel.TasksViewModelFactory(fragment,dbOperation);
    }
}
