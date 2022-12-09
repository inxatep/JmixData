package com.sample.carmarket.entity.car;

import com.sample.carmarket.app.CarServiceImp;
import com.sample.carmarket.entity.Status;
import io.jmix.core.DataManager;
import io.jmix.core.SaveContext;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import com.sample.carmarket.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

@UiController("Car.browse")
@UiDescriptor("car-browse.xml")
@LookupComponent("table")
public class CarBrowse extends MasterDetailScreen<Car> {

    @Autowired
    private CarServiceImp carServiceImp;

    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;

    @Subscribe("markAsSold")
    public void onImportBtnClick(Button.ClickEvent event) {

        Car car = getTable().getSingleSelected();

        if(carServiceImp.markOnSold(car).getStatus() == Status.SOLD) {
            notifications.create()
                    .withCaption("Already Sold")
                    .withType(Notifications.NotificationType.TRAY)
                    .show();
        } else {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            car.setStatus(Status.SOLD);
            car.setDate_of_sale(formatter.get2DigitYearStart());
            dataManager.save(new SaveContext().saving(car));
            notifications.create()
                    .withCaption("Done")
                    .withType(Notifications.NotificationType.TRAY)
                    .show();
        }
    }
}


