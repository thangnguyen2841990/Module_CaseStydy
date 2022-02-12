package com.codegym.controller.userCustomer;

import com.codegym.model.UserCustomer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserCustomerManagement implements IUserCustomerManagement {
    private List<UserCustomer> userCustomerList = new ArrayList<>();
    public static final UserCustomerManagement INSTANCE = new UserCustomerManagement();

    private UserCustomerManagement() {
        File file = new File("userCustomer.txt");
        if (file.exists()){
            try {
                readFile("userCustomer.txt");
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }

    public static UserCustomerManagement getInstance() {
        return INSTANCE;
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is  = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.userCustomerList = (List<UserCustomer>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.userCustomerList);
    }

    @Override
    public boolean checkUsernameExist(String username) {
        boolean isExist = false;
        for (int i = 0; i < getSize(); i++) {
                if (userCustomerList.get(i).getUsername().equals(username)){
                    isExist = true;
                }
        }
        return isExist;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        boolean isLogin = false;
        for (int i = 0; i < getSize(); i++) {
            if (userCustomerList.get(i).getUsername().equals(username) && userCustomerList.get(i).getPassword().equals(password)){
                isLogin =true;
            }
        }
        return isLogin;
    }

    @Override
    public int findUserByUsername(String username) {
        int index = -1;
        for (int i = 0; i < getSize(); i++) {
            if (userCustomerList.get(i).getUsername().equals(username)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public void add(UserCustomer userCustomer) {
        userCustomerList.add(userCustomer);
        try {
            writeFile("userCustomer.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int index, UserCustomer userCustomer) {
            userCustomerList.set(index, userCustomer);
    }

    @Override
    public void remove(int index) {
            userCustomerList.remove(index);
    }

    @Override
    public void displayAll() {
        for (UserCustomer userCustomer : userCustomerList) {
            System.out.println(userCustomer);
        }
    }

    @Override
    public int findById(String id) {
        int index = -1;
        return index;
    }

    @Override
    public UserCustomer getByIndex(int index) {
        return  userCustomerList.get(index);
    }

    @Override
    public int getSize() {
        return userCustomerList.size();
    }
}
