package com.gvoscar.apps.carshop.vehicles.interactors;

import com.gvoscar.apps.carshop.vehicles.repositories.VehiclesRepository;
import com.gvoscar.apps.carshop.vehicles.repositories.VehiclesRepositoryImpl;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class VehiclesInteractorImpl implements VehiclesInteractor {
    VehiclesRepository repo;

    public VehiclesInteractorImpl() {
        this.repo = new VehiclesRepositoryImpl();
    }

    @Override
    public void getData() {
        repo.getData();
    }
}
