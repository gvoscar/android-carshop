package com.gvoscar.apps.carshop;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.gvoscar.apps.carshop.pojos.Category;
import com.gvoscar.apps.carshop.pojos.Vehicle;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CarShopApp extends MultiDexApplication {

    private Category category;
    private Vehicle vehicle;

    @Override
    public void onCreate() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        super.onCreate();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
