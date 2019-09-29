package com.example.todolist;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

    private int year = 0;
    private int month = 0;
    private int day = 0;
    private String formattedDate = "";

    private String[] formattedDateSplit;
    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentDay = 0;

    private Date currentTime;
    SimpleDateFormat dateFormat;

    public DateFormat() {
        initCurrentDate();
    }

    public DateFormat(int year, int month, int day) {
        initCurrentDate();
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

    private void initCurrentDate() {
        currentTime = Calendar.getInstance().getTime();
        Log.d("Current time => ", "" + currentTime);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = dateFormat.format(currentTime);
        formattedDateSplit = formattedDate.split("/");
        currentYear = Integer.parseInt(formattedDateSplit[2]);
        currentMonth = Integer.parseInt(formattedDateSplit[1]);
        currentDay = Integer.parseInt(formattedDateSplit[0]);
        Log.d("formatted Date => ", "" + formattedDate);
    }

    public boolean isOldDate(String date) {
        try {
            @SuppressLint("SimpleDateFormat")
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            assert date1 != null;
            return date1.before(currentTime);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("isOldDate method", e.toString());
            return false;
        }

    }

    public int getCurrentYear() {
        return currentYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
