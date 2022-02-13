package com.codegym.controller.customer;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.Customer;
import com.codegym.model.CustomerBuyStory;
import com.codegym.model.CustomersByAppointment;
import com.codegym.model.OrderStory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

public interface ICustomerManagement extends GeneralManagement<Customer>, WriteFile, ReadFile {
    Date convertStringtoDate(String dateBorrowed);
    long getDaysDiff(String dateBorrowed);
    long payMoney1(int index);
    void displayCustomer15Days();
    void writeFileCustomerByAppointment(String path) throws IOException;
    void readFileCustomerByAppointment(String path) throws IOException, ClassNotFoundException;
    void addNewCustomerByAppointment(CustomersByAppointment newCustomer);
    void displayAllCustomerByAppointment();
    void clearAllCustomerAppointment();
    void writeFileTransaction(String path) throws IOException;
    void reafFileTransaction(String path) throws IOException, ClassNotFoundException;
    void displayAllTransacton();
    void clearAllTransaction();
    long totalTurnover();
    void addCustomerBuyStory(CustomerBuyStory customerBuyStory);
    void displayCustomerBuyStory();
    void clearCustomerBuyStory();
    void updateCustomerBuyStory(int index, CustomerBuyStory customerBuyStory);
    int totalPayMoneyCustomerBuyStory();
    int payMoneyCustomerBuyStory(int index, int quanlity);
    int getSizeCustomerStory();
    void removeCustomerBuyStory(int index);
    void readFileCustomerBuyStory(String path) throws IOException, ClassNotFoundException;
    void WriteFileCustomerBuyStory(String path) throws IOException;
    void addOrderStory(OrderStory orderStory);
    void clearOrderStoryList();
    void showOrderStory();
    void writeFileOrderStory(String path) throws IOException;
    void readFileOrderStory(String path) throws IOException, ClassNotFoundException;
    int totalOrderStory();

}
