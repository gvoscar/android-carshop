package com.gvoscar.apps.carshop.loader.presenters;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public interface LoaderPresenter {

    void onCreate();

    void onDestroy();

    /**
     * Comprobar autenticacion.
     */
    void checkAuth();

    /**
     * Evento de usuario no autenticado
     */
    void unAuthenticated();

    /**
     * Evento de usuario autenticado.
     */
    void onAuthenticated();
}
