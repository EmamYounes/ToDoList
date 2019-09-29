package com.example.todolist.active_task.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.custom_layout.ToDoItemView;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;


public class ActiveTaskAdapter extends RecyclerView.Adapter<ActiveTaskAdapter.ViewHolder> {


    private ArrayList<ToDoModel> oldTaskList;

    public ActiveTaskAdapter(ArrayList<ToDoModel> oldTaskList) {
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
        holder.toDoItemView.setCardTitle(oldTaskList.get(position).getCardTitle());
        holder.toDoItemView.setCardDescription(oldTaskList.get(position).getCardDescription());
        holder.toDoItemView.setVisibilityCardDate(View.VISIBLE);
        holder.toDoItemView.setcardDate(oldTaskList.get(position).getToDoDate());
    }

    @Override
    public int getItemCount() {
        return oldTaskList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ToDoItemView toDoItemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoItemView = itemView.findViewById(R.id.to_do_item_view);
        }
    }
}
