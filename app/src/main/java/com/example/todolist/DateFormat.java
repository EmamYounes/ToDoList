package com.example.todolist;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormat {

    private static DateFormat dateFormat;
    private int year = 0;
    private int month = 0;
    private int day = 0;
    String formattedDate = "";

    String[] formattedDateSplit;
    int currentYear = 0;
    int currentMonth = 0;
    int currentDay = 0;

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
        Date currentTime = Calendar.getInstance().getTime();
        Log.d("Current time => ", "" + currentTime);

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = df.format(currentTime);
        formattedDateSplit = formattedDate.split("/");
        currentYear = Integer.parseInt(formattedDateSplit[2]);
        currentMonth = Integer.parseInt(formattedDateSplit[1]);
        currentDay = Integer.parseInt(formattedDateSplit[0]);
        Log.d("formatted Date => ", "" + formattedDate);
    }

    public boolean isOldDate(String date) {
        if (date.isEmpty() || !date.contains("/")) return false;
        String[] splitedDate = date.split("/");
        int selectedYear = Integer.parseInt(splitedDate[2]);
        int selectedMonth = Integer.parseInt(splitedDate[1]);
        int selectedDay = Integer.parseInt(splitedDate[0]);
        if (selectedYear <= currentYear) {
            if (selectedMonth <= currentMonth) {
                if (selectedDay < currentDay) {
                    return true;
                }
            }

        }
        return false;
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
