package com.codegym.view;

import java.util.Scanner;

public class StaffManagementMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputStrung = new Scanner(System.in);
    public void run(){
        int choice = -1;
        StoryMenu storyMenu = new StoryMenu();
        CustomerMenu customerMenu = new CustomerMenu();
        do {
            System.out.println("----CỬA HÀNG CHO THUÊ TRUYỆN CODEGYM----");
            System.out.println("1. Quản lý truyện.");
            System.out.println("2. Quản lý khách hàng.");
            System.out.println("0. Quay lại.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 2 ){
                System.out.println("UserLogin chỉ có từ 1 - 2!");
            }
            switch (choice){
                case 1 : {
                    storyMenu.run();
                    break;
                }
                case 2 : {
                    customerMenu.run();
                    break;
                }
            }
        }while (choice != 0);
    }
}
