package com.example.todolist.active_task;

import com.example.todolist.DateFormat;
import com.example.todolist.MySingleton;
import com.example.todolist.local_data.DatabaseHelper;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

import io.reactivex.Observable;

public class ActiveTaskPresenter {

    private ActiveTaskView getView;

    private DatabaseHelper databaseHelper;
    private ArrayList<ToDoModel> toDoList;
    private DateFormat dateFormat;


    ActiveTaskPresenter(ActiveTaskView getView) {
        this.getView = getView;
    }


    void onStart() {
        databaseHelper = MySingleton.getInstance().getDatabaseHelper();
        toDoList = databaseHelper.getToDoList();
        dateFormat = new DateFormat();
        getView.setAdapterData(getListForDay(toDoList));
        handleEmptyCase(getListForDay(toDoList));
    }

    private ArrayList<ToDoModel> getListForDay(ArrayList<ToDoModel> toDoModels) {
        return (ArrayList<ToDoModel>) Observable.fromIterable(toDoModels).filter(item ->
                !dateFormat.isOldDate(item.getToDoDate())).toList().blockingGet();
    }

    void handleEmptyCase(ArrayList<ToDoModel> toDoModels) {
        if (toDoModels.isEmpty()) {
            getView.showEmptyCase();
        } else {
            getView.hideEmptyCase();
        }
    }
}