package com.example.todolist.to_do;

import com.example.todolist.DateFormat;
import com.example.todolist.MySingleton;
import com.example.todolist.R;

public class ToDoPresenter {

    ToDoView getView;

    ToDoPresenter(ToDoView myView) {
        this.getView = myView;
    }

    void onStart() {
        DateFormat dateFormat = new DateFormat();
        getView.setInitialDate(dateFormat.getCurrentYear(), dateFormat.getCurrentMonth(), dateFormat.getCurrentDay());
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
            getView.dismissAlertDialog();
        }
    }

    void handleEmptyCase() {
        if (MySingleton.getInstance().getDatabaseHelper().getToDoList().isEmpty()) {
            getView.showEmptyCase();
        } else {
            getView.hideEmptyCase();
        }
    }
}
