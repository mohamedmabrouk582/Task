package com.caplease.com.task.ui.fragment;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.caplease.com.task.R;
import com.caplease.com.task.base.fragment.BaseFragment;
import com.caplease.com.task.data.model.Task;
import com.caplease.com.task.databinding.ShowTaskBinding;

public class ShowTaskFragment extends BaseFragment {
    private static final String TASK = "task";
    private ShowTaskBinding showTaskBinding;

    public static ShowTaskFragment getFragment(Task task){
        Bundle bundle=new Bundle();
        bundle.putParcelable(TASK,task);
        ShowTaskFragment fragment=new ShowTaskFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int setContentView() {
        return R.layout.show_task;
    }

    @Override
    public void iniViews() {
     showTaskBinding= DataBindingUtil.bind(view);
     showTaskBinding.setTasks(getArguments().getParcelable("taskInfo"));
    }
}
