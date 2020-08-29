package com.gvoscar.apps.carshop.loader.presenters;

import com.gvoscar.apps.carshop.libs.base.eventbus.EventBus;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.loader.events.LoaderEvent;
import com.gvoscar.apps.carshop.loader.interactors.LoaderInteractor;
import com.gvoscar.apps.carshop.loader.interactors.LoaderInteractorImpl;
import com.gvoscar.apps.carshop.loader.ui.LoaderView;

import org.greenrobot.eventbus.Subscribe;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoaderPresenterImpl implements LoaderPresenter {
    private final static String TAG = LoaderPresenterImpl.class.getSimpleName();

    private EventBus eventBus;
    private LoaderView mView;
    private LoaderInteractor mInteractor;

    public LoaderPresenterImpl(LoaderView view) {
        mView = view;
        eventBus = GreenRobotEventBus.getInstance();
        mInteractor = new LoaderInteractorImpl();
    }

    public LoaderPresenterImpl(EventBus eventBus, LoaderView mView, LoaderInteractor mInteractor) {
        this.eventBus = eventBus;
        this.mView = mView;
        this.mInteractor = mInteractor;
    }


    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void checkAuth() {
        this.mInteractor.checkAuth();
    }

    @Subscribe
    public void onEvent(LoaderEvent event) {
        switch (event.getEventType()) {
            case LoaderEvent.UNAUTHENTICATED:
                unAuthenticated();
                break;
            case LoaderEvent.AUTHENTICATED:
                onAuthenticated();
                break;
        }
    }

    @Override
    public void unAuthenticated() {
        if (this.mView != null) {
            this.mView.unAuthenticated();
        }
    }

    @Override
    public void onAuthenticated() {
        if (this.mView != null) {
            this.mView.onAuthenticated();
        }
    }
}
