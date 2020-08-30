package com.gvoscar.apps.carshop.vehicles.presenters;

import com.gvoscar.apps.carshop.pojos.Vehicle;


public interface VehiclePresenter {
    void onCreate();

    void onDestroy();

    void guardarVehiculo(Vehicle vehicle, String file);
}
