package com.example.todolist;

import com.example.todolist.local_data.DatabaseHelper;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class MySingleton {


    private static MySingleton instance;
    private DatabaseHelper databaseHelper;
    private GoogleSignInClient googleSignInClient;

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

    public GoogleSignInClient getGoogleSignInClient() {
        return googleSignInClient;
    }

    public void saveGoogleSignInClient(GoogleSignInClient googleSignInClient) {
        this.googleSignInClient = googleSignInClient;
    }
}