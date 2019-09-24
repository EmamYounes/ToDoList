package com.example.todolist.to_do.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.to_do.model.ToDoListModel;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {


    ArrayList<ToDoListModel> toDoListModels;

    public ToDoListAdapter(ArrayList<ToDoListModel> toDoListModels) {
        this.toDoListModels = toDoListModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardTitle.setText(toDoListModels.get(position).getCardTitle());
        holder.cardDescription.setText(toDoListModels.get(position).getCardDescription());
    }

    @Override
    public int getItemCount() {
        return toDoListModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cardTitle;
        public TextView cardDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardTitle = itemView.findViewById(R.id.card_title);
            cardDescription = itemView.findViewById(R.id.card_description);
        }
    }
}
