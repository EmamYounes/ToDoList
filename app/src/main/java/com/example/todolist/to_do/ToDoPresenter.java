package com.example.todolist.to_do;

public class ToDoPresenter {

    ToDoView getView;

    public ToDoPresenter(ToDoView myView) {
        this.getView = myView;
    }

    public void onStart() {
        getView.setInitialDate(2019,3,21);
    }
}
