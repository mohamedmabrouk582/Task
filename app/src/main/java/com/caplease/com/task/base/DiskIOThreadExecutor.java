package com.caplease.com.task.base;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class DiskIOThreadExecutor implements Executor {
    private final Executor mDiskIO;

    @Inject
    public DiskIOThreadExecutor(Executor mDiskIO) {
        this.mDiskIO = mDiskIO;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        mDiskIO.execute(command);
    }
}
