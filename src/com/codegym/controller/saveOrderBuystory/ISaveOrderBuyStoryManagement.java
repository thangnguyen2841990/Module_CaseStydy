package com.codegym.controller.saveOrderBuystory;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.CustomerBuyStory;

public interface ISaveOrderBuyStoryManagement extends GeneralManagement<CustomerBuyStory>, WriteFile, ReadFile {
    int totalPayMoney();
}
