package com.caplease.com.task.di.modules;

import android.arch.lifecycle.ViewModelProvider;

import com.caplease.com.task.data.db.TaskDbOperation;
import com.caplease.com.task.di.scopes.AddTaskScope;
import com.caplease.com.task.ui.fragment.AddTaskFragment;
import com.caplease.com.task.viewModel.addTask.AddTaskViewModel;

import dagger.Module;
import dagger.Provides;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */
@Module
public class AddTaskModule {
    private AddTaskFragment fragment;

    public AddTaskModule(AddTaskFragment fragment) {
        this.fragment = fragment;
    }

    @AddTaskScope
    @Provides
    public AddTaskFragment getFragment() {
        return fragment;
    }

    @AddTaskScope
    @Provides
    public ViewModelProvider.Factory getFactory(TaskDbOperation dbOperation){
        return new AddTaskViewModel.AddTaskViewModelFactory(fragment,dbOperation);
    }
}
