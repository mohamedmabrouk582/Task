package com.caplease.com.task.data.db;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.caplease.com.task.data.model.Task;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class TaskDbOperation {
    private TaskDao taskDao;

    @Inject
    public TaskDbOperation(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public LiveData<List<Task>> getData(){
        return taskDao.getTasks();
    }

    public Long insertTask(Task task) throws ExecutionException, InterruptedException {
        return new  Insert().execute(task).get();
    }

    private class Insert extends AsyncTask<Task,Void,Long>{

        @Override
        protected Long doInBackground(Task... tasks) {
            return taskDao.insert(tasks[0]);
        }
    }
}
