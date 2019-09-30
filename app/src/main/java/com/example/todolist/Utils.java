package com.example.todolist;

import android.net.Uri;

public class Utils {

    private String userName = "";
    private String userMail = "";
    private Uri userImageUri;
    private static Utils instance;

    public static Utils getInstance() {
        if (instance == null)
            instance = new Utils();
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public void saveUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void saveUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Uri getUserImageUri() {
        return userImageUri;
    }

    public void saveUserImageUrl(Uri userImageUri) {
        this.userImageUri = userImageUri;
    }
}
