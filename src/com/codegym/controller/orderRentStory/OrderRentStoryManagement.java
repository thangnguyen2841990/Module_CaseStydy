package com.codegym.controller.orderRentStory;

import com.codegym.controller.orderBuyStory.OrderBuyStoryManagement;
import com.codegym.model.CustomersByAppointment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRentStoryManagement implements IOrderRentStoryManagement {
    private OrderRentStoryManagement() {

    }

    private static final OrderRentStoryManagement INSTANCE = new OrderRentStoryManagement();

    public static OrderRentStoryManagement getInstance() {
        return INSTANCE;
    }

    private List<CustomersByAppointment> orderRentStoryList = new ArrayList<>();

    @Override
    public void add(CustomersByAppointment customersByAppointment) {
        orderRentStoryList.add(customersByAppointment);
    }

    @Override
    public void update(int index, CustomersByAppointment customersByAppointment) {
        orderRentStoryList.set(index, customersByAppointment);
    }

    @Override
    public void remove(int index) {
        orderRentStoryList.remove(index);
    }

    @Override
    public void displayAll() {
        for (CustomersByAppointment customer: orderRentStoryList) {
            System.out.println(customer);
        }
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < orderRentStoryList.size(); i++) {
            if (orderRentStoryList.get(i).getStoryId().equals(id)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public CustomersByAppointment getByIndex(int index) {
        return orderRentStoryList.get(index);
    }

    @Override
    public int getSize() {
        return orderRentStoryList.size();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.orderRentStoryList = (List<CustomersByAppointment>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.orderRentStoryList);
    }
}
