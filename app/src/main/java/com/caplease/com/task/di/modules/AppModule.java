package com.caplease.com.task.di.modules;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.arch.persistence.room.Room;

import com.caplease.com.task.app.MyApp;
import com.caplease.com.task.data.db.TaskDao;
import com.caplease.com.task.data.db.TaskDb;
import com.caplease.com.task.data.db.TaskDbOperation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MyApp myApp;

    public AppModule(MyApp myApp) {
        this.myApp = myApp;
    }
    @Singleton
    @Provides
    public MyApp getMyApp() {
        return myApp;
    }

    @Singleton
    @Provides
    public TaskDbOperation getTaskDbOperation(TaskDao dao){
        return new TaskDbOperation(dao);
    }

    @Singleton
    @Provides
    public TaskDao getTaskDao(TaskDb taskDb){
        return taskDb.getTaskDao();
    }


    @Singleton
    @Provides
    public TaskDb getTaskDb(){
        return Room.databaseBuilder(myApp.getApplicationContext(),TaskDb.class,"Task").build();
    }
}
