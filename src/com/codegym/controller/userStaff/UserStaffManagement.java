package com.codegym.controller.userStaff;

import com.codegym.model.UserStaff;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserStaffManagement implements IUserStaffManagement<UserStaff> {
    private List<UserStaff> userStaffList = new ArrayList<>();
    public static final UserStaffManagement INSTANCE = new UserStaffManagement();

    private UserStaffManagement() {

    }

    public static UserStaffManagement getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean checkUsernameExist(String username) {
        boolean isExist = false;
        for (int i = 0; i < getSize(); i++) {
            if (userStaffList.get(i).getUsername().equals(username)) {
                isExist = true;
            }
        }
        return isExist;
    }

    @Override
    public boolean checkLogin(String usernam, String password) {
        boolean isLogin = false;
        for (int i = 0; i < getSize(); i++) {
            if (userStaffList.get(i).getUsername().equals(usernam) && userStaffList.get(i).getPassword().equals(password)) {
                isLogin = true;
            }
        }
        return isLogin;
    }

    @Override
    public int findUserByUsername(String username) {
        int index = -1;
        for (int i = 0; i < getSize(); i++) {
            if (userStaffList.get(i).getUsername().equals(username)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public void add(UserStaff userStaff) {
        userStaffList.add(userStaff);
    }

    @Override
    public void update(int index, UserStaff userStaff) {
        userStaffList.set(index, userStaff);
    }

    @Override
    public void remove(int index) {
        userStaffList.remove(index);
    }

    @Override
    public void displayAll() {
        for (int i = 0; i < getSize(); i++) {
            System.out.println((i + 1) + ". " + userStaffList.get(i));
        }
    }

    @Override
    public int findById(String id) {
        return 0;
    }

    @Override
    public UserStaff getByIndex(int index) {
    return userStaffList.get(index);
    }

    @Override
    public int getSize() {
        return userStaffList.size();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.userStaffList = (List<UserStaff>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.userStaffList);
    }
}
