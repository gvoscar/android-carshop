package com.gvoscar.apps.carshop.vehicles.interactors;

import android.content.Context;

import com.gvoscar.apps.carshop.pojos.Vehicle;
import com.gvoscar.apps.carshop.vehicles.repositories.VehicleRepository;
import com.gvoscar.apps.carshop.vehicles.repositories.VehicleRepositoryImpl;

public class VehicleInteractorImpl implements VehicleInteractor {

    VehicleRepository repo;

    public VehicleInteractorImpl(Context context) {
        this.repo = new VehicleRepositoryImpl(context);
    }

    @Override
    public void guardarVehiculo(Vehicle vehicle, String file) {
        repo.guardarVehiculo(vehicle, file);
    }
}
