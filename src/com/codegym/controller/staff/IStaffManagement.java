package com.codegym.controller.staff;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.Staff;

import java.util.Date;

public interface IStaffManagement extends GeneralManagement<Staff>, WriteFile, ReadFile {
    Date convertStringToDate(String birthDay);
    void displayStaffBirthDay();
}
