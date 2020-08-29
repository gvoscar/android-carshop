package com.gvoscar.apps.carshop.login.interactors;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.gvoscar.apps.carshop.login.repositories.LoginRepository;
import com.gvoscar.apps.carshop.login.repositories.LoginRepositoryImpl;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoginInteractorImpl implements LoginInteractor {

    private static final String TAG = LoginInteractorImpl.class.getSimpleName();
    private LoginRepository mRepository;

    public LoginInteractorImpl(Context context) {
        mRepository = new LoginRepositoryImpl(context);
    }

    @Override
    public void signinWithGoogle(GoogleSignInAccount acct) {
        mRepository.signinWithGoogle(acct);
    }

    @Override
    public void subscribeSigninChanges() {
        mRepository.subscribeSigninChanges();
    }

    @Override
    public void unSubscribeSigninChanges() {
        mRepository.unSubscribeSigninChanges();
    }
}
