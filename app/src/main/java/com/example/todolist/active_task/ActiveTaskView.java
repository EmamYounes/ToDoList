package com.example.todolist.active_task;

import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public interface ActiveTaskView {

    void setAdapterData(ArrayList<ToDoModel> oldTaskList);

    void showEmptyCase();

    void hideEmptyCase();
}
