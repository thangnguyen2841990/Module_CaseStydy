package com.codegym.model;

import java.io.Serializable;

public class Customer implements Serializable {
    private String id;
    private String name;
    private String indentity;
    private String phoneNumber;
    private String dateBorrowed;

    public Customer() {
    }

    public Customer(String id, String name, String indentity, String phoneNumber, String dateBorrowed) {
        this.id = id;
        this.name = name;
        this.indentity = indentity;
        this.phoneNumber = phoneNumber;
        this.dateBorrowed = dateBorrowed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndentity() {
        return indentity;
    }

    public void setIndentity(String indentity) {
        this.indentity = indentity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", " + this.name + ", CMND: " + this.indentity + ", SĐT: " + this.phoneNumber + ", Ngày mượn: " +
                this.dateBorrowed;
    }
}
