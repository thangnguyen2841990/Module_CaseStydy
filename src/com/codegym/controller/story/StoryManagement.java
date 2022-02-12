package com.codegym.controller.story;

import com.codegym.model.Story;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StoryManagement implements IStoryManagement {
    private List<Story> storyList = new ArrayList<>();
    public static final StoryManagement INSTANCE = new StoryManagement();



    private StoryManagement() {
    }

    public static StoryManagement getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(Story story) {
        storyList.add(story);
    }

    @Override
    public void update(int index, Story story) {
        storyList.set(index, story);
    }

    @Override
    public void remove(int index) {
        storyList.remove(index);
    }

    @Override
    public void displayAll() {
        for (int i = 0; i < storyList.size(); i++) {
            System.out.println(storyList.get(i)+ ", Giá: " + storyList.get(i).getPrice() + ", Số lượng còn lại: "+ storyList.get(i).getQuanlity());
        }
    }

    @Override
    public int findById(String id) {
        int index = -1;
        for (int i = 0; i < getSize(); i++) {
            if (storyList.get(i).getId().equals(id)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public Story getByIndex(int index) {
      return   storyList.get(index);
    }

    @Override
    public int getSize() {
        return storyList.size();
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.storyList = (List<Story>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.storyList);
    }
}
