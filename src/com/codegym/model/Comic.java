package com.codegym.model;

public class Comic extends Story {
    private int part;

    public Comic() {
    }

    public Comic(int part) {
        this.part = part;
    }

    public Comic(String id, String name, int price, int quanlity, int part) {
        super(id, name, price, quanlity);
        this.part = part;
    }

    public int getPart() {
        return part;
    }

    public void setPart(int part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return super.toString()+", tập: "+ this.part;
    }
}
