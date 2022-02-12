package com.codegym.model;

import java.io.Serializable;

public class OrderStory implements Serializable {
    private final String name = "Nguyễn Văn A";
    private final String phoneNumber = "0394910426";
    private Story story;
    private int quanlity;
    private int payMoney;

    public OrderStory(Story story, int quanlity, int payMoney) {
        this.story = story;
        this.quanlity = quanlity;
        this.payMoney = payMoney;

    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    public int getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public int getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(int payMoney) {
        this.payMoney = payMoney;
    }



    @Override
    public String toString() {
        return this.name + ", " + this.phoneNumber + ", "+
                story.toString() + " - Số lượng: " + this.quanlity + " - Thành tiền: " + this.payMoney;

    }
}
