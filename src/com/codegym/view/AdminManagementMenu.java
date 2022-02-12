package com.codegym.view;

import java.util.Scanner;

public class AdminManagementMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static  Scanner inputString = new Scanner(System.in);
    public void run(){
        int choice = -1;
        StoreMenu storeMenu = new StoreMenu();
        StaffMenu staffMenu = new StaffMenu();
        StaffAccountMenu staffAccountMenu = new StaffAccountMenu();
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 3){
                System.out.println("UserLogin chỉ có từ 1 - 3!");
            }
            switch (choice){
                case 1 : {
                    staffAccountMenu.run();
                    break;
                }
                case 2 : {
                    staffMenu.run();
                    break;
                }
                case 3 : {
                    storeMenu.run();
                    break;
                }
            }
        }while (choice != 0);
    }

    private void menu() {
        System.out.println("----CỬA HÀNG CHO THUÊ TRUYỆN CODEGYM----");
        System.out.println("1. Quản lý tài khoản của nhân viên");
        System.out.println("2. Quản lý nhân viên.");
        System.out.println("3. Quản lý cửa hàng.");
        System.out.println("0. Quay lại.");
    }
}
