package com.example.todolist.to_do.model;

public class DateFormat {

    private static DateFormat dateFormat;
    private int year = 0;
    private int month = 0;
    private int day = 0;

    public DateFormat() {
    }

    public DateFormat(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public String getDate() {
        return day + "/" + month + "/" + year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
