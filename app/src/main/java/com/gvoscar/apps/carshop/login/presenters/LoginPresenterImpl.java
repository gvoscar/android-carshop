package com.gvoscar.apps.carshop.login.presenters;

import android.content.Context;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.gvoscar.apps.carshop.libs.base.eventbus.EventBus;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.login.events.LoginEvent;
import com.gvoscar.apps.carshop.login.interactors.LoginInteractor;
import com.gvoscar.apps.carshop.login.interactors.LoginInteractorImpl;
import com.gvoscar.apps.carshop.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoginPresenterImpl implements LoginPresenter {
    private static final String TAG = LoginPresenterImpl.class.getSimpleName();

    private EventBus mBus;
    private LoginView mView;
    private LoginInteractor mInteractor;

    public LoginPresenterImpl(Context context, LoginView view) {
        this.mBus = GreenRobotEventBus.getInstance();
        this.mView = view;
        this.mInteractor = new LoginInteractorImpl(context);
    }

    public LoginPresenterImpl(Context context, EventBus mBus, LoginView mView, LoginInteractor mInteractor) {
        this.mBus = mBus;
        this.mView = mView;
        this.mInteractor = mInteractor;
    }

    @Override
    public void onCreate() {
        mBus.register(this);
    }

    @Override
    public void onStart() {
        mInteractor.subscribeSigninChanges();
    }

    @Override
    public void onStop() {
        mInteractor.unSubscribeSigninChanges();
    }

    @Override
    public void onDestroy() {
        mBus.unregister(this);
        mView = null;
    }

    @Override
    public void signinWithGoogle(GoogleSignInAccount acct) {
        if (this.mView != null) {
            this.mView.showProgress();
            this.mView.disableInputs();
            this.mInteractor.signinWithGoogle(acct);
        }
    }

    @Subscribe
    public void onEvent(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.ERROR:
                onError(event.getMessage() != null ? event.getMessage() : "");
                break;
            case LoginEvent.SUCCESS:
                onSuccess();
                break;
        }
    }

    public void onError(String message) {
        if (this.mView != null) {
            this.mView.hideProgress();
            this.mView.enableInputs();
        }
    }

    public void onSuccess() {
        if (this.mView != null) {
            this.mView.hideProgress();
            this.mView.onSuccess();
        }
    }
}
