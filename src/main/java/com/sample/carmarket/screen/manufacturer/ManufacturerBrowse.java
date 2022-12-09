package com.sample.carmarket.screen.manufacturer;

import com.sample.carmarket.app.CalculateCarImpService;
import com.sample.carmarket.entity.Manufacturer;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.MasterDetailScreen;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Manufacturer.browse")
@UiDescriptor("manufacturer-browse.xml")
@LookupComponent("table")
public class ManufacturerBrowse extends MasterDetailScreen<Manufacturer> {

    @Autowired
    private CalculateCarImpService calculateCarImpService;
    @Autowired
    private Notifications notifications;


    @Subscribe("calculateCars")
    public void onImportBtnClick(Button.ClickEvent event) {
        Manufacturer manufacturer = getTable().getSingleSelected();
        calculateCarImpService.listManufact(manufacturer);

        notifications.create()
                .withCaption(calculateCarImpService.listManufact(manufacturer))
                .withType(Notifications.NotificationType.TRAY)
                .show();
    }
}