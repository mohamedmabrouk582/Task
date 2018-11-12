package com.caplease.com.task.data.db;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.caplease.com.task.data.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("select * from Task")
    LiveData<List<Task>> getTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insert(Task task);
}
