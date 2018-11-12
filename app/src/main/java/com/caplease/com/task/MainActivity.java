package com.caplease.com.task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.caplease.com.task.base.activity.BaseActivity;
import com.caplease.com.task.ui.fragment.TasksFragment;

public class MainActivity extends BaseActivity {

    @Override
    public int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void iniViews() {

     replaceFragment(TasksFragment.getFragment(),R.id.Fragment_Container,"TasksFragment",false);
    }
}
