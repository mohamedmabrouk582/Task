package com.caplease.com.task.ui.fragment;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.caplease.com.task.R;
import com.caplease.com.task.app.MyApp;
import com.caplease.com.task.base.fragment.BaseFragment;
import com.caplease.com.task.base.fragment.RequestFragment;
import com.caplease.com.task.data.db.TaskDbOperation;
import com.caplease.com.task.data.model.Task;
import com.caplease.com.task.databinding.TaskLayoutBinding;
import com.caplease.com.task.di.components.DaggerTasksComponent;
import com.caplease.com.task.di.modules.TasksModule;
import com.caplease.com.task.ui.adapters.TaskAdapter;
import com.caplease.com.task.viewModel.tasks.TasksViewModel;
import com.caplease.com.task.views.TasksView;

import java.util.List;

import javax.inject.Inject;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class TasksFragment extends BaseFragment implements TasksView, TaskAdapter.TaskListener {
    @Inject public TaskDbOperation operation;
    @Inject public ViewModelProvider.Factory factory;
    private TaskLayoutBinding layoutBinding;
    private TasksViewModel viewModel;
    private TaskAdapter taskAdapter;
    @Override
    public int setContentView() {
        return R.layout.task_layout;
    }


    public static TasksFragment getFragment(){
        return new TasksFragment();
    }

    @Override
    public void iniViews() {
     layoutBinding= DataBindingUtil.bind(view);
        DaggerTasksComponent.builder()
                .appComponent(MyApp.get(getActivity()).getAppComponent())
                .tasksModule(new TasksModule(this))
                .build().inject(this);
        viewModel= ViewModelProviders.of(this,factory).get(TasksViewModel.class);
        viewModel.attachView(this);
        taskAdapter=new TaskAdapter();
        taskAdapter.setListener(this);
        layoutBinding.tasksRecyclerView.setAdapter(taskAdapter);
        layoutBinding.setTaskVm(viewModel);
        viewModel.reqTasks();
    }

    @Override
    public void loadTasks(List<Task> tasks) {
        taskAdapter.setData(tasks);
    }

    @Override
    public void onClick(Task item, int pos) {
        Bundle bundle=new Bundle();
        bundle.putParcelable("taskInfo",item);
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_tasksFragment_to_showTaskFragment,bundle);
       // replaceFragment(ShowTaskFragment.getFragment(item), R.id.Fragment_Container,"ShowTaskFragment",true);
    }
}
