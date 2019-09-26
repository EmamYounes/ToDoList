package com.example.todolist.to_do.model;

public class ToDoModel {

    private int id;
    private String toDoDate = "";
    private String cardTitle = "";
    private String cardDescription = "";
    private String cardMail = "";

    public ToDoModel() {
    }

    public ToDoModel(int id, String toDoDate, String cardTitle, String cardDescription, String cardMail) {
        this.cardTitle = cardTitle;
        this.cardDescription = cardDescription;
        this.cardMail = cardMail;
        this.toDoDate = toDoDate;
        this.id = id;
    }

    public String getToDoDate() {
        return toDoDate;
    }

    public void setToDoDate(String toDoDate) {
        this.toDoDate = toDoDate;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardMail() {
        return cardMail;
    }

    public void setCardMail(String cardMail) {
        this.cardMail = cardMail;
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
