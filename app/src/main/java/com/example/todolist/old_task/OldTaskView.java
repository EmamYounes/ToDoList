package com.example.todolist.old_task;

import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public interface OldTaskView {
    void setAdapterData( ArrayList<ToDoModel> oldTaskList);
}
