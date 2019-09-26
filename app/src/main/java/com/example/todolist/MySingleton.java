package com.example.todolist;

import com.example.todolist.local_data.DatabaseHelper;

public class MySingleton {


    private static MySingleton instance;
    private DatabaseHelper databaseHelper;

    private MySingleton() {

    }

    public static MySingleton getInstance() {
        if (instance == null) {
            instance = new MySingleton();
        }
        return instance;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }

    public void saveDatabaseHelper(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }
}