package com.gvoscar.apps.carshop.analytics;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CarShopAnalytics {
    private static final String TAG = CarShopAnalytics.class.getSimpleName();
    private FirebaseAnalytics mAnalytics;


    public CarShopAnalytics(Context context) {
        this.mAnalytics = FirebaseAnalytics.getInstance(context);
    }

    private static class SingletonHolder {
        private static CarShopAnalytics INSTANCE(Context context) {
            return new CarShopAnalytics(context);
        }
    }

    public static CarShopAnalytics getInstance(Context context) {
        return SingletonHolder.INSTANCE(context);
    }

    public void loginEvent() {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, "Google");
        mAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }
}
