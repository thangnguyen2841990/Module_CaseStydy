package com.codegym.controller.orderBuyStory;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.CustomerBuyStory;

public interface IOrderBuyStoryManagement extends GeneralManagement<CustomerBuyStory>, WriteFile, ReadFile {
    int payMoney(int index, int quanlity);
    int totalPayMoney();
    void clear();
}
