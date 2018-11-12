package com.caplease.com.task.di.components;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import com.caplease.com.task.di.modules.AddTaskModule;
import com.caplease.com.task.di.scopes.AddTaskScope;
import com.caplease.com.task.ui.fragment.AddTaskFragment;

import dagger.Component;

@AddTaskScope
@Component(dependencies = AppComponent.class,modules = AddTaskModule.class)
public interface AddTaskComponent {
    void inject(AddTaskFragment fragment);
}
