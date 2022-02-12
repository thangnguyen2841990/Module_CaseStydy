package com.codegym.model;

public class Novel extends Story {
    private String type;

    public Novel() {
    }

    public Novel(String type) {
        this.type = type;
    }

    public Novel(String id, String name, int price, int quanlity, String type) {
        super(id, name, price, quanlity);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return super.toString() + ", Thể loại: " + this.type;
    }
}
