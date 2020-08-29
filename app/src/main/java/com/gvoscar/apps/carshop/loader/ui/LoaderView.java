package com.gvoscar.apps.carshop.loader.ui;

public interface LoaderView {
    /**
     * Evento de usuario no autenticado
     */
    void unAuthenticated();

    /**
     * Evento de usuario autenticado.
     */
    void onAuthenticated();
}
