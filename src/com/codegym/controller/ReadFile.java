package com.codegym.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReadFile {
    void readFile(String path) throws IOException, ClassNotFoundException;
}
