package com.example.todolist.old_task.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public class OldTaskListAdapter extends RecyclerView.Adapter<OldTaskListAdapter.ViewHolder> {


   private ArrayList<ToDoModel> oldTaskList;

    public OldTaskListAdapter(ArrayList<ToDoModel> oldTaskList) {
        this.oldTaskList = oldTaskList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.old_task_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return oldTaskList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
