package com.codegym.controller.userStaff;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.UserStaff;

public interface IUserStaffManagement<U> extends WriteFile, ReadFile, GeneralManagement<UserStaff> {
    boolean checkUsernameExist(String username);
    boolean checkLogin(String usernam, String password);
    int findUserByUsername(String username);
}
