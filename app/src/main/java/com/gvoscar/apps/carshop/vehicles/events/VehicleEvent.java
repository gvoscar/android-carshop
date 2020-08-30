package com.gvoscar.apps.carshop.vehicles.events;

import com.gvoscar.apps.carshop.pojos.Vehicle;

import java.util.List;

public class VehicleEvent {
    public static final int ERROR = 100;
    public static final int SUCCESS = 200;

    private int eventType;
    private String message;

    public VehicleEvent() {
    }

    public VehicleEvent(int eventType, String message) {
        this.eventType = eventType;
        this.message = message;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
