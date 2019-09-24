package com.example.todolist.to_do.model;

public class ToDoListModel {

    private String cardTitle="";
    private String cardDescription="";

    public ToDoListModel(String cardTitle, String cardDescription) {
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }
}
