package com.example.arranque1.appsisteme;

import java.sql.Timestamp;

/**
 * Created by Sergio Cano on 30/05/2016.
 */
public class Log {
    public String state;
    public String date;

    public Log(String state, String date) {
        this.state = state;
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
