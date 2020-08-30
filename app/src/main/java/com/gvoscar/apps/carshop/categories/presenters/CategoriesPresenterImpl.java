package com.gvoscar.apps.carshop.categories.presenters;

import com.gvoscar.apps.carshop.categories.events.CategoriesEvent;
import com.gvoscar.apps.carshop.categories.interactors.CategoriesInteractor;
import com.gvoscar.apps.carshop.categories.interactors.CategoriesInteractorImpl;
import com.gvoscar.apps.carshop.categories.ui.CategoriesView;
import com.gvoscar.apps.carshop.libs.base.eventbus.EventBus;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.pojos.Category;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class CategoriesPresenterImpl implements CategoriesPresenter {

    private EventBus mBus;
    private CategoriesView mView;
    private CategoriesInteractor mInteractor;

    public CategoriesPresenterImpl(CategoriesView view) {
        this.mBus = GreenRobotEventBus.getInstance();
        this.mView = view;
        this.mInteractor = new CategoriesInteractorImpl();
    }

    @Override
    public void onCreate() {
        this.mBus.register(this);

    }

    @Override
    public void onDestroy() {
        this.mBus.unregister(this);
    }

    @Override
    public void getData() {
        this.mInteractor.getData();
    }

    @Subscribe
    public void onEvent(CategoriesEvent event) {
        switch (event.getEventType()) {
            case CategoriesEvent.NOT_FOUND:
                onNotFound(event.getMessage());
                break;
            case CategoriesEvent.DATA_LOADED:
                onDataLoaded(event.getList());
                break;
        }
    }

    public void onNotFound(String message) {
        if (mView != null) {
            mView.onNotFound(message);
        }
    }

    public void onDataLoaded(List<Category> list) {
        if (mView != null) {
            mView.onDataLoaded(list);
        }
    }

}
