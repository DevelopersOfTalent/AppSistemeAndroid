package com.example.arranque1.appsisteme;

import java.io.Serializable;

/**
 * Created by Arranque 1 on 18/05/2016.
 */
public class Contact extends Object implements Serializable {
    private Integer id;
    private String name;
    private String phone;


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

    public Contact(Integer id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "" + name;
    }
}
