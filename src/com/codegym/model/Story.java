package com.codegym.model;

import java.io.Serializable;

public class Story implements Serializable {
    private String id;
    private String name;
    private int price;
    private int quanlity;

    public Story() {
    }

    public Story(String id, String name, int price, int quanlity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quanlity = quanlity;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    @Override
    public String toString() {
        return  "Mã số truyện: "+this.id +", "+ this.name;
    }
}
