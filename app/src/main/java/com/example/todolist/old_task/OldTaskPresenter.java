package com.example.todolist.old_task;

import com.example.todolist.DateFormat;
import com.example.todolist.MySingleton;
import com.example.todolist.local_data.DatabaseHelper;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

import io.reactivex.Observable;

class OldTaskPresenter {

    private OldTaskView getView;

    private DatabaseHelper databaseHelper;
    private ArrayList<ToDoModel> toDoList;
    private DateFormat dateFormat;


    OldTaskPresenter(OldTaskView getView) {
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
                dateFormat.isOldDate(item.getToDoDate())).toList().blockingGet();
    }

    void handleEmptyCase(ArrayList<ToDoModel> toDoModels) {
        if (toDoModels.isEmpty()) {
            getView.showEmptyCase();
        } else {
            getView.hideEmptyCase();
        }
    }
}
