package com.codegym.view;

import com.codegym.controller.userAdmin.IUserAdminManagement;
import com.codegym.controller.userAdmin.UserAdminManagement;
import com.codegym.controller.userCustomer.IUserCustomerManagement;
import com.codegym.controller.userCustomer.UserCustomerManagement;
import com.codegym.controller.userStaff.IUserStaffManagement;
import com.codegym.controller.userStaff.UserStaffManagement;
import com.codegym.model.UserCustomer;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserLogin {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    public void run() {
        int choice = -1;
        IUserAdminManagement userAdminManagement = UserAdminManagement.getInstance();
        IUserStaffManagement userStaffManagement = UserStaffManagement.getInstance();
        IUserCustomerManagement userCustomerManagement = UserCustomerManagement.getInstance();
        AdminManagementMenu adminManagementMenu = new AdminManagementMenu();
        StaffManagementMenu staffManagementMenu = new StaffManagementMenu();
        CustomerManagementmenu customerManagementmenu = new CustomerManagementmenu();
        doReadFileUserStaff(userStaffManagement);
        doReadFileUserCustomer(userCustomerManagement);
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 4) {
                System.err.println("UserLogin chỉ có từ 1 - 4!");
            }
            switch (choice) {
                case 1: {
                    doLogin(userAdminManagement, userStaffManagement, userCustomerManagement, adminManagementMenu, staffManagementMenu, customerManagementmenu);
                    break;
                }
                case 2: {
                    doRegister(userCustomerManagement);
                    break;
                }
                case 3: {
                    doChangePassword(userCustomerManagement);
                    break;
                }
                case 4 : {
                    System.out.println("----Lấy lại mật khẩu----");
                    System.out.println("Nhập tên tài khoản: ");
                    String username = inputString.nextLine();
                    int index = userCustomerManagement.findUserByUsername(username);
                    if (index == -1){
                        System.err.println("Tên tài khoản không đúng!");
                    }else{
                        System.out.println(userCustomerManagement.getByIndex(index));

                    }
                }
            }
            doWriteFileUserCustomer(userCustomerManagement);
        } while (choice != 0);

    }

    private void doLogin(IUserAdminManagement userAdminManagement, IUserStaffManagement userStaffManagement, IUserCustomerManagement userCustomerManagement, AdminManagementMenu adminManagementMenu, StaffManagementMenu staffManagementMenu, CustomerManagementmenu customerManagementmenu) {
        System.out.println("----Đăng nhập----");
        System.out.println("Nhập tên tài khoản: ");
        String username = inputString.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = inputString.nextLine();
        boolean isAdminLogin = userAdminManagement.checkLogin(username, password);
        boolean isStaffLogin = userStaffManagement.checkLogin(username, password);
        boolean isCustomerLogin = userCustomerManagement.checkLogin(username, password);
        if (isAdminLogin) {
            adminManagementMenu.run();
        } else if (isStaffLogin) {
            staffManagementMenu.run();
        } else if (isCustomerLogin) {
            customerManagementmenu.run();
        } else {
            System.err.println("Tên tài khoản hoặc mật khẩu không đúng!");
        }
    }

    private void doWriteFileUserCustomer(IUserCustomerManagement userCustomerManagement) {
        try {
            userCustomerManagement.writeFile("userCustomer.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doReadFileUserCustomer(IUserCustomerManagement userCustomerManagement) {
        try {
            userCustomerManagement.readFile("userCustomer.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void doReadFileUserStaff(IUserStaffManagement userStaffManagement) {
        try {
            userStaffManagement.readFile("userStaff.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void menu() {
        System.out.println("----CỬA HÀNG CHO THUÊ TRUYỆN CODEGYM----");
        System.out.println("1. Đăng nhập.");
        System.out.println("2. Đăng ký");
        System.out.println("3. Đổi mật khẩu.");
        System.out.println("4. Lấy lại mật khẩu.");
        System.out.println("0. Thoát.");
    }

    private void doChangePassword(IUserCustomerManagement userCustomerManagement) {
        System.out.println("----Đổi mật khẩu----");
        while (true) {
            System.out.println("Nhập tên tài khoản: ");
            String username = inputString.nextLine();
            System.out.println("Nhập mật khẩu cữ: ");
            String password = inputString.nextLine();
            boolean isCheckLogin = userCustomerManagement.checkLogin(username, password);
            if (isCheckLogin) {
                System.out.println("Nhập mật khẩu mới: ");
                String newPassword = inputPassword();
                System.out.println("Nhập lại mật khẩu mới: ");
                String newPassword1 = inputString.nextLine();
                if (newPassword1.equals(newPassword)) {
                    UserCustomer newUser = new UserCustomer(username, newPassword);
                    int index = userCustomerManagement.findUserByUsername(username);
                    userCustomerManagement.update(index, newUser);
                    System.out.println("Đổi mật khẩu thành công!");
                    break;
                } else {
                    System.err.println("Mật khẩu nhập lại chưa trùng mật khẩu mới!");
                }
            } else {
                System.err.println("Tên tài khoản hoặc mật khẩu không đúng!");
            }
        }
    }

    private void doRegister(IUserCustomerManagement userCustomerManagemen) {
        System.out.println("----Đăng ký----");
        String username = inputUsername(userCustomerManagemen);
        String password = inputPassword();
        UserCustomer newUser = new UserCustomer(username, password);
        userCustomerManagemen.add(newUser);
        System.out.println("Đăng ký thành công!");
    }

    private boolean check(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private String inputUsername(IUserCustomerManagement userStaffManagement) {
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
                System.err.println("Tên tài khoản 6 - 12 ký tự, không có ký tự đặc biệt, không ký tự in hoa!");
            } else if (isCheckUsernameExist) {
                System.out.println("Tên tài khoản đã tồn tại!");
            }
        }
    }

    private String inputPassword() {
        while (true) {
            System.out.println("Nhập mật khẩu(6-12 ký tự,có thể có ký tự đặc biệt, ít nhất 1 ký tự in hoa!): ");
            String password = inputString.nextLine();
            final String REGEX_PASSWORD = "^.*[A-Z].*$";
            boolean isCheckPassword = check(REGEX_PASSWORD, password);
            if (isCheckPassword) {
                System.out.println("Mật khẩu hợp lệ!");
                return password;
            } else {
                System.out.println("Mật khẩu 6-12 ký tự, ít nhất 1 ký tự in hoa!");
            }
        }
    }
}
