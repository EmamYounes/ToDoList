package com.example.todolist.to_do.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.custom_layout.ToDoItemView;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {


    private ArrayList<ToDoModel> toDoModels;
    private View.OnClickListener mClickListener;

    public ToDoListAdapter(ArrayList<ToDoModel> toDoModels) {
        this.toDoModels = toDoModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(view1 -> mClickListener.onClick(view1));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.toDoItemView.setCardTitle(toDoModels.get(position).getCardTitle());
        holder.toDoItemView.setCardDescription(toDoModels.get(position).getCardDescription());
    }

    @Override
    public int getItemCount() {
        return toDoModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ToDoItemView toDoItemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoItemView = itemView.findViewById(R.id.to_do_item_view);
        }
    }

    public void updateList(ArrayList<ToDoModel> toDoModels) {
        this.toDoModels = toDoModels;
        notifyDataSetChanged();
    }

    public void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }
}
