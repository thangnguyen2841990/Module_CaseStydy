package com.codegym.model;

import java.io.Serializable;

public class DaysDiff implements Serializable {
    private Customer customer;
    long daysDiff;

    public DaysDiff(Customer customer, long daysDiff) {
        this.customer = customer;
        this.daysDiff = daysDiff;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getDaysDiff() {
        return daysDiff;
    }

    public void setDaysDiff(long daysDiff) {
        this.daysDiff = daysDiff;
    }

    @Override
    public String toString() {
        return this.customer + ", Số ngày: "+ this.daysDiff;
    }
}
