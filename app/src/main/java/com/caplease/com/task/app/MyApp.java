package com.caplease.com.task.app;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.FragmentActivity;

import com.caplease.com.task.di.components.AppComponent;
import com.caplease.com.task.di.components.DaggerAppComponent;
import com.caplease.com.task.di.modules.AppModule;

public class MyApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent= DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
        appComponent.inject(this);
    }

    public static MyApp get(Activity activity){
        return (MyApp) activity.getApplication();
    }

    public static MyApp get(FragmentActivity activity){
        return (MyApp) activity.getApplication();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
