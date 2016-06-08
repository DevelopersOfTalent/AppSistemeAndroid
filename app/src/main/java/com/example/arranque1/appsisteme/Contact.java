package com.example.arranque1.appsisteme;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Arranque 1 on 18/05/2016.
 */
public class Contact extends Object implements Serializable {
    private Integer id;
    private String name;
    private String phone;
    private String imageSource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public Contact(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public Contact(Integer id, String name, String phone, String imageSource) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.imageSource = imageSource;
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
