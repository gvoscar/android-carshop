package com.gvoscar.apps.carshop.loader.events;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoaderEvent {
    public static final int UNAUTHENTICATED = 100;
    public static final int AUTHENTICATED = 200;

    private int eventType;
    private String message;

    public LoaderEvent() {
    }

    public LoaderEvent(int eventType, String message) {
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
