package com.gvoscar.apps.carshop.database;

import com.gvoscar.apps.carshop.BuildConfig;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class SetupDatabase {
    /**
     * ¿Esta en desarrollo?
     */
    private static final boolean isDebug = BuildConfig.DEBUG;

    /**
     * Orientacion a produccion.
     */
    private static final String PND = "pnd";

    /**
     * Orientacion a pruebas.
     */
    private static final String QA = "qa";

    /**
     * Obtener orientacion de base de datos.
     *
     * @return Devuelve la referencia donde se debe apuntar.
     */
    public static String getOrientation() {
        return isDebug ? QA : PND;
    }

    /**
     * Obtener orientacion de funciones de backend.
     *
     * @return Devuelve la orientacion de las funciones de backend.
     */
    public static String getFunctionOrientation() {
        return isDebug ? "q" : "p";
    }
}
