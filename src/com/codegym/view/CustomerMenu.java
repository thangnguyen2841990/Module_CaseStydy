package com.codegym.view;

import com.codegym.controller.customer.CustomerManagement;
import com.codegym.controller.customer.ICustomerManagement;
import com.codegym.model.Customer;

import java.io.IOException;
import java.util.Scanner;

public class CustomerMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        int choice = -1;
        ICustomerManagement customerManagement = CustomerManagement.getInstance();
        doReadFile(customerManagement);
        doReadFileCustomerByAppointMent(customerManagement);
        doReadFileOrderStory(customerManagement);
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 8) {
                System.out.println("UserLogin chỉ có từ 1 - 9!");
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
                    doPayMoney(customerManagement);
                    break;

                }
                case 7: {
                    doShowCustomer15Days(customerManagement);
                    break;
                }
                case 8 : {
                    doShowCustomerByAppointment(customerManagement);
                    break;
                }
                case 9 : {
                    doShowOrderStory(customerManagement);
                    break;
                }
            }
            doWriteFile(customerManagement);
            doWriteFileOrderStory(customerManagement);
        } while (choice != 0);
    }

    private void doShowOrderStory(ICustomerManagement customerManagement) {
        System.out.println("----Hiển thị danh sách đơn hàng khách đặt mua sách----");
        customerManagement.showOrderStory();
        int total = customerManagement.totalOrderStory();
        System.out.println("Tổng số tiền cần thanh toán: "+ total+("VND"));
    }

    private void doWriteFileOrderStory(ICustomerManagement customerManagement) {
        try {
            customerManagement.writeFileOrderStory("orderStory.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doReadFileOrderStory(ICustomerManagement customerManagement) {
        try {
            customerManagement.readFileOrderStory("orderStory.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void doShowCustomerByAppointment(ICustomerManagement customerManagement) {
        System.out.println("----Hiển thị danh sách khách hàng đặt hẹn thuê truyện----");
        customerManagement.displayAllCustomerByAppointment();
    }

    private void doReadFileCustomerByAppointMent(ICustomerManagement customerManagement) {
        try {
            customerManagement.readFileCustomerByAppointment("customerByAppointment.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        System.out.println("1. Hiển thị danh sách khách hàng.");
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

    private void doPayMoney(ICustomerManagement customerManagement) {
        System.out.println("----Tính tiền cho khách hàng----");
        System.out.println("Nhập mã số khách hàng: ");
        String id = inputString.nextLine();
        int index = customerManagement.findById(id);
        if (index == -1){
            System.err.println("Mã số khách hàng không đúng!");
        }else {
            customerManagement.getByIndex(index);
            long payMoney = customerManagement.payMoney1(index);
            System.out.println("Số tiền khách hàng phải trả là: "+ payMoney+"(VND)");
        }
    }

    private void doFindCustomerById(ICustomerManagement customerManagement) {
        System.out.println("----Tìm khách hàng----");
        System.out.println("Nhập mã số khách hàng: ");
        String id = inputString.nextLine();
        int index = inputNumber.nextInt();
        if (index == -1){
            System.out.println("Không tìm thấy khách hàng!");
        }else {
            customerManagement.getByIndex(index);
        }
    }

    private void doRemoveCustomer(ICustomerManagement customerManagement) {
        System.out.println("----Xóa khách hàng----");
        System.out.println("Nhập mã số khách hàng: ");
        String id = inputString.nextLine();
        int index = customerManagement.findById(id);
        if (index == -1){
            System.out.println("Mã số khách hàng không đúng!");
        }else {
            customerManagement.remove(index);
            System.out.println("Đã Xóa thành công!");
        }
    }

    private void doUpdateCustomer(ICustomerManagement customerManagement) {
        System.out.println("----Cập nhật thông tin khách hàng----");
        System.out.println("Nhập mã số khách hàng: ");
        String id = inputString.nextLine();
        int index = customerManagement.findById(id);
        if (index == -1 ){
            System.out.println("Mã số khách hàng không đúng!");
        }else {
            Customer newCustomer = inputNewCustomerInfo();
            customerManagement.update(index,newCustomer);
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
