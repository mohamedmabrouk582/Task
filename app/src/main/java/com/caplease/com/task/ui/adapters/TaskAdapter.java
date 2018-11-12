package com.caplease.com.task.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.caplease.com.task.R;
import com.caplease.com.task.data.model.Task;
import com.caplease.com.task.databinding.TaskItemViewBinding;
import com.caplease.com.task.BR;

/*
 * Created By mabrouk on 12/11/18
 * Task
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.Holder> {
    int pos;
    private List<Task> data;

    public interface TaskListener {
        void onClick(Task item, int pos);
    }

    private TaskListener listener;

    public TaskAdapter() {
        data = new ArrayList<>();
    }

    public void setListener(TaskListener listener) {
        this.listener = listener;
    }

    public void setData(List<Task> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void addItem(Task item) {
        data.add(item);
        // notifyDataSetChanged();
        notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TaskItemViewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.task_item_view, parent, false);
        return new Holder(binding);
    }

    public int currentPos() {
        return pos;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        pos = position;
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TaskItemViewBinding itemViewBinding;

        public Holder(TaskItemViewBinding itemViewBinding) {
            super(itemViewBinding.getRoot());
            this.itemViewBinding = itemViewBinding;

        }

        public void bind(Task product) {
            itemViewBinding.setVariable(BR.task, product);
            itemViewBinding.executePendingBindings();
            itemViewBinding.getRoot().setOnClickListener((v) ->
                    listener.onClick(product, getAdapterPosition())
            );
        }
    }
}