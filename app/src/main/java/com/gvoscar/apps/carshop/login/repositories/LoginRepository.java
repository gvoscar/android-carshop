package com.gvoscar.apps.carshop.login.repositories;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public interface LoginRepository {

    void signinWithGoogle(GoogleSignInAccount acct);

    void subscribeSigninChanges();

    void unSubscribeSigninChanges();
}
