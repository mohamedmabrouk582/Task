package com.caplease.com.task.data.db;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.caplease.com.task.data.model.Task;

@Database(entities = Task.class ,version = 1,exportSchema = false)
public abstract class TaskDb extends RoomDatabase{
    public abstract TaskDao getTaskDao();
}
