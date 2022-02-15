package com.codegym.controller.orderStoryPayMoney;

import com.codegym.model.Customer;
import com.codegym.model.CustomerBuyStory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderStoryPayMoney implements IOrderStoryPayMoney{
    private OrderStoryPayMoney(){

    }
    private static final OrderStoryPayMoney INSTANCE = new OrderStoryPayMoney();
    public static OrderStoryPayMoney getInstance(){
        return INSTANCE;
    }
    List<CustomerBuyStory> orderStoryPayMoney = new ArrayList<>();

    @Override
    public void add(CustomerBuyStory customerBuyStory) {
        orderStoryPayMoney.add(customerBuyStory);
    }

    @Override
    public void update(int index, CustomerBuyStory customerBuyStory) {
        orderStoryPayMoney.set(index, customerBuyStory);
    }

    @Override
    public void remove(int index) {
orderStoryPayMoney.remove(index);
    }

    @Override
    public void displayAll() {
        for (CustomerBuyStory customerBuyStory:orderStoryPayMoney) {
            System.out.println(customerBuyStory);
        }
    }

    @Override
    public int findById(String id) {
        return 0;
    }

    @Override
    public CustomerBuyStory getByIndex(int index) {
        return orderStoryPayMoney.get(index);
    }

    @Override
    public int getSize() {
        return orderStoryPayMoney.size();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream   ois = new ObjectInputStream(is);
        this.orderStoryPayMoney = (List<CustomerBuyStory>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.orderStoryPayMoney);
    }

    @Override
    public int totalPayMoney() {
        int total = 0;
        for (int i = 0; i < orderStoryPayMoney.size(); i++) {
            total  = total + orderStoryPayMoney.get(i).getPayMoney();
        }
        return total;
    }

    @Override
    public void clear() {
        orderStoryPayMoney.clear();
    }
}
