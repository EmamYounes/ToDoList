package com.example.todolist.to_do;

import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public interface ToDoView {

    void setInitialDate(int year, int month, int date);

    String getNoteTitle();

    String getNoteDescription();

    void makeToastMessage(int stringRes);

    void addNoteToList(String noteTitle, String noteDescription);

    void dismissAlertDialog();

    void showEmptyCase();

    void hideEmptyCase();

    void setVisibilityAddButton(int visibility);

    ArrayList<ToDoModel> getListForDay(ArrayList<ToDoModel> toDoModels);
}
