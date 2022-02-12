package com.codegym.controller.staff;

import com.codegym.model.Staff;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StaffManagement implements IStaffManagement {
    List<Staff> staffList = new ArrayList<>();
    public static final StaffManagement INSTANCE = new StaffManagement();

    private StaffManagement() {
    }

    public static StaffManagement getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Staff staff) {
        staffList.add(staff);
    }

    @Override
    public void update(int index, Staff staff) {
        staffList.set(index, staff);
    }

    @Override
    public void remove(int index) {
        staffList.remove(index);
    }

    @Override
    public void displayAll() {
        for (Staff staff : staffList) {
            System.out.println(staff);
        }
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < getSize(); i++) {
            if (staffList.get(i).getId().equals(id)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public Staff getByIndex(int index) {
        return staffList.get(index);
    }

    @Override
    public int getSize() {
        return staffList.size();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.staffList = (List<Staff>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.staffList);
    }

    @Override
    public Date convertStringToDate(String birthDay) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = sdf.parse(birthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public void displayStaffBirthDay() {
        List<Staff> staffList1 = new ArrayList<>();
        Date date = new Date();
        for (int i = 0; i < getSize(); i++) {
            if (convertStringToDate(staffList.get(i).getBirthDay()).getMonth() == date.getMonth()) {
                staffList1.add(staffList.get(i));
            }
        }
        if (staffList1.size() == 0) {
            System.out.println("Không có nhân viên nào sinh nhật trong tháng!");
        } else {
            for (Staff staff : staffList1) {
                System.out.println(staff);
            }
        }
    }
}
