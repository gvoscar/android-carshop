package com.gvoscar.apps.carshop.vehicles.presenters;


import com.gvoscar.apps.carshop.libs.base.eventbus.EventBus;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.pojos.Vehicle;
import com.gvoscar.apps.carshop.vehicles.events.VehiclesEvent;
import com.gvoscar.apps.carshop.vehicles.interactors.VehiclesInteractor;
import com.gvoscar.apps.carshop.vehicles.interactors.VehiclesInteractorImpl;
import com.gvoscar.apps.carshop.vehicles.ui.VehiclesView;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class VehiclesPresenterImpl implements VehiclesPresenter {
    private EventBus mBus;
    private VehiclesView mView;
    private VehiclesInteractor mInteractor;

    public VehiclesPresenterImpl(VehiclesView view) {
        this.mBus = GreenRobotEventBus.getInstance();
        this.mView = view;
        this.mInteractor = new VehiclesInteractorImpl();
    }

    @Override
    public void onCreate() {
        this.mBus.register(this);

    }

    @Override
    public void onDestroy() {
        this.mBus.unregister(this);
    }

    @Override
    public void getData() {
        this.mInteractor.getData();
    }

    @Subscribe
    public void onEvent(VehiclesEvent event) {
        switch (event.getEventType()) {
            case VehiclesEvent.NOT_FOUND:
                onNotFound(event.getMessage());
                break;
            case VehiclesEvent.DATA_LOADED:
                onDataLoaded(event.getList());
                break;
        }
    }

    public void onNotFound(String message) {
        if (mView != null) {
            mView.onNotFound(message);
        }
    }

    public void onDataLoaded(List<Vehicle> list) {
        if (mView != null) {
            mView.onDataLoaded(list);
        }
    }
}
