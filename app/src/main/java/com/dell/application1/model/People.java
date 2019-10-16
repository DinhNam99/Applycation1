package com.dell.application1.model;

public class People {
    int id;
    String name;
    int phoneNumber;
    String category;
    String mail;
    int active;

    public People(String name, int phoneNumber, int active) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    public People(int id, String name, int phoneNumber, String category,String mail, int active) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.category = category;
        this.mail = mail;
        this.active = active;
    }

    public People() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
