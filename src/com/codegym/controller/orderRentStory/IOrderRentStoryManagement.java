package com.codegym.controller.orderRentStory;

import com.codegym.controller.GeneralManagement;
import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.CustomersByAppointment;

public interface IOrderRentStoryManagement extends GeneralManagement<CustomersByAppointment>, WriteFile, ReadFile {
}
