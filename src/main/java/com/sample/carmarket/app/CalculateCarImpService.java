package com.sample.carmarket.app;

import com.sample.carmarket.entity.Car;
import com.sample.carmarket.entity.EngineType;
import com.sample.carmarket.entity.Manufacturer;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component("CalculateCarImpService")
public class CalculateCarImpService {
    @Autowired
    DataManager dataManager;
    public String listManufact(Manufacturer manufacturer) {
        List<Car> NissanManufact = dataManager.load(Car.class)
                .query("select p from Car p where p.model.manufacturer.name = :name")
                .parameter("name", manufacturer.getName())
                .list();
        int gazCars = 0;
        int electricCars = 0;

        for (Car list: NissanManufact) {
            if(list.getModel().getEngineType() != EngineType.GASOLINE) {
                gazCars++;
            } else {
                electricCars++;
            }
        }
        HashMap<String, Integer> cars = new HashMap<>();
        cars.put("electricCars", electricCars);
        cars.put("gazCars", gazCars);

        return cars.toString();
    }

}
