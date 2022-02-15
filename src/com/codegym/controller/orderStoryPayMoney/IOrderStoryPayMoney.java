package com.codegym.controller.orderStoryPayMoney;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.CustomerBuyStory;

public interface IOrderStoryPayMoney extends GeneralManagement<CustomerBuyStory>, WriteFile, ReadFile {
    int totalPayMoney();
    void clear();
}
