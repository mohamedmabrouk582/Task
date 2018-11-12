package com.caplease.com.task.base;

import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class MainThreadExecutor implements Executor {
    private Handler mainThreadHandler;

    @Inject
    public MainThreadExecutor(Handler mainThreadHandler) {
        this.mainThreadHandler = mainThreadHandler;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mainThreadHandler.post(command);
    }
}
