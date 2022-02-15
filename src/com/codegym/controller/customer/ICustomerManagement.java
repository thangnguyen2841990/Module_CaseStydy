package com.codegym.controller.customer;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.Customer;
import com.codegym.model.OrderStory;
import java.io.IOException;
import java.util.Date;


public interface ICustomerManagement extends GeneralManagement<Customer>, WriteFile, ReadFile {
    Date convertStringtoDate(String dateBorrowed);

    long getDaysDiff(String dateBorrowed);

    long payMoney1(int index);

    void displayCustomer15Days();

    void writeFileTransaction(String path) throws IOException;

    void reafFileTransaction(String path) throws IOException, ClassNotFoundException;

    void displayAllTransacton();

    void clearAllTransaction();

    long totalTurnover();

}