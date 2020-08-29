package com.gvoscar.apps.carshop.login.presenters;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public interface LoginPresenter {
    void onCreate();

    void onStart();

    void onStop();

    void onDestroy();

    void signinWithGoogle(GoogleSignInAccount acct);
}
