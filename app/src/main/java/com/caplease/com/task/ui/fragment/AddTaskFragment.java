package com.caplease.com.task.ui.fragment;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;

import com.caplease.com.task.R;
import com.caplease.com.task.app.MyApp;
import com.caplease.com.task.base.fragment.BaseFragment;
import com.caplease.com.task.data.db.TaskDbOperation;
import com.caplease.com.task.databinding.AddEventFragmentBinding;
import com.caplease.com.task.di.components.DaggerAddTaskComponent;
import com.caplease.com.task.di.modules.AddTaskModule;
import com.caplease.com.task.viewModel.addTask.AddTaskViewModel;
import com.caplease.com.task.views.AddTaskView;

import javax.inject.Inject;

public class AddTaskFragment extends BaseFragment implements AddTaskView{
    private AddEventFragmentBinding fragmentBinding;
   @Inject public TaskDbOperation operation;
   @Inject public ViewModelProvider.Factory factory;
   private AddTaskViewModel viewModel;
    @Override
    public int setContentView() {
        return R.layout.add_event_fragment;
    }

    public static AddTaskFragment getFragment(){
        return new AddTaskFragment();
    }

    @Override
    public void iniViews() {
        DaggerAddTaskComponent.builder()
                .appComponent(MyApp.get(getActivity()).getAppComponent())
                .addTaskModule(new AddTaskModule(this))
                .build().inject(this);
     fragmentBinding= DataBindingUtil.bind(view);
     viewModel= ViewModelProviders.of(this,factory).get(AddTaskViewModel.class);
     viewModel.attachView(this);
     fragmentBinding.setAdds(viewModel);
    }
}
