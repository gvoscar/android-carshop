package com.gvoscar.apps.carshop.libs.base.eventbus;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class GreenRobotEventBus implements EventBus {
    /**
     * Sistema de eventos.
     */
    private org.greenrobot.eventbus.EventBus eventBus;

    /**
     * Constructor.
     */
    public GreenRobotEventBus() {
        this.eventBus = org.greenrobot.eventbus.EventBus.getDefault();
    }

    /**
     * Registrarse.
     * <p>
     * Este metodo permite registrarse para recibir eventos.
     *
     * @param subscriber Suscriptor
     */
    @Override
    public void register(Object subscriber) {
        this.eventBus.register(subscriber);
    }

    /**
     * Anular registro.
     * <p>
     * Este metodo permite anular el registro para dejar de recibir eventos.
     *
     * @param subscriber Suscriptor.
     */
    @Override
    public void unregister(Object subscriber) {
        this.eventBus.unregister(subscriber);
    }

    /**
     * Publicar evento.
     * <p>
     * Este metodo permite publicar un evento.
     *
     * @param event Evento.
     */
    @Override
    public void post(Object event) {
        this.eventBus.post(event);
    }

    /**
     * Clase singleton.
     * <p>
     * Esta clase permite administrar una sola instancia.
     */
    private static class SingletonHolder {
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }

    /**
     * Obtener instancia.
     * <p>
     * Este metodo permite obtener la instancia del manejador de eventos.
     *
     * @return Instancia unica.
     */
    public static GreenRobotEventBus getInstance() {
        return SingletonHolder.INSTANCE;
    }


}
