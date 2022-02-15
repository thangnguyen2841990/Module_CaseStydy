package com.codegym.view;


import com.codegym.controller.orderBuyStory.IOrderBuyStoryManagement;
import com.codegym.controller.orderBuyStory.OrderBuyStoryManagement;
import com.codegym.controller.orderRentStory.IOrderRentStoryManagement;
import com.codegym.controller.orderRentStory.OrderRentStoryManagement;
import com.codegym.controller.saveOrderBuystory.ISaveOrderBuyStoryManagement;
import com.codegym.controller.saveOrderBuystory.SaveOrderBuyStoryManagement;
import com.codegym.controller.story.IStoryManagement;
import com.codegym.controller.story.StoryManagement;
import com.codegym.model.CustomerBuyStory;
import com.codegym.model.CustomersByAppointment;
import com.codegym.model.Story;


import java.io.IOException;
import java.util.Scanner;

public class CustomerManagementmenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        int choice = -1;
        IOrderBuyStoryManagement orderBuyStoryManagement = OrderBuyStoryManagement.getInstance();
        IStoryManagement storyManagement = StoryManagement.getInstance();
        IOrderRentStoryManagement orderRentStoryManagement = OrderRentStoryManagement.getInstance();
        ISaveOrderBuyStoryManagement saveOrderBuyStoryManagement = SaveOrderBuyStoryManagement.getInstance();
        try {
            storyManagement.readFile("story.txt");
        } catch (IOException | ClassNotFoundException e) {

        }
        try {
            orderRentStoryManagement.readFile("orderRentStory.txt");
        } catch (IOException | ClassNotFoundException e) {

        }
        try {
            orderBuyStoryManagement.readFile("customerBuyStory.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 8) {
                System.out.println("UserLogin chỉ có từ 1 - 8!");
            }
            switch (choice) {
                case 1: {
                    System.out.println("----BẢNG GIÁ CHO THUÊ TRUYỆN----");
                    System.out.println("Mượn từ 5 ngày trở xuống 2000(VND)/1 ngày. ");
                    System.out.println("Mượn từ 5 - 10 ngày 5000(VND)/1 ngày. ");
                    System.out.println("Mượn từ 10 - 15  ngày 10000(VND)/1 ngày. ");
                    System.out.println("Mượn từ trên 15 ngày 15000(VND)/1 ngày. ");
                    break;
                }
                case 2 : {
                    System.out.println("----DANH SÁCH TRUYỆN----");
                    storyManagement.displayAll();
                    break;
                }
                case 3: {
                    doOrderStory(orderBuyStoryManagement, storyManagement,saveOrderBuyStoryManagement);
                    break;
                }
                case 4: {
                    doShowOrderStory(orderBuyStoryManagement);
                    break;
                }
                case 5: {
                    doUpdateCustomerBuyStory(orderBuyStoryManagement, storyManagement,saveOrderBuyStoryManagement);
                    break;
                }
                case 6: {
                    doShowAllStory(storyManagement);
                    doOrder(orderRentStoryManagement);
                    break;
                }
                case 7: {
                    doShowAllHistoryOrder(orderRentStoryManagement);
                    break;
                }
            }
            try {
                storyManagement.writeFile("story.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                orderBuyStoryManagement.writeFile("customerBuyStory.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                orderRentStoryManagement.writeFile("orderRentStory.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                saveOrderBuyStoryManagement.writeFile("orderStory.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (choice != 0);
    }

    private void doUpdateCustomerBuyStory(IOrderBuyStoryManagement orderBuyStoryManagement, IStoryManagement storyManagement,
                                          ISaveOrderBuyStoryManagement saveOrderBuyStoryManagement) {
        System.out.println("----Cập nhật đơn hàng----");
        int choiceUpdate = -1;
        do {
            System.out.println("1. Thay đổi số lượng, truyện muốn mua.");
            System.out.println("2. Xóa bớt truyện muốn mua.");
            System.out.println("0. Quay lại.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choiceUpdate = inputNumber.nextInt();
            if (choiceUpdate > 2) {
                System.out.println("Menu chỉ có từ 1 - 3!");
            }
            switch (choiceUpdate) {
                case 1: {
                    doShowOrderStory(orderBuyStoryManagement);
                    System.out.println("Nhập mã số truyện: ");
                    String id = inputString.nextLine();
                    int index = orderBuyStoryManagement.findById(id);

                    if (index == -1) {
                        System.err.println("Mã số truyện không đúng!");
                    } else {
                        CustomerBuyStory newCustomerBuyStory = creatOrderStory(storyManagement, orderBuyStoryManagement);
                        orderBuyStoryManagement.update(index,newCustomerBuyStory);
                        saveOrderBuyStoryManagement.update(index,newCustomerBuyStory);
                        doShowOrderStory(orderBuyStoryManagement);
                    }
                    break;
                }
                case 2: {
                    doShowOrderStory(orderBuyStoryManagement);
                    System.out.println("Nhập mã số truyện: ");
                    String id = inputString.nextLine();
                    int index = orderBuyStoryManagement.findById(id);

                    if (index == -1) {
                        System.err.println("Mã số truyện không đúng!");
                    } else {
                        orderBuyStoryManagement.remove(index);
                        saveOrderBuyStoryManagement.remove(index);
                        System.out.println("Đã xóa thành công!");
                    }
                    break;
                }

            }
        } while (choiceUpdate != 0);
    }

    private void doShowOrderStory(IOrderBuyStoryManagement orderBuyStoryManagement) {
        if (orderBuyStoryManagement.getSize() == 0){
            System.out.println("Không có đơn hàng nào!");
        }else {
            System.out.println("----ĐƠN HÀNG CỦA BẠN----");
            orderBuyStoryManagement.displayAll();
            int totalPayMoney = orderBuyStoryManagement.totalPayMoney();
            System.out.println("Tổng số tiền phải thanh toán là: " + totalPayMoney + "(VND.)");
        }

    }

    private void doOrderStory(IOrderBuyStoryManagement orderBuyStoryManagement, IStoryManagement storyManagement,ISaveOrderBuyStoryManagement saveOrderBuyStoryManagement) {
        System.out.println("----Đặt mua truyện----");
        orderBuyStoryManagement.clear();
        int choiceBuy = -1;
        while (true) {
            System.out.println("----DANH SÁCH TRUYỆN CỦA CỬA HÀNG----");
            doShowAllStory(storyManagement);
            System.out.println("1. Mua.");
            System.out.println("0. Thoát");
            System.out.println("Nhập lựa chọn của bạn: ");
            choiceBuy = inputNumber.nextInt();
            if (choiceBuy == 0) {
                break;
            }
            if (choiceBuy == 1) {
                System.out.println("Nhập mã số truyện: ");
                String storyId = inputString.nextLine();
                int index = storyManagement.findById(storyId);
                if (index == -1) {
                    System.err.println("Mã số sản phẩm không đúng!");
                } else {
                    System.out.println("Số lượng muốn mua: ");
                    int quanlity = inputNumber.nextInt();
                    int quanlityStory = storyManagement.getByIndex(index).getQuanlity();
                    if (quanlity > quanlityStory) {
                        System.err.println("Số lượng sách trong kho không đủ!");
                    } else {
                        int payMoney = orderBuyStoryManagement.payMoney(index, quanlity);
                        Story story = storyManagement.getByIndex(index);
                        CustomerBuyStory customerBuyStory = new CustomerBuyStory(story, quanlity, payMoney);
                        orderBuyStoryManagement.add(customerBuyStory);
                        System.out.println("----GIỎ HÀNG CỦA BẠN----");
                        orderBuyStoryManagement.displayAll();
                        int totalPayMoney = orderBuyStoryManagement.totalPayMoney();
                        System.out.println("Tổng số tiền phải trả là: " + totalPayMoney + "(VND)");
                        int newQuanlity = quanlityStory - quanlity;
                        storyManagement.getByIndex(index).setQuanlity(newQuanlity);
                        saveOrderBuyStoryManagement.add(customerBuyStory);
                    }
                }
            }
        }
    }

    private CustomerBuyStory creatOrderStory(IStoryManagement storyManagement, IOrderBuyStoryManagement orderBuyStoryManagement) {

        System.out.println("----Danh sách truyện của cửa hàng----");
        storyManagement.displayAll();
        System.out.println("Nhập mã số truyện: ");
        String storyId = inputString.nextLine();
        int index = storyManagement.findById(storyId);
        Story story = null;
        int quanlity = 0;
        int payMoney = 0;
        if (index == -1) {
            System.err.println("Mã số truyện không đúng!");
        } else {
            System.out.println("Số lượng muốn mua: ");
            quanlity = inputNumber.nextInt();
            int quanlityStory = storyManagement.getByIndex(index).getQuanlity();
            if (quanlity > quanlityStory) {
                System.err.println("Số lượng sách trong kho không đủ!");
            } else {
                payMoney = orderBuyStoryManagement.payMoney(index,quanlity);
                story = storyManagement.getByIndex(index);
            }
        }
        return new CustomerBuyStory(story, quanlity, payMoney);
    }
    private void doShowAllHistoryOrder(IOrderRentStoryManagement orderRentStoryManagement) {
        System.out.println("----Lịch sử đặt hẹn thuê sách----");
        orderRentStoryManagement.displayAll();
    }

    private void doOrder(IOrderRentStoryManagement orderRentStoryManagement) {
        System.out.println("----Đặt hẹn thuê sách----");
        CustomersByAppointment newCustomer = inputNewCustomerByAppointmentInfo();
        orderRentStoryManagement.add(newCustomer);
        System.out.println("Bạn đã đặt lịch hẹn thành công!");
    }

    private void doShowAllStory(IStoryManagement storyManagement) {
        System.out.println("----Hiển thị danh sách truyện của cửa hàng----");
        int size = storyManagement.getSize();
        if (size == 0) {
            System.out.println("Danh sách rỗng!");
        } else {
            storyManagement.displayAll();
        }
    }

    private void menu() {
        System.out.println("----CỬA HÀNG BÁN & CHO THUÊ TRUYỆN CODEGYM----");
        System.out.println("1. Bảng giá của cửa hàng.");
        System.out.println("2. Hiển thị danh sách truyện.");
        System.out.println("3. Đặt mua sách.");
        System.out.println("4. Kiểm tra đơn hàng.");
        System.out.println("5. Cập nhật đơn hàng.");
        System.out.println("6. Đặt hẹn thuê sách.");
        System.out.println("7. Lịch sử đặt hẹn thuê sách.");
        System.out.println("0. Thoát.");
    }

    private CustomersByAppointment inputNewCustomerByAppointmentInfo() {
        System.out.println("Nhập thông tin khách hàng: ");
        System.out.println("Họ và tên: ");
        String name = inputString.nextLine();
        System.out.println("Số điện thoại: ");
        String phoneNumber = inputString.nextLine();
        System.out.println("Mã số sách bạn muốn thuê: ");
        String id = inputString.nextLine();
        System.out.println("Thời gian bạn có thể đến cửa hàng: ");
        String time = inputString.nextLine();
        return new CustomersByAppointment(name, phoneNumber, id, time);
    }
}
