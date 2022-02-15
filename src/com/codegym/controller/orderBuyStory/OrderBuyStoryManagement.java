package com.codegym.controller.orderBuyStory;

import com.codegym.controller.story.IStoryManagement;
import com.codegym.controller.story.StoryManagement;
import com.codegym.model.CustomerBuyStory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OrderBuyStoryManagement implements IOrderBuyStoryManagement {
    private OrderBuyStoryManagement() {

    }

    private static final OrderBuyStoryManagement INSTANCE = new OrderBuyStoryManagement();

    public static OrderBuyStoryManagement getInstance() {
        return INSTANCE;
    }

    private List<CustomerBuyStory> customerBuyStoryList = new ArrayList<>();
    IStoryManagement storyManagement = StoryManagement.getInstance();

    @Override
    public void add(CustomerBuyStory customerBuyStory) {
        customerBuyStoryList.add(customerBuyStory);
    }

    @Override
    public void update(int index, CustomerBuyStory customerBuyStory) {
        int indexStory = storyManagement.findById(customerBuyStoryList.get(index).getStory().getId());
        int quanlityStory = storyManagement.getByIndex(indexStory).getQuanlity();
        int oldQuanlity = customerBuyStoryList.get(index).getQuanlity();
        customerBuyStoryList.set(index, customerBuyStory);
        int newQuanlity = customerBuyStoryList.get(index).getQuanlity();
        if (newQuanlity > oldQuanlity) {
            storyManagement.getByIndex(indexStory).setQuanlity(quanlityStory - (newQuanlity - oldQuanlity));
        } else if (newQuanlity < oldQuanlity) {
            storyManagement.getByIndex(indexStory).setQuanlity(quanlityStory + (oldQuanlity - newQuanlity));
        }
    }

    @Override
    public void remove(int index) {
        String idStory = customerBuyStoryList.get(index).getStory().getId();
        int indexStory = storyManagement.findById(idStory);
        int quanlityStory = storyManagement.getByIndex(index).getQuanlity();
        storyManagement.getByIndex(indexStory).setQuanlity
                (quanlityStory + customerBuyStoryList.get(index).getQuanlity());
        customerBuyStoryList.remove(index);

    }

    @Override
    public void displayAll() {
        for (int i = 0; i < customerBuyStoryList.size(); i++) {
            System.out.println(customerBuyStoryList.get(i));
        }
    }

    @Override
    public int findById(String id) {
        int index  = -1;
        for (int i = 0; i <customerBuyStoryList.size() ; i++) {
            if (customerBuyStoryList.get(i).getStory().getId().equals(id)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public CustomerBuyStory getByIndex(int index) {
        return customerBuyStoryList.get(index);
    }

    @Override
    public int getSize() {
        return customerBuyStoryList.size();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.customerBuyStoryList = (List<CustomerBuyStory>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.customerBuyStoryList);
    }

    @Override
    public int payMoney(int index, int quanlity) {
        int payMoney = 0;
        payMoney = storyManagement.getByIndex(index).getPrice() * quanlity;
        return payMoney;
    }

    @Override
    public int totalPayMoney() {
        int total = 0;
        for (int i = 0; i < customerBuyStoryList.size(); i++) {
            total = total + customerBuyStoryList.get(i).getPayMoney();
        }
        return total;
    }

    @Override
    public void clear() {
        customerBuyStoryList.clear();
    }
}
