package com.gvoscar.apps.carshop.categories.presenters;

import com.gvoscar.apps.carshop.categories.interactors.CategoryInteractor;
import com.gvoscar.apps.carshop.categories.interactors.CategoryInteractorImpl;
import com.gvoscar.apps.carshop.categories.ui.CategoryView;
import com.gvoscar.apps.carshop.libs.base.eventbus.EventBus;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.pojos.Category;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CategoryPresenterImpl implements CategoryPresenter {

    private EventBus eventBus;
    private CategoryView view;
    private CategoryInteractor interactor;


    public CategoryPresenterImpl(CategoryView view) {
        this.eventBus = GreenRobotEventBus.getInstance();
        this.view = view;
        this.interactor = new CategoryInteractorImpl();
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.eventBus.unregister(this);
    }

    // PENDIENTE
    void onEvent(){

    }

    @Override
    public void agregarCategoria(Category category) {
        this.interactor.agregarCategoria(category);
    }

    @Override
    public void actualizarCategoria(Category category) {
        this.interactor.actualizarCategoria(category);
    }
}
