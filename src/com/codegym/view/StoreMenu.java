package com.codegym.view;

import com.codegym.controller.customer.CustomerManagement;
import com.codegym.controller.customer.ICustomerManagement;

import java.io.IOException;
import java.util.Scanner;

public class StoreMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        int choice = -1;
        ICustomerManagement customerManagement = CustomerManagement.getInstance();
        doReadFileTransaction(customerManagement);
        do {
            System.out.println("1. Hiển thị tổng doanh thu trong ngày");
            System.out.println("2. Hiển thị các giao dịch trong ngày.");
            System.out.println("3. Xóa hết các giao dịch trong ngày.");
            System.out.println("0. Quay lại.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 3) {
                System.out.println("UserLogin chỉ từ 1 - 3!");
            }
            switch (choice) {
                case 1: {
                    System.out.println("----Hiển thị tổng doanh thu trong ngày----.");
                    long total = customerManagement.totalTurnover();
                    System.out.println("Tổng doanh thu trong ngày: " + total + "(VNĐ)");
                    break;
                }
                case 2: {
                    System.out.println("----Hiển thị các giao dịch trong ngày----");
                    customerManagement.displayAllTransacton();
                    break;
                }
                case 3: {
                    System.out.println("----Xóa hết các giao dịch trong ngày----");
                    customerManagement.clearAllTransaction();
                    System.out.println("Đã xóa hết các giao dịch!");
                    break;
                }
            }
            doWriteFileTransaction(customerManagement);
        } while (choice != 0);
    }

    private void doWriteFileTransaction(ICustomerManagement customerManagement) {
        try {
            customerManagement.writeFileTransaction("transaction.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doReadFileTransaction(ICustomerManagement customerManagement) {
        try {
            customerManagement.reafFileTransaction("transaction.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
    }
}
