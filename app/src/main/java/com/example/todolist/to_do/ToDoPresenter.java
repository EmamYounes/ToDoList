package com.example.todolist.to_do;

import android.view.View;

import com.example.todolist.DateFormat;
import com.example.todolist.MySingleton;
import com.example.todolist.R;
import com.example.todolist.to_do.model.ToDoModel;

import java.util.ArrayList;

public class ToDoPresenter {

    ToDoView getView;
    DateFormat dateFormat;

    ToDoPresenter(ToDoView myView) {
        this.getView = myView;
    }

    void onStart() {
        dateFormat = new DateFormat();
        //start from first day in current month
        getView.setInitialDate(dateFormat.getCurrentYear(), dateFormat.getCurrentMonth(), 1);
    }

    void handleAddButtonVisibilty(String date) {
        if (dateFormat.isOldDate(date)) {
            getView.setVisibilityAddButton(View.GONE);
        } else {
            getView.setVisibilityAddButton(View.VISIBLE);
        }
    }

    void handlePostiveButtonAction() {
        String noteTitle = getView.getNoteTitle();
        String noteDescription = getView.getNoteDescription();
        if (noteDescription.isEmpty()) {
            getView.makeToastMessage(R.string.add_note_warning);
        } else if (noteTitle.isEmpty()) {
            getView.makeToastMessage(R.string.add_note_title_warning);
        } else {
            getView.addNoteToList(noteTitle, noteDescription);
            handleEmptyCase(getView.getListForDay(MySingleton.getInstance().getDatabaseHelper().getToDoList()));
            getView.dismissAlertDialog();
        }
    }

    void handleEmptyCase(ArrayList<ToDoModel> toDoModels) {
        if (toDoModels.isEmpty()) {
            getView.showEmptyCase();
        } else {
            getView.hideEmptyCase();
        }
    }
}
