package com.codegym.model;

import java.io.Serializable;

public class CustomersByAppointment implements Serializable {
    private String name;
    private String phoneNumber;
    private String storyId;
    private String time;

    public CustomersByAppointment(String name, String phoneNumber, String storyId, String time) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.storyId = storyId;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return this.name +", SĐT: "+ this.phoneNumber+", Mã số truyện:  "+ this.storyId+
                ", Thời gian hẹn: "+ this.time;
    }
}
