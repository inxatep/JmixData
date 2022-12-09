package com.sample.carmarket.app;

import com.sample.carmarket.entity.Car;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("CarServiceImp")
public class CarServiceImp {
    @Autowired
   private DataManager dataManager;

    public Car markOnSold(Car car) {
        return dataManager.load(Car.class)
                .query("select p from Car p where p.registration_number = :registrationNumber")
                .parameter("registrationNumber", car.getRegistration_number())
                .optional()
                .orElse(null);
    }

}
