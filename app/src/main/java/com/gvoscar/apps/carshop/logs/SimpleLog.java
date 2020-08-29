package com.gvoscar.apps.carshop.logs;

import android.util.Log;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.gvoscar.apps.carshop.BuildConfig;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class SimpleLog {
    private static final boolean DEBUG = BuildConfig.DEBUG;


    /**
     * Enviar mensaje de DEBUG.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     */
    public static void d(String tag, String msg) {
        if (DEBUG) {
            Log.d(tag, msg);
        }
    }

    /**
     * Enviar mensaje de DEBUG y registra la excepción.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     * @param tr  Una excepción al registro
     */
    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.d(tag, msg, tr);
        }
    }

    /**
     * Enviar mensaje de ERROR.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     */
    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
        FirebaseCrashlytics.getInstance().log(msg);
    }

    /**
     * Enviar mensaje de ERROR y registra la excepción.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     * @param tr  Una excepción al registro
     */
    public static void e(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.e(tag, msg, tr);
        }
        FirebaseCrashlytics.getInstance().log(msg);
    }

    /**
     * Enviar mensaje de INFO.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     */
    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }

    /**
     * Enviar mensaje de INFO y registrar la excepción.
     *
     * @param tag identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     * @param tr  Una excepción al registro
     */
    public static void i(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.i(tag, msg, tr);
        }
    }

    /**
     * Enviar mensaje de VERBOSE.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     */
    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    /**
     * Enviar mensaje de VERBOSE y registrar la excepción.
     *
     * @param tag identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     * @param tr  Una excepción al registro
     */
    public static void v(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.v(tag, msg, tr);
        }
    }

    /**
     * Enviar mensaje de WARN.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     */
    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    /**
     * Enviar mensaje de WARN y registra la excepción.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     * @param tr  Una excepción al registro
     */
    public static void w(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.w(tag, msg, tr);
        }
    }

    /**
     * Enviar mensaje de WTF. Qué terrible falla.
     * <p>
     * Informe una condición que nunca debería ocurrir. El ERROR siempre se registrará en el
     * nivel ASSERT con la pila de llamadas. Dependiendo de la configuración del sistema,
     * se puede agregar un informe DropBoxManageral proceso y / o el proceso se puede finalizar
     * de inmediato con un cuadro de diálogo de ERROR.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     */
    public static void wtf(String tag, String msg) {
        if (DEBUG) {
            Log.wtf(tag, msg);
        }
    }

    /**
     * Enviar mensaje de WTF y registra la excepción.
     * <p>
     * Informe una excepción que nunca debería suceder.
     *
     * @param tag Identifica la clase o actividad donde se produce la llamada de registro.
     * @param msg El mensaje que desea registrar.
     * @param tr  Una excepción al registro
     */
    public static void wtf(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.wtf(tag, msg, tr);
        }
    }
}
