package com.codegym.controller.saveOrderBuystory;

import com.codegym.model.CustomerBuyStory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveOrderBuyStoryManagement implements ISaveOrderBuyStoryManagement {
    private SaveOrderBuyStoryManagement() {

    }

    private static final SaveOrderBuyStoryManagement INSTANCE = new SaveOrderBuyStoryManagement();

    public static SaveOrderBuyStoryManagement getInstance() {
        return INSTANCE;
    }

    private List<CustomerBuyStory> saveBuyStoryList = new ArrayList<>();

    @Override
    public void add(CustomerBuyStory customerBuyStory) {
        saveBuyStoryList.add(customerBuyStory);
    }

    @Override
    public void update(int index, CustomerBuyStory customerBuyStory) {
        saveBuyStoryList.set(index, customerBuyStory);
    }

    @Override
    public void remove(int index) {
        saveBuyStoryList.remove(index);
    }

    @Override
    public void displayAll() {
        for (CustomerBuyStory customerBuyStory: saveBuyStoryList) {
            System.out.println(customerBuyStory);
        }
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < saveBuyStoryList.size(); i++) {
            if (saveBuyStoryList.get(i).getStory().getId().equals(id)){
                index = i;
            }
        }
        return index;
    }

    @Override
    public CustomerBuyStory getByIndex(int index) {
        return saveBuyStoryList.get(index);
    }

    @Override
    public int getSize() {
        return saveBuyStoryList.size();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.saveBuyStoryList = (List<CustomerBuyStory>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.saveBuyStoryList);
    }

    @Override
    public int totalPayMoney() {
        int total = 0;
        for (int i = 0; i < saveBuyStoryList.size(); i++) {
            total = total + saveBuyStoryList.get(i).getPayMoney();
        }
        return total;
    }
}
