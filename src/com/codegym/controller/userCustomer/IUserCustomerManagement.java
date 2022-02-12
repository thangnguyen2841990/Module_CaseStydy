package com.codegym.controller.userCustomer;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.UserCustomer;

public interface IUserCustomerManagement extends GeneralManagement<UserCustomer>, WriteFile, ReadFile {
    boolean checkUsernameExist(String username);
    boolean checkLogin(String username, String password);
    int findUserByUsername(String username);
}
