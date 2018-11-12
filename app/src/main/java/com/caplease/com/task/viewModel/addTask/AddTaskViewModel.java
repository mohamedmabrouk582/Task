package com.caplease.com.task.viewModel.addTask;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.caplease.com.task.data.db.TaskDbOperation;
import com.caplease.com.task.data.model.Task;
import com.caplease.com.task.ui.fragment.AddTaskFragment;
import com.caplease.com.task.viewModel.base.BaseViewModel;
import com.caplease.com.task.views.AddTaskView;

import java.util.concurrent.ExecutionException;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

public class AddTaskViewModel<v extends AddTaskView> extends BaseViewModel<v> implements AddTaskVmodel<v> {
    private TaskDbOperation dbOperation;
    private AddTaskFragment fragment;
    private ObservableField<String> des=new ObservableField<>();
    private ObservableField<String> error=new ObservableField<>();

    public AddTaskViewModel(AddTaskFragment application, TaskDbOperation dbOperation) {
        super(application.getActivity().getApplication());
        this.dbOperation = dbOperation;
    }

    public ObservableField<String> getDes() {
        return des;
    }

    public ObservableField<String> getError() {
        return error;
    }

    @BindingAdapter({"taskWatcher","error"})
    public static void taskText(EditText editText, TextWatcher watcher,ObservableField<String> error){
        editText.addTextChangedListener(watcher);
        editText.setError(error.get());
    }

    public  TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
         des.set(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void addTask(String des) {
        try {
            Log.d("inserrrr",dbOperation.insertTask(new Task(des))+"");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void addTask(View view){
        error.set(null);
        if (des!=null && !TextUtils.isEmpty(des.get())){
           addTask(des.get());
        }else {
            error.set("Des not be empty");
        }
    }


    public static class AddTaskViewModelFactory implements ViewModelProvider.Factory {
        private AddTaskFragment fragment;
        private TaskDbOperation dbOperation;

        public AddTaskViewModelFactory(AddTaskFragment fragment, TaskDbOperation dbOperation) {
            this.fragment = fragment;
            this.dbOperation = dbOperation;
        }

        @NonNull
        @Override
        public AddTaskViewModel create(@NonNull Class modelClass) {
            return new AddTaskViewModel(fragment, dbOperation);
        }
    }
}