package com.codegym.view;

import com.codegym.controller.story.IStoryManagement;
import com.codegym.controller.story.StoryManagement;
import com.codegym.model.FactoryStory;
import com.codegym.model.Story;

import java.io.IOException;
import java.util.Scanner;

public class StoryMenu {
    private static Scanner inputNumber = new Scanner(System.in);
    private static Scanner inputString = new Scanner(System.in);
    public void run(){
        int choice = -1;
        IStoryManagement storyManagement = StoryManagement.getInstance();
        doReadFile(storyManagement);
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 6 ){
                System.out.println("UserLogin chỉ có từ 1 - 6!");
            }
            switch (choice) {
                case 1 : {
                    doShowAllStory(storyManagement);
                    break;
                }
                case 2 : {
                    doAddNewStory(storyManagement);
                    break;
                }
                case 3 : {
                    doUpdateStory(storyManagement);
                    break;
                }
                case 4 : {
                    doRemoveStory(storyManagement);
                    break;
                }
                case 5 : {
                    doFindStoryByID(storyManagement);
                    break;
                }
                case 6 : {
                    System.out.println("Nhập má số truyện: ");
                    String id = inputString.nextLine();
                    int index = storyManagement.findById(id);
                    if (index == -1 ){
                        System.err.println("Mã số truyện không đúng!");
                    } else {
                        System.out.println("Nhập số lượng mới: ");
                        int newQuanlity = inputNumber.nextInt();
                        storyManagement.getByIndex(index).setQuanlity(newQuanlity);
                        System.out.println("Cập nhật xong!");
                    }
                   break;
                }
            }
            doWriteFile(storyManagement);
        }while (choice != 0);
    }

    private void doWriteFile(IStoryManagement storyManagement) {
        try {
            storyManagement.writeFile("story.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doReadFile(IStoryManagement storyManagement) {
        try {
            storyManagement.readFile("story.txt");
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private void menu() {
        System.out.println("1. Hiển thị danh sách truyên.");
        System.out.println("2. Thêm truyện mới.");
        System.out.println("3. Cập nhật thông tin truyện.");
        System.out.println("4. Xóa truyện.");
        System.out.println("5. Tìm truyện theo mã số.");
        System.out.println("6. Cập nhật số lượng truyện.");
        System.out.println("0. Thoát.");
    }

    private void doFindStoryByID(IStoryManagement storyManagement) {
        System.out.println("----Tim truyện theo mã số----");
        System.out.println("Nhập mã số truyện: ");
        String id = inputString.nextLine();
        int index = storyManagement.findById(id);
        if (index == -1){
            System.out.println("Mã số truyện không đúng!");
        }else {
            storyManagement.getByIndex(index);
        }
    }

    private void doRemoveStory(IStoryManagement storyManagement) {
        System.out.println("----Xóa truyện----");
        System.out.println("Nhập mã số truyện: ");
        String id = inputString.nextLine();
        int index = storyManagement.findById(id);
        if (index == -1){
            System.out.println("Mã số truyện không đúng!");
        }else {
            storyManagement.remove(index);
            System.out.println("Xóa thành công!");
        }
    }

    private void doUpdateStory(IStoryManagement storyManagement) {
        System.out.println("----Cập nhật thông tin truyện----");
        System.out.println("Nhập mã số truyện: ");
        String id = inputString.nextLine();
        int index = storyManagement.findById(id);
        if(index == -1){
            System.out.println("Mã số truyện không đúng!");
        } else {
            Story newStory = creatNewStory();
            storyManagement.update(index,newStory);
            System.out.println("Cập nhật thành công!");
        }
    }

    private void doAddNewStory(IStoryManagement storyManagement) {
        System.out.println("----Thêm truyện mới----");
        Story newStory = creatNewStory();
        storyManagement.add(newStory);
        System.out.println("Thêm thành công!");
    }

    private void doShowAllStory(IStoryManagement storyManagement) {
        System.out.println("----Hiển thị danh sách truyện----");
        int size = storyManagement.getSize();
        if (size == 0){
            System.out.println("Danh sách rỗng!");
        }else {
            storyManagement.displayAll();
        }
    }
    private Story creatNewStory(){
        int choice = -1;
        Story newStory = null;
        do {
            System.out.println("1. Thêm truyện tranh.");
            System.out.println("2. Thêm tiểu thuyết.");
            System.out.println("0. Quay lại.");
            System.out.println("Nhập lựa chọn của bạn: ");
            choice = inputNumber.nextInt();
            if (choice > 2){
                System.out.println("UserLogin chỉ có từ 1 - 2!");
            }
            newStory = FactoryStory.getStory(choice);
            return newStory;
        }while (choice != 0);
    }
}
