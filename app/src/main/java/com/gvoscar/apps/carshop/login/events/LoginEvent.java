package com.gvoscar.apps.carshop.login.events;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoginEvent {
    public static final int ERROR = 100;
    public static final int SUCCESS = 200;


    private int eventType;
    private String message;

    public LoginEvent() {
    }

    public LoginEvent(int eventType, String message) {
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
