package com.example.bulksms.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Client extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;

    private String telephone;

    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
