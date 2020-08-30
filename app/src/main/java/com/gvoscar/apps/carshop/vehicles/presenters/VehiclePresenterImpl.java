package com.gvoscar.apps.carshop.vehicles.presenters;

import android.content.Context;

import com.gvoscar.apps.carshop.libs.base.eventbus.EventBus;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.pojos.Vehicle;
import com.gvoscar.apps.carshop.vehicles.events.VehicleEvent;
import com.gvoscar.apps.carshop.vehicles.interactors.VehicleInteractor;
import com.gvoscar.apps.carshop.vehicles.interactors.VehicleInteractorImpl;
import com.gvoscar.apps.carshop.vehicles.ui.VehicleView;

import org.greenrobot.eventbus.Subscribe;

public class VehiclePresenterImpl implements VehiclePresenter {

    private EventBus eventBus;
    private VehicleView view;
    private VehicleInteractor interactor;

    public VehiclePresenterImpl(Context context, VehicleView view) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.view = view;
        this.interactor = new VehicleInteractorImpl(context);
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.eventBus.unregister(this);
    }

    @Override
    public void guardarVehiculo(Vehicle vehicle, String file) {
        this.interactor.guardarVehiculo(vehicle, file);
    }

    @Subscribe
    public void onEvent(VehicleEvent event) {
        switch (event.getEventType()) {
            case VehicleEvent.ERROR:
                onError(event.getMessage());
                break;
            case VehicleEvent.SUCCESS:
                onSuccess();
                break;
        }
    }

    public void onError(String message) {
        if (view != null) {
            view.onError(message);
        }
    }


    public void onSuccess() {
        if (view != null) {
            view.onSuccess();
        }
    }
}
