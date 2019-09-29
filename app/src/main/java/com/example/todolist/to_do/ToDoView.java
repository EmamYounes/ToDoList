package com.example.todolist.to_do;

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
}
