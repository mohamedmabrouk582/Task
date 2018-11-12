package com.caplease.com.task.di.components;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import com.caplease.com.task.app.MyApp;
import com.caplease.com.task.data.db.TaskDb;
import com.caplease.com.task.data.db.TaskDbOperation;
import com.caplease.com.task.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    TaskDbOperation getTaskDbOperation();
    void inject(MyApp myApp);
}
