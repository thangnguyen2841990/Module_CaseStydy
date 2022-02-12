package com.codegym.view;

import com.codegym.controller.userCustomer.IUserCustomerManagement;
import com.codegym.controller.userStaff.IUserStaffManagement;
import com.codegym.controller.userStaff.UserStaffManagement;
import com.codegym.model.UserCustomer;
import com.codegym.model.UserStaff;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffAccountMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);
    public void run(){
        int choice = -1;
        IUserStaffManagement userStaffManagement = UserStaffManagement.getInstance();
        try {
            userStaffManagement.readFile("userStaff.txt");
        } catch (IOException | ClassNotFoundException e) {

        }
        do {
            System.out.println("1. Hiển thị tài khoản của nhân viên.");
            System.out.println("2. Tạo tài khoản cho nhân viên.");
            System.out.println("3. Đổi mật khẩu.");
            System.out.println("4. Xóa tài khoản nhân viên.");
            System.out.println("0. Quay lại.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 4){
                System.out.println("UserLogin chỉ có từ 1 - 4!");
            }
            switch (choice){
                case 1 : {
                    doShowAllAccount(userStaffManagement);
                    break;
                }
                case 2 : {
                    System.out.println("----Tạo tài khoản cho nhân viên----");
                    String username = inputUsername(userStaffManagement);
                    String password = inputPassword();
                    UserStaff newUser = new UserStaff(username, password);
                    userStaffManagement.add(newUser);
                    System.out.println("Đăng ký thành công!");
                    break;
                }
                case 3 : {
                    System.out.println("----Đổi mật khẩu của nhân viên----");
                    while (true) {
                        System.out.println("Nhập tên tài khoản: ");
                        String username = inputString.nextLine();
                        System.out.println("Nhập mật khẩu cữ: ");
                        String password = inputString.nextLine();
                        boolean isCheckLogin = userStaffManagement.checkLogin(username, password);
                        if (isCheckLogin) {
                            System.out.println("Nhập mật khẩu mới: ");
                            String newPassword = inputPassword();
                            System.out.println("Nhập lại mật khẩu mới: ");
                            String newPassword1 = inputString.nextLine();
                            if (newPassword1.equals(newPassword)) {
                                UserStaff newUser = new UserStaff(username, newPassword);
                                int index = userStaffManagement.findUserByUsername(username);
                                userStaffManagement.update(index, newUser);
                                System.out.println("Đổi mật khẩu thành công!");
                                break;
                            } else {
                                System.err.println("Mật khẩu nhập lại chưa trùng mật khẩu mới!");
                            }
                        } else {
                            System.out.println("Tên tài khoản hoặc mật khẩu không đúng!");
                        }
                    }
                    break;
                }
                case 4 : {
                    System.out.println("----Xóa tài khoản của nhân viên----");
                    System.out.println("Nhập số thứ tự tài khoản muốn xóa: ");
                    int index = inputNumber.nextInt();
                    userStaffManagement.remove(index-1);
                    System.out.println("Xóa thành công!");
                }
            }
            try {
                userStaffManagement.writeFile("userStaff.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }while(choice != 0);
    }

    private void doShowAllAccount(IUserStaffManagement userStaffManagement) {
        System.out.println("----Hiển thị tài khoản của nhân viên----");
        int size = userStaffManagement.getSize();
        if (size == 0){
            System.out.println("Danh sách rỗng!");
        }else {
            userStaffManagement.displayAll();
        }
    }
    private String inputUsername(IUserStaffManagement userStaffManagement) {
        while (true) {
            System.out.println("Nhập tên tài khoản(6 - 12 ký tự, không có ký tự đặc biệt, không ký tự in hoa): ");
            String username = inputString.nextLine();
            final String REGEX_USERNAME = "^[_a-z0-9]{6,12}$";
            boolean isCheckUsername = check(REGEX_USERNAME, username);
            boolean isCheckUsernameExist = userStaffManagement.checkUsernameExist(username);
            if (isCheckUsername && isCheckUsernameExist == false) {
                System.out.println("Tên tài khoản hợp lệ!");
                return username;
            } else if (isCheckUsername == false) {
                System.out.println("Tên tài khoản 6 - 12 ký tự, không có ký tự đặc biệt, không ký tự in hoa!");
            } else if (isCheckUsernameExist) {
                System.out.println("Tên tài khoản đã tồn tại!");
            }
        }
    }

    private String inputPassword() {
        while (true) {
            System.out.println("Nhập mật khẩu(6-12 ký tự, ít nhất 1 ký tự in hoa!): ");
            String password = inputString.nextLine();
            final String REGEX_PASSWORD = "^\\w+$";
            boolean isCheckPassword = check(REGEX_PASSWORD, password);
            if (isCheckPassword) {
                System.out.println("Mật khẩu hợp lệ!");
                return password;
            } else {
                System.out.println("Mật khẩu 6-12 ký tự, ít nhất 1 ký tự in hoa!");
            }
        }
    }
    private boolean check(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
