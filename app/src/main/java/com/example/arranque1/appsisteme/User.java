package com.example.arranque1.appsisteme;

/**
 * Created by Arranque 1 on 26/05/2016.
 */
public class User {
    public String id;
    public UserType uType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserType getuType() {
        return uType;
    }

    public void setuType(UserType uType) {
        this.uType = uType;
    }
}
