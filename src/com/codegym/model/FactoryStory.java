package com.codegym.model;

import java.util.Scanner;

public class FactoryStory {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);

    private Story story;
    public static Story getStory(int type){
        Story newStory = null;
        switch (type){
            case 1 : {
                newStory = inputNewComic();
                break;
            }
            case 2 : {
                newStory = inputNewNovel();
                break;
            }
        }
        return newStory;
    }
    private static Comic inputNewComic(){
        System.out.println("Nhập thông tin truyện tranh");
        System.out.println("Mã số: ");
        String id = inputString.nextLine();
        System.out.println("Tên truyên: ");
        String name = inputString.nextLine();
        System.out.println("Giá: ");
        int price = inputNumber.nextInt();
        System.out.println("Số lượng: ");
        int quanlity = inputNumber.nextInt();
        System.out.println("Tập: ");
        int part = inputNumber.nextInt();
        return new Comic(id,name,price,quanlity,part);
    }
    private static Novel inputNewNovel(){
        System.out.println("Nhập thông tin tiểu thuyết");
        System.out.println("Mã số: ");
        String id = inputString.nextLine();
        System.out.println("Tên truyên: ");
        String name = inputString.nextLine();
        System.out.println("Giá: ");
        int price = inputNumber.nextInt();
        System.out.println("Số lượng: ");
        int quanlity = inputNumber.nextInt();
        System.out.println("Thể loại: ");
        String type = inputString.nextLine();
        return new Novel(id,name,price,quanlity,type);
    }
}
