package com.gvoscar.apps.carshop.vehicles.ui;


import com.gvoscar.apps.carshop.pojos.Vehicle;

import java.util.List;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public interface VehiclesView {
    void onNotFound(String message);

    void onDataLoaded(List<Vehicle> list);
}
