package com.gvoscar.apps.carshop.login.ui;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public interface LoginView {

    int RC_SIGN_IN = 9001;

    void enableInputs();
    void disableInputs();

    void showProgress();
    void hideProgress();

    void onError(String message);
    void onSuccess();
}
