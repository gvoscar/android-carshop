package com.gvoscar.apps.carshop.categories.presenters;

import com.gvoscar.apps.carshop.pojos.Category;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public interface CategoryPresenter {
    void onCreate();
    void onDestroy();
    void agregarCategoria(Category category);
    void actualizarCategoria(Category category);
}
