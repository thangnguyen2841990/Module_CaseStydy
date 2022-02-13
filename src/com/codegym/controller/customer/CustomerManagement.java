package com.codegym.controller.customer;

import com.codegym.controller.story.IStoryManagement;
import com.codegym.controller.story.StoryManagement;
import com.codegym.model.*;
import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CustomerManagement extends Thread implements ICustomerManagement {
    private List<Customer> customerList = new ArrayList<>();
    private List<Transaction> transactionList = new ArrayList<>();
    private List<CustomersByAppointment> customersByAppointments = new ArrayList<>();
    private List<CustomerBuyStory> customerBuyStoryList = new ArrayList<>();
    private IStoryManagement storyManagement = StoryManagement.getInstance();
    private List<OrderStory> orderStoryList = new ArrayList<>();

    public static final CustomerManagement INSTANCE = new CustomerManagement();


    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }


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
        return  customerList.get(index);
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
            payMoney = 10000 % daysDiff;
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
    public void writeFileCustomerByAppointment(String path) throws IOException {
        OutputStream os = new FileOutputStream("customerByAppointment.txt");
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.customersByAppointments);

    }

    @Override
    public void readFileCustomerByAppointment(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream("customerByAppointment.txt");
        ObjectInputStream ois = new ObjectInputStream(is);
        this.customersByAppointments = (List<CustomersByAppointment>) ois.readObject();
    }

    @Override
    public void addNewCustomerByAppointment(CustomersByAppointment newCustomer) {
        customersByAppointments.add(newCustomer);
    }

    @Override
    public void displayAllCustomerByAppointment() {
        for (CustomersByAppointment customer : customersByAppointments) {
            System.out.println(customer);
        }
    }

    @Override
    public void clearAllCustomerAppointment() {
        this.customersByAppointments.clear();
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

    @Override
    public void addCustomerBuyStory(CustomerBuyStory customerBuyStory) {
        customerBuyStoryList.add(customerBuyStory);
    }

    @Override
    public void displayCustomerBuyStory() {
        for (int i = 0; i < customerBuyStoryList.size(); i++) {
            System.out.println((i + 1) + " - " + customerBuyStoryList.get(i));
        }
    }

    @Override
    public void clearCustomerBuyStory() {
        customerBuyStoryList.clear();
    }

    @Override
    public void updateCustomerBuyStory(int index, CustomerBuyStory customerBuyStory) {
        customerBuyStoryList.set(index,customerBuyStory);
    }

    @Override
    public int totalPayMoneyCustomerBuyStory() {
        int total = 0;
        for (int i = 0; i < customerBuyStoryList.size(); i++) {
            total = total + customerBuyStoryList.get(i).getPayMoney();
        }
        return total;
    }

    @Override
    public int payMoneyCustomerBuyStory(int index, int quanlity) {
        int payMoney = 1;
        payMoney = storyManagement.getByIndex(index).getPrice() * quanlity;
        return payMoney;
    }

    @Override
    public int getSizeCustomerStory() {
        return customerBuyStoryList.size();
    }

    @Override
    public void removeCustomerBuyStory(int index) {
        customerBuyStoryList.remove(index);
    }

    @Override
    public void readFileCustomerBuyStory(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois   = new ObjectInputStream(is);
        this.customerBuyStoryList  = (List<CustomerBuyStory>) ois.readObject();
    }

    @Override
    public void WriteFileCustomerBuyStory(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.customerBuyStoryList);
    }

    @Override
    public void addOrderStory(OrderStory orderStory) {
        orderStoryList.add(orderStory);
    }

    @Override
    public void clearOrderStoryList() {
    orderStoryList.clear();
    }

    @Override
    public void showOrderStory() {
        for (OrderStory orderStory: orderStoryList) {
            System.out.println(orderStory);
        }
    }

    @Override
    public void writeFileOrderStory(String path) throws IOException {
            OutputStream os = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(this.orderStoryList);
    }

    @Override
    public void readFileOrderStory(String path) throws IOException, ClassNotFoundException {
            InputStream is = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(is);
            this.orderStoryList = (List<OrderStory>) ois.readObject();
    }

    @Override
    public int totalOrderStory() {
        int total = 0;
        for (int i = 0; i < orderStoryList.size(); i++) {
            total = total + orderStoryList.get(i).getPayMoney();
        }
        return total;
    }


}
