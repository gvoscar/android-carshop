package com.gvoscar.apps.carshop.categories.ui;

import com.gvoscar.apps.carshop.pojos.Category;

import java.util.List;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public interface CategoriesView {
    void onNotFound(String message);

    void onDataLoaded(List<Category> list);
}
