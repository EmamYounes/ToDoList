package com.example.todolist.to_do;

import com.example.todolist.R;

public class ToDoPresenter {

    ToDoView getView;

    ToDoPresenter(ToDoView myView) {
        this.getView = myView;
    }

    void onStart() {
        getView.setInitialDate(2019, 3, 21);
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
}
