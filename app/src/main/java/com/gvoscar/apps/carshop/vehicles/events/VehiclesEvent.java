package com.gvoscar.apps.carshop.vehicles.events;

import com.gvoscar.apps.carshop.pojos.Category;
import com.gvoscar.apps.carshop.pojos.Vehicle;

import java.util.List;

public class VehiclesEvent {
    public static final int NOT_FOUND = 100;
    public static final int DATA_LOADED = 200;

    private int eventType;
    private String message;
    private List<Vehicle> list;

    public VehiclesEvent() {
    }

    public VehiclesEvent(int eventType, String message, List<Vehicle> list) {
        this.eventType = eventType;
        this.message = message;
        this.list = list;
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

    public List<Vehicle> getList() {
        return list;
    }

    public void setList(List<Vehicle> list) {
        this.list = list;
    }
}
