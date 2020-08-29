package com.gvoscar.apps.carshop;

import androidx.multidex.MultiDexApplication;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CarShopApp extends MultiDexApplication {
    @Override
    public void onCreate() {
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
        super.onCreate();
    }
}
