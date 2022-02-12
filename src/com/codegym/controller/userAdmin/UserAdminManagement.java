package com.codegym.controller.userAdmin;

public class UserAdminManagement implements IUserAdminManagement{
    private final String USERNAME = "admin";
    private final String PASSWORD = "123456";
    public static final UserAdminManagement INSTANCE = new UserAdminManagement();
    private UserAdminManagement(){
    }
    public static  UserAdminManagement getInstance(){
        return INSTANCE;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        boolean isLogin = false;
        if (username.equals(USERNAME)&&password.equals(PASSWORD)){
            isLogin = true;
        }
        return isLogin;
    }
}
