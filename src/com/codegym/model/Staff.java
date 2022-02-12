package com.codegym.model;

import java.io.Serializable;

public class Staff implements Serializable {
    private String id;
    private String name;
    private String birthDay;
    private String homeTown;
    private String sex;
    private String job;

    public Staff() {
    }

    public Staff(String id, String name, String birthDay, String homeTown, String sex, String job) {
        this.id = id;
        this.name = name;
        this.birthDay = birthDay;
        this.homeTown = homeTown;
        this.sex = sex;
        this.job = job;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", " + this.name + ", Sinh nhật: " + this.birthDay + ", " + this.sex +
                ", Quê quán: " + this.homeTown + ", Công việc: " + this.job;
    }
}
