package com.codegym.model;

import java.io.Serializable;

public class UserCustomer implements Serializable {
    private String username;
    private String password;

    public UserCustomer() {
    }

    public UserCustomer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
