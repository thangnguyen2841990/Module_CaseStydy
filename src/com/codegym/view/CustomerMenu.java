package com.codegym.view;

import com.codegym.controller.customer.CustomerManagement;
import com.codegym.controller.customer.ICustomerManagement;
import com.codegym.controller.orderRentStory.IOrderRentStoryManagement;
import com.codegym.controller.orderRentStory.OrderRentStoryManagement;
import com.codegym.controller.orderStoryPayMoney.IOrderStoryPayMoney;
import com.codegym.controller.orderStoryPayMoney.OrderStoryPayMoney;
import com.codegym.controller.saveOrderBuystory.ISaveOrderBuyStoryManagement;
import com.codegym.controller.saveOrderBuystory.SaveOrderBuyStoryManagement;
import com.codegym.model.Customer;
import com.codegym.model.CustomerBuyStory;

import java.io.IOException;
import java.util.Scanner;

public class CustomerMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        int choice = -1;
        ICustomerManagement customerManagement = CustomerManagement.getInstance();
        ISaveOrderBuyStoryManagement saveOrderBuyStoryManagement = SaveOrderBuyStoryManagement.getInstance();
        IOrderRentStoryManagement orderRentStoryManagement = OrderRentStoryManagement.getInstance();
        IOrderStoryPayMoney orderStoryPayMoney = OrderStoryPayMoney.getInstance();
        doReadFile(customerManagement);
        doOrderRentStory(orderRentStoryManagement);
        doReadFileOrderStory(saveOrderBuyStoryManagement);
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 9) {
                System.out.println("Menu chỉ có từ 1 - 9!");
            }
            switch (choice) {
                case 1: {
                    doShowAllCustomer(customerManagement);
                    break;
                }
                case 2: {
                    doAddNewCustomer(customerManagement);
                    break;
                }
                case 3: {
                    doUpdateCustomer(customerManagement);
                    break;
                }
                case 4: {
                    doRemoveCustomer(customerManagement);
                    break;
                }
                case 5: {
                    doFindCustomerById(customerManagement);
                    break;
                }
                case 6: {
                    doPayMoney(customerManagement,saveOrderBuyStoryManagement,orderStoryPayMoney);
                    break;

                }
                case 7: {
                    doShowCustomer15Days(customerManagement);
                    break;
                }
                case 8: {
                    doShowCustomerByAppointment(orderRentStoryManagement);
                    break;
                }
                case 9: {
                    doShowOrderStory(saveOrderBuyStoryManagement);
                    break;
                }
            }
            doWriteFile(customerManagement);
            doWriteFileOrderStory(saveOrderBuyStoryManagement);
            doWriteFileOrderStoryPayMoney(orderStoryPayMoney);
        } while (choice != 0);
    }

    private void doWriteFileOrderStoryPayMoney(IOrderStoryPayMoney orderStoryPayMoney) {
        try {
            orderStoryPayMoney.writeFile("orderStoryPayMoney.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWriteFileOrderStory(ISaveOrderBuyStoryManagement saveOrderBuyStoryManagement) {
        try {
            saveOrderBuyStoryManagement.writeFile("orderStory.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doOrderRentStory(IOrderRentStoryManagement orderRentStoryManagement) {
        try {
            orderRentStoryManagement.readFile("orderRentStory.txt");
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    private void doReadFileOrderStory(ISaveOrderBuyStoryManagement saveOrderBuyStoryManagement) {
        try {
            saveOrderBuyStoryManagement.readFile("orderStory.txt");
        } catch (IOException | ClassNotFoundException e) {

        }
    }

    private void doShowOrderStory(ISaveOrderBuyStoryManagement saveOrderBuyStoryManagement) {
        int size = saveOrderBuyStoryManagement.getSize();
        if (size == 0) {
            System.out.println("Không có đơn hàng nào!");
        } else {
            System.out.println("----Hiển thị danh sách đơn hàng khách đặt mua sách----");
            saveOrderBuyStoryManagement.displayAll();
            int total = saveOrderBuyStoryManagement.totalPayMoney();
            System.out.println("Tổng số tiền cần thanh toán: " + total + "(VND)");
        }
    }

    private void doShowCustomerByAppointment(IOrderRentStoryManagement orderRentStoryManagement) {
        System.out.println("----Hiển thị danh sách khách hàng đặt hẹn thuê truyện----");
        orderRentStoryManagement.displayAll();
    }



    private void doWriteFile(ICustomerManagement customerManagement) {
        try {
            customerManagement.writeFile("customer.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doReadFile(ICustomerManagement customerManagement) {
        try {
            customerManagement.readFile("customer.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void menu() {
        System.out.println("1. Hiển thị danh sách khách hàng thuê truyện tại cửa hàng.");
        System.out.println("2. Thêm khách hàng mới.");
        System.out.println("3. Cập nhật thông tin khách hàng.");
        System.out.println("4. Xóa khách hàng.");
        System.out.println("5. Tìm khách hàng.");
        System.out.println("6. Tính tiền cho khách hàng.");
        System.out.println("7. Hiển thị danh sách khách hàng mượn sách quá 15 ngày.");
        System.out.println("8. Hiển thị danh sách khách hàng đặt hẹn thuê sách.");
        System.out.println("9. Hiển thị danh sách khách hàng đặt mua sách.");
        System.out.println("0. Thoát.");
    }

    private void doShowCustomer15Days(ICustomerManagement customerManagement) {
        System.out.println("----Hiển thị danh sách khách hàng mượn sách quá 15 ngày----");
        customerManagement.displayCustomer15Days();
    }

    private void doPayMoney(ICustomerManagement customerManagement, ISaveOrderBuyStoryManagement saveOrderBuyStoryManagement,IOrderStoryPayMoney orderStoryPayMoney) {
        System.out.println("----Tính tiền cho khách hàng----");
        int choice = -1;
        do {
            System.out.println("1. Tính tiền cho khách thuê truyện.");
            System.out.println("2. Tính tiền cho khách mua truyện.");
            System.out.println("0. Quay lại.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 2) {
                System.err.println("Menu chỉ có 1 và 2!");
            }
            switch (choice) {
                case 1: {
                    doShowAllCustomer(customerManagement);
                    System.out.println("Nhập mã số khách hàng: ");
                    String id = inputString.nextLine();
                    int index = customerManagement.findById(id);
                    if (index == -1) {
                        System.err.println("Mã số khách hàng không đúng!");
                    } else {
                        customerManagement.getByIndex(index);
                        long payMoney = customerManagement.payMoney1(index);
                        System.out.println("Số tiền khách hàng phải trả là: " + payMoney + "(VND)");
                    }
                    break;
                }
                case 2: {
                    if (saveOrderBuyStoryManagement.getSize() == 0) {
                        System.err.println("Không có đơn hàng nào!");
                    } else {
                        doShowOrderStory(saveOrderBuyStoryManagement);
                        System.out.println("Nhập mã số truyện khách hàng đặt: ");
                        String id = inputString.nextLine();
                        int index = saveOrderBuyStoryManagement.findById(id);
                        if (index == -1) {
                            System.err.println("Mã số truyện không đúng!");
                        } else {
                            System.out.println("Tính tiền cho khách thành công!");
                            CustomerBuyStory customerBuyStory =saveOrderBuyStoryManagement.getByIndex(index);
                            orderStoryPayMoney.add(customerBuyStory);
                            saveOrderBuyStoryManagement.remove(index);
                        }
                    }
                    break;
                }
            }
        } while (choice != 0);

    }

    private void doFindCustomerById(ICustomerManagement customerManagement) {
        System.out.println("----Tìm khách hàng----");
        System.out.println("Nhập mã số khách hàng: ");
        String id = inputString.nextLine();
        int index = inputNumber.nextInt();
        if (index == -1) {
            System.out.println("Không tìm thấy khách hàng!");
        } else {
            customerManagement.getByIndex(index);
        }
    }

    private void doRemoveCustomer(ICustomerManagement customerManagement) {
        System.out.println("----Xóa khách hàng----");
        System.out.println("Nhập mã số khách hàng: ");
        String id = inputString.nextLine();
        int index = customerManagement.findById(id);
        if (index == -1) {
            System.out.println("Mã số khách hàng không đúng!");
        } else {
            customerManagement.remove(index);
            System.out.println("Đã Xóa thành công!");
        }
    }

    private void doUpdateCustomer(ICustomerManagement customerManagement) {
        System.out.println("----Cập nhật thông tin khách hàng----");
        System.out.println("Nhập mã số khách hàng: ");
        String id = inputString.nextLine();
        int index = customerManagement.findById(id);
        if (index == -1) {
            System.out.println("Mã số khách hàng không đúng!");
        } else {
            Customer newCustomer = inputNewCustomerInfo();
            customerManagement.update(index, newCustomer);
            System.out.println("Cập nhật thành công!");
        }
    }

    private void doAddNewCustomer(ICustomerManagement customerManagement) {
        System.out.println("----Thêm khách hàng mới----");
        Customer newCustomer = inputNewCustomerInfo();
        customerManagement.add(newCustomer);
        System.out.println("Đã thêm thành công!");
    }

    private Customer inputNewCustomerInfo() {
        System.out.println("Nhập thông tin khách hàng");
        System.out.println("Mã số khách hàng: ");
        String id = inputString.nextLine();
        System.out.println("Họ và tên: ");
        String name = inputString.nextLine();
        System.out.println("CMND: ");
        String indentity = inputString.nextLine();
        System.out.println("Số điện thoại: ");
        String phoneNumber = inputString.nextLine();
        System.out.println("Ngày mượn truyện: ");
        String dateBorrowed = inputString.nextLine();
        return new Customer(id, name, indentity, phoneNumber, dateBorrowed);
    }

    private void doShowAllCustomer(ICustomerManagement customerManagement) {
        System.out.println("----Hiển thị danh sách khách hàng----");
        int size = customerManagement.getSize();
        if (size == 0) {
            System.out.println("Dánh sách rỗng!");
        } else {
            customerManagement.displayAll();
        }
    }
}
