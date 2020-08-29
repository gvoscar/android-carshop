package com.gvoscar.apps.carshop.libs.base.eventbus;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public interface EventBus {
    /**
     * Registrarse.
     * <p>
     * Este metodo permite registrarse para recibir eventos.
     *
     * @param subscriber Suscriptor
     */
    void register(Object subscriber);

    /**
     * Anular registro.
     * <p>
     * Este metodo permite anular el registro para dejar de recibir eventos.
     *
     * @param subscriber Suscriptor.
     */
    void unregister(Object subscriber);

    /**
     * Publicar evento.
     * <p>
     * Este metodo permite publicar un evento.
     *
     * @param event Evento.
     */
    void post(Object event);
}
