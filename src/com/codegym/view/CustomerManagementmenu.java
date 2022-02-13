package com.codegym.view;

import com.codegym.controller.customer.CustomerManagement;
import com.codegym.controller.customer.ICustomerManagement;
import com.codegym.controller.story.IStoryManagement;
import com.codegym.controller.story.StoryManagement;
import com.codegym.model.CustomerBuyStory;
import com.codegym.model.CustomersByAppointment;
import com.codegym.model.OrderStory;
import com.codegym.model.Story;
import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import java.io.IOException;
import java.util.Scanner;

public class CustomerManagementmenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        int choice = -1;
        ICustomerManagement customerManagement = CustomerManagement.getInstance();
        IStoryManagement storyManagement = StoryManagement.getInstance();
        doReadFileStory(storyManagement);
        doReadFileCustomerByAppointment(customerManagement);
        doReadFileCustomerBuyStory(customerManagement);
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 5) {
                System.out.println("UserLogin chỉ có từ 1 - 5!");
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
                case 2: {
                    doOrderStory(customerManagement, storyManagement);
                    break;
                }
                case 3: {
                    doShowOrderStory(customerManagement);
                    break;
                }
                case 4: {
                    System.out.println("----Cập nhật đơn hàng----");
                    int choiceUpdate = -1;
                    do {
                        System.out.println("1. Thay đổi số lượng, truyện muốn mua.");
                        System.out.println("2. Xóa bớt truyện muốn mua,");
                        System.out.println("3. Hủy đơn hàng.");
                        System.out.println("0. Quay lại.");
                        System.out.println("Nhập lựa chọn của bạn: ");
                        choiceUpdate = inputNumber.nextInt();
                        if (choiceUpdate > 3) {
                            System.out.println("Menu chỉ có từ 1 - 3!");
                        }
                        switch (choiceUpdate) {
                            case 1: {
                                doShowOrderStory(customerManagement);
                                System.out.println("Nhập số thứ tự sản phẩm cần cập nhật: ");
                                int index = inputNumber.nextInt();
                                int size = customerManagement.getSizeCustomerStory();

                                if (index - 1 < 0 || index - 1 > size - 1) {
                                    System.err.println("Số thứ tự sản phẩm không đúng!");
                                } else {
                                    CustomerBuyStory newCustomerBuyStory = creatOrderStory(storyManagement, customerManagement);
                                    customerManagement.updateCustomerBuyStory(index - 1, newCustomerBuyStory);
                                    doShowOrderStory(customerManagement);
                                }
                                break;
                            }
                            case 2: {
                                doShowOrderStory(customerManagement);
                                System.out.println("Nhập số thứ tự sản phẩm cần cập nhật: ");
                                int index = inputNumber.nextInt();
                                int size = customerManagement.getSizeCustomerStory();

                                if (index - 1 < 0 || index - 1 > size - 1) {
                                    System.err.println("Số thứ tự sản phẩm không đúng!");
                                } else {
                                    customerManagement.removeCustomerBuyStory(index - 1);
                                    doShowOrderStory(customerManagement);
                                }
                                break;
                            }
                            case 3: {
                                customerManagement.clearCustomerBuyStory();
                                doShowOrderStory(customerManagement);
                                break;
                            }
                        }
                        doWriteFileCustomerBuyStory(customerManagement);
                        doWriteFileOrderStory(customerManagement);
                    } while (choiceUpdate != 0);
                    break;
                }
                case 5: {
                    doShowAllStory(storyManagement);
                    doOrder(customerManagement);
                    break;
                }
                case 6: {
                    doShowAllHistoryOrder(customerManagement);
                    break;
                }
                case 7: {
                    doClearCustomerByAppointment(customerManagement);
                    break;
                }
            }
            writeFileCustomerByAppointment(customerManagement);
        } while (choice != 0);
    }

    private void doShowOrderStory(ICustomerManagement customerManagement) {
        if (customerManagement.getSizeCustomerStory() == 0){
            System.out.println("Không có đơn hàng nào!");
        }else {
            System.out.println("----ĐƠN HÀNG CỦA BẠN----");
            customerManagement.displayCustomerBuyStory();
            int totalPayMoney = customerManagement.totalPayMoneyCustomerBuyStory();
            System.out.println("Tổng số tiền phải thanh toán là: " + totalPayMoney + "(VND.)");
        }

    }

    private void doOrderStory(ICustomerManagement customerManagement, IStoryManagement storyManagement) {
        System.out.println("----Đặt mua truyện----");
        customerManagement.clearCustomerBuyStory();
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
                        int payMoney = customerManagement.payMoneyCustomerBuyStory(index, quanlity);
                        Story story = storyManagement.getByIndex(index);
                        CustomerBuyStory customerBuyStory = new CustomerBuyStory(story, quanlity, payMoney);
                        customerManagement.addCustomerBuyStory(customerBuyStory);
                        System.out.println("----GIỎ HÀNG CỦA BẠN----");
                        customerManagement.displayCustomerBuyStory();
                        int totalPayMoney = customerManagement.totalPayMoneyCustomerBuyStory();
                        OrderStory orderStory = new OrderStory(story, quanlity, payMoney);
                        customerManagement.addOrderStory(orderStory);
                        System.out.println("Tổng số tiền phải trả là: " + totalPayMoney + "(VND)");
                        System.out.println("Nhập số 1 đẻ tiếp tục mua hàng, số 0 đẻ thoát!");
                        int newQuanlity = storyManagement.getByIndex(index).getQuanlity() - quanlity;
                        storyManagement.getByIndex(index).setQuanlity(newQuanlity);
                        doWriteFileOrderStory(customerManagement);
                        doWriteFileCustomerBuyStory(customerManagement);
                    }
                }
            } else {
                System.err.println("Chọn '1' để 'MUA' hoặc '0' để 'THOÁT'!");
            }
        }
    }

    private CustomerBuyStory creatOrderStory(IStoryManagement storyManagement, ICustomerManagement customerManagement) {

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
                payMoney = customerManagement.payMoneyCustomerBuyStory(index, quanlity);
                story = storyManagement.getByIndex(index);

            }
        }
        return new CustomerBuyStory(story, quanlity, payMoney);
    }


    private void doWriteFileOrderStory(ICustomerManagement customerManagement) {
        try {
            customerManagement.writeFileOrderStory("orderStory.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doWriteFileCustomerBuyStory(ICustomerManagement customerManagement) {
        try {
            customerManagement.WriteFileCustomerBuyStory("customerBuyStory.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doReadFileCustomerBuyStory(ICustomerManagement customerManagement) {
        try {
            customerManagement.readFileCustomerBuyStory("customerBuyStory.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void doClearCustomerByAppointment(ICustomerManagement customerManagement) {
        System.out.println("---Xóa hết lịch sử đặt hẹn thuê truyện----");
        customerManagement.clearAllCustomerAppointment();
    }

    private void doReadFileCustomerByAppointment(ICustomerManagement customerManagement) {
        try {
            customerManagement.readFileCustomerByAppointment("customerByAppointment.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void doReadFileStory(IStoryManagement storyManagement) {
        try {
            storyManagement.readFile("story.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void writeFileCustomerByAppointment(ICustomerManagement customerManagement) {
        try {
            customerManagement.writeFileCustomerByAppointment("customerByAppointment.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doShowAllHistoryOrder(ICustomerManagement customerManagement) {
        System.out.println("----Lịch sử đặt hẹn thuê sách----");
        customerManagement.displayAllCustomerByAppointment();
    }

    private void doOrder(ICustomerManagement customerManagement) {
        System.out.println("----Đặt hẹn thuê sách----");
        CustomersByAppointment newCustomer = inputNewCustomerByAppointmentInfo();
        customerManagement.addNewCustomerByAppointment(newCustomer);
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
        System.out.println("----CỬA HÀNG CHO THUÊ TRUYỆN CODEGYM----");
        System.out.println("1. Bảng giá của cửa hàng.");
        System.out.println("2. Đặt mua sách.");
        System.out.println("3. Kiểm tra đơn hàng.");
        System.out.println("4. Cập nhật đơn hàng.");
        System.out.println("5. Đặt hẹn thuê sách.");
        System.out.println("6. Lịch sử đặt hẹn thuê sách.");
        System.out.println("7. Xóa lịch sử đặt hẹn");
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
