package com.codegym.controller;

public interface GeneralManagement<T> {
    void add(T t);

    void update(int index, T t);

    void remove(int index);

    void displayAll();

    int findById(String id);

    T  getByIndex(int index);

    int getSize();
}
