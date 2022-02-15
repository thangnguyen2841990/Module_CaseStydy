package com.codegym.controller.customer;

import com.codegym.controller.story.IStoryManagement;
import com.codegym.controller.story.StoryManagement;
import com.codegym.model.*;
import com.sun.corba.se.spi.orbutil.fsm.Input;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CustomerManagement extends Thread implements ICustomerManagement {
    private List<Customer> customerList = new ArrayList<>();
    private List<Transaction> transactionList = new ArrayList<>();
    public static final CustomerManagement INSTANCE = new CustomerManagement();

    private CustomerManagement() {
    }

    public static CustomerManagement getInstance() {
        return INSTANCE;
    }


    @Override
    public void add(Customer customer) {
        customerList.add(customer);
    }

    @Override
    public void update(int index, Customer customer) {
        customerList.set(index, customer);
    }

    @Override
    public void remove(int index) {
        customerList.remove(index);
    }

    @Override
    public void displayAll() {
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < getSize(); i++) {
            if (customerList.get(i).getId().equals(id)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public Customer getByIndex(int index) {
        return customerList.get(index);
    }

    @Override
    public int getSize() {
        return customerList.size();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.customerList = (List<Customer>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.customerList);
    }

    @Override
    public Date convertStringtoDate(String dateBorrowed) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateBorrowed);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public long getDaysDiff(String dateBorrowed) {
        Date endDate = new Date();
        Date starDate = convertStringtoDate(dateBorrowed);
        long getDiff = endDate.getTime() - starDate.getTime();
        long getDaysDiff = TimeUnit.MILLISECONDS.toDays(getDiff);
        return getDaysDiff;
    }

    @Override
    public long payMoney1(int index) {
        long payMoney = 0;
        String dateBorrowed = customerList.get(index).getDateBorrowed();
        long daysDiff = getDaysDiff(dateBorrowed);
        if (daysDiff <= 5) {
            payMoney = 2000 * daysDiff;
        } else if (daysDiff > 5 && daysDiff <= 10) {
            payMoney = 5000 * daysDiff;
        } else if (daysDiff > 10 && daysDiff <= 15) {
            payMoney = 10000 * daysDiff;
        } else if (daysDiff > 15) {
            payMoney = 20000 * daysDiff;
        }
        DaysDiff daysDiff1 = new DaysDiff(customerList.get(index), daysDiff);
        transactionList.add(new Transaction(daysDiff1, payMoney));
        try {
            writeFileTransaction("transaction.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return payMoney;
    }


    @Override
    public void displayCustomer15Days() {
        List<DaysDiff> customerDaysDiff = new ArrayList<>();
        for (int i = 0; i < getSize(); i++) {
            if (getDaysDiff(customerList.get(i).getDateBorrowed()) > 15) {
                customerDaysDiff.add(new DaysDiff(customerList.get(i), getDaysDiff(customerList.get(i).getDateBorrowed())));
            }
        }
        if (customerDaysDiff.size() == 0) {
            System.out.println("Không có khách hàng nào mượn sách quá 15 ngày!");
        } else {
            doSortCustomerDaysDiff(customerDaysDiff);
            for (DaysDiff customer : customerDaysDiff) {
                System.out.println(customer);
            }
        }
    }

    private void doSortCustomerDaysDiff(List<DaysDiff> customerDaysDiff) {
        for (int i = 0; i < customerDaysDiff.size() - 1; i++) {
            for (int j = customerDaysDiff.size() - 1; j > i; j--) {
                if (customerDaysDiff.get(j).getDaysDiff() > customerDaysDiff.get(j - 1).getDaysDiff()) {
                    DaysDiff temp = customerDaysDiff.get(j);
                    customerDaysDiff.set(j, customerDaysDiff.get(j - 1));
                    customerDaysDiff.set(j - 1, temp);
                }
            }
        }
    }


    @Override
    public void writeFileTransaction(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.transactionList);
    }

    @Override
    public void reafFileTransaction(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.transactionList = (List<Transaction>) ois.readObject();
    }

    @Override
    public void displayAllTransacton() {
        for (int i = 0; i < transactionList.size(); i++) {
            System.out.println((i + 1) + ". " + transactionList.get(i));
        }
    }

    @Override
    public void clearAllTransaction() {
        transactionList.clear();
    }

    @Override
    public long totalTurnover() {
        long total = 0;
        for (int i = 0; i < transactionList.size(); i++) {
            total = total + transactionList.get(i).getPayMoney();
        }
        return total;
    }


}
