package com.gvoscar.apps.carshop.categories.events;

import com.gvoscar.apps.carshop.pojos.Category;

import java.util.List;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CategoriesEvent {

    public static final int NOT_FOUND = 100;
    public static final int DATA_LOADED = 200;

    private int eventType;
    private String message;
    private List<Category> list;

    public CategoriesEvent() {
    }

    public CategoriesEvent(int eventType, String message, List<Category> list) {
        this.eventType = eventType;
        this.message = message;
        this.list = list;
    }

    @Override
    public String toString() {
        return "CategoriesEvent{" +
                "eventType=" + eventType +
                ", message='" + message + '\'' +
                '}';
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

    public List<Category> getList() {
        return list;
    }

    public void setList(List<Category> list) {
        this.list = list;
    }
}
