package com.codegym.view;

import com.codegym.controller.staff.IStaffManagement;
import com.codegym.controller.staff.StaffManagement;
import com.codegym.model.Staff;

import java.io.IOException;
import java.util.Scanner;

public class StaffMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);
    public void run() {
        int choice = -1;
        IStaffManagement staffManagement = StaffManagement.getInstance();
        doReadFileStaff(staffManagement);
        do {
            System.out.println("1. Hiển thị danh sách nhân viên.");
            System.out.println("2. Thêm nhân viên mới.");
            System.out.println("3. Cập nhật thông tin nhân viên.");
            System.out.println("4. Xóa nhân viên.");
            System.out.println("5. Hiển thị nhân viên sinh nhật trong tháng.");
            System.out.println("0. Quay lại.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 5) {
                System.out.println("UserLogin chỉ có từ 1 - 5");
            }
            switch (choice) {
                case 1: {
                    doShowAllStaff(staffManagement);
                    break;
                }
                case 2: {
                    doAddNewStaff(staffManagement);
                    break;
                }
                case 3 : {
                    doUpdateStaff(staffManagement);
                    break;
                }
                case 4 : {
                    doRemoveStaff(staffManagement);
                    break;
                }
                case 5 : {
                    doShowStaffBirthDayInMonth(staffManagement);
                    break;
                }
            }
            doWriteFileStaff(staffManagement);
        } while (choice != 0);
    }

    private void doWriteFileStaff(IStaffManagement staffManagement) {
        try {
            staffManagement.writeFile("staff.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doReadFileStaff(IStaffManagement staffManagement) {
        try {
            staffManagement.readFile("staff.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void doShowStaffBirthDayInMonth(IStaffManagement staffManagement) {
        System.out.println("----Hiển thị nhân viên sinh nhật trong tháng----");
        staffManagement.displayStaffBirthDay();
    }

    private void doRemoveStaff(IStaffManagement staffManagement) {
        System.out.println("----Xóa nhân viên----");
        System.out.println("Nhập mã số nhân viên: ");
        String id = inputString.nextLine();
        int index = staffManagement.findById(id);
        if (index == -1 ){
            System.out.println("Mã số nhân viên không đúng!");
        }else {
            staffManagement.remove(index);
            System.out.println("Xóa nhân viên thành công!");
        }
    }

    private void doUpdateStaff(IStaffManagement staffManagement) {
        System.out.println("----Cập nhật thông tin nhân viên----");
        System.out.println("Nhập mã số nhân viên: ");
        String id = inputString.nextLine();
        int index = staffManagement.findById(id);
        if (index == -1 ){
            System.out.println("Mã số nhân viên không đúng!");
        }else {
            Staff newStaff = inputNewStaffInfo();
            staffManagement.update(index,newStaff);
            System.out.println("Cập nhật thành công!");
        }
    }

    private void doAddNewStaff(IStaffManagement staffManagement) {
        System.out.println("----Thêm nhân viên mới----");
        Staff newStaff = inputNewStaffInfo();
        staffManagement.add(newStaff);
        System.out.println("Thêm nhân viên thành công!");
    }

    private Staff inputNewStaffInfo() {
        System.out.println("Nhập thông tin nhân viên");
        System.out.println("Mã số nhân viên: ");
        String id = inputString.nextLine();
        System.out.println("Họ và tên: ");
        String name = inputString.nextLine();
        System.out.println("Sinh nhật: ");
        String birthDay = inputString.nextLine();
        System.out.println("Quê quán: ");
        String homeTown = inputString.nextLine();
        System.out.println("Giới tính: ");
        String sex = inputString.nextLine();
        System.out.println("Công việc: ");
        String job = inputString.nextLine();
        return new Staff(id, name, birthDay, homeTown, sex, job);
    }

    private void doShowAllStaff(IStaffManagement staffManagement) {
        System.out.println("----Hiển thị danh sách nhân viên----");
        int size = staffManagement.getSize();
        if (size == 0) {
            System.out.println("Danh sách rỗng!");
        } else {
            staffManagement.displayAll();
        }
    }
}
