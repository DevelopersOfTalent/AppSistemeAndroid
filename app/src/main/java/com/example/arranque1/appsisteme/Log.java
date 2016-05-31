package com.example.arranque1.appsisteme;

import java.sql.Timestamp;

/**
 * Created by Sergio Cano on 30/05/2016.
 */
public class Log {
    public String state;
    public Timestamp date;

    public Log(String state, Timestamp date) {
        this.state = state;
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
