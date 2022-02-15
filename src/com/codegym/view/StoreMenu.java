package com.codegym.view;

import com.codegym.controller.customer.CustomerManagement;
import com.codegym.controller.customer.ICustomerManagement;
import com.codegym.controller.orderStoryPayMoney.IOrderStoryPayMoney;
import com.codegym.controller.orderStoryPayMoney.OrderStoryPayMoney;

import java.io.IOException;
import java.util.Scanner;

public class StoreMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        int choice = -1;
        ICustomerManagement customerManagement = CustomerManagement.getInstance();
        IOrderStoryPayMoney orderStoryPayMoney = OrderStoryPayMoney.getInstance();
        doReadFileTransaction(customerManagement);
        try {
            orderStoryPayMoney.readFile("orderStoryPayMoney.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
                    System.out.println("Tổng doanh thu cho thuê truyện trong ngày: " + total + "(VNĐ.)");
                    int total1 = orderStoryPayMoney.totalPayMoney();
                    System.out.println("Tổng doanh thu bán truyện trong ngày là: " + total1 + "(VND.)");
                    System.out.println("Tổng doanh thu trong ngày: "+(total + total1) + "(VND.)");
                    break;
                }
                case 2: {
                    System.out.println("----Hiển thị các giao dịch trong ngày----");
                    System.out.println("----DANH SÁCH GIAO DỊCH THUÊ TRUYỆN----");
                    customerManagement.displayAllTransacton();
                    System.out.println("----DANH SÁCH GIAO DỊCH BÁN TRUYỆN----");
                    orderStoryPayMoney.displayAll();
                    break;
                }
                case 3: {
                    System.out.println("----Xóa hết các giao dịch trong ngày----");
                    customerManagement.clearAllTransaction();
                    orderStoryPayMoney.clear();
                    System.out.println("Đã xóa hết các giao dịch!");

                    break;
                }
            }
            try {
                orderStoryPayMoney.writeFile("orderStoryPayMoney.txt");
            } catch (IOException e) {
                e.printStackTrace();
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
