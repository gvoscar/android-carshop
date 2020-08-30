package com.gvoscar.apps.carshop.vehicles.repositories;


import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.gvoscar.apps.carshop.database.Database;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.pojos.Vehicle;
import com.gvoscar.apps.carshop.vehicles.events.VehiclesEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class VehiclesRepositoryImpl implements VehiclesRepository {
    private static final String TAG = VehiclesRepositoryImpl.class.getSimpleName();
    private Database mDatabase;

    public VehiclesRepositoryImpl() {
        mDatabase = Database.getInstance();
    }

    @Override
    public void getData() {
        try {
            DatabaseReference reference = mDatabase.getVehiclesRef();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        List<Vehicle> list = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Vehicle vehicle = snapshot.getValue(Vehicle.class);
                            list.add(vehicle);
                        }

                        // Invertir orden.
                        Collections.reverse(list);
                        postEvent(VehiclesEvent.DATA_LOADED, "Datos cargados", list);
                    } else {
                        postEvent(VehiclesEvent.NOT_FOUND, "No hay datos");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        } catch (Exception e) {
        }
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null, null);
    }

    private void postEvent(int eventType, String message) {
        postEvent(eventType, message, null);
    }

    private void postEvent(int eventType, String message, List<Vehicle> list) {
        GreenRobotEventBus.getInstance().post(new VehiclesEvent(eventType, message, list));
    }
}
