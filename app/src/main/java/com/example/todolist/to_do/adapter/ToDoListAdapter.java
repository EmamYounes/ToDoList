package com.example.todolist.to_do.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {


    ArrayList<ToDoModel> toDoModels;

    public ToDoListAdapter(ArrayList<ToDoModel> toDoModels) {
        this.toDoModels = toDoModels;
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
        holder.cardTitle.setText(toDoModels.get(position).getCardTitle());
        holder.cardDescription.setText(toDoModels.get(position).getCardDescription());
    }

    @Override
    public int getItemCount() {
        return toDoModels.size();
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
