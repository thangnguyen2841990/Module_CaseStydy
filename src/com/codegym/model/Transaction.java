package com.codegym.model;

import java.io.Serializable;

public class Transaction implements Serializable {
    private DaysDiff daysDiff;
    private long payMoney;

    public Transaction(DaysDiff daysDiff, long payMoney) {
        this.daysDiff = daysDiff;
        this.payMoney = payMoney;
    }

    public DaysDiff getDaysDiff() {
        return daysDiff;
    }

    public void setDaysDiff(DaysDiff daysDiff) {
        this.daysDiff = daysDiff;
    }

    public long getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(long payMoney) {
        this.payMoney = payMoney;
    }

    @Override
    public String toString() {
        return daysDiff.toString() + " - Đã thanh toán: " + this.payMoney+"(VND)";
    }
}
