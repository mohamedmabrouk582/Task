package com.caplease.com.task.viewModel.tasks;

import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.caplease.com.task.R;
import com.caplease.com.task.data.db.TaskDbOperation;
import com.caplease.com.task.ui.fragment.AddTaskFragment;
import com.caplease.com.task.ui.fragment.TasksFragment;
import com.caplease.com.task.viewModel.base.BaseViewModel;
import com.caplease.com.task.views.TasksView;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

public class TasksViewModel<v extends TasksView> extends BaseViewModel<v> implements TasksVmodel<v> {
    private TaskDbOperation operation;
    private TasksFragment fragment;


    public TasksViewModel(TasksFragment application, TaskDbOperation dbOperation) {
        super(application.getActivity().getApplication());
        fragment=application;
        this.operation=dbOperation;
    }

    @Override
    public void reqTasks() {
       operation.getData().observe(fragment,e->{
           Log.d("dsdsd",e.toString());
           getView().loadTasks(e);
       });
    }

    public void addTask(View view){
        fragment.replaceFragment(AddTaskFragment.getFragment(), R.id.Fragment_Container,"AddTaskFragment",true);

    }


    public static class TasksViewModelFactory implements ViewModelProvider.Factory {
        private TasksFragment fragment;
        private TaskDbOperation operation;

        public TasksViewModelFactory(TasksFragment fragment,TaskDbOperation dbOperation) {
            this.fragment = fragment;
            this.operation = dbOperation;
        }

        @NonNull
        @Override
        public TasksViewModel create(@NonNull Class modelClass) {
            return new TasksViewModel(fragment,operation);
        }
    }
}