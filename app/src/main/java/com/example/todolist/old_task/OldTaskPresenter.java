package com.example.todolist.old_task;

import com.example.todolist.MySingleton;
import com.example.todolist.local_data.DatabaseHelper;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public class OldTaskPresenter {

    private OldTaskView getView;

    DatabaseHelper databaseHelper;
    ArrayList<ToDoModel> toDoList;


    public OldTaskPresenter(OldTaskView getView) {
        this.getView = getView;
    }


    void onStart() {
        databaseHelper = MySingleton.getInstance().getDatabaseHelper();
        toDoList = databaseHelper.getToDoList();
        getView.setAdapterData(toDoList);
    }

}
