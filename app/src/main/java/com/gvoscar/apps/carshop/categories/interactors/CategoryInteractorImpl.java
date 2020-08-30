package com.gvoscar.apps.carshop.categories.interactors;

import com.gvoscar.apps.carshop.categories.repositories.CategoryRepository;
import com.gvoscar.apps.carshop.categories.repositories.CategoryRepositoryImpl;
import com.gvoscar.apps.carshop.pojos.Category;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CategoryInteractorImpl implements CategoryInteractor {

    private CategoryRepository mRepo;

    public CategoryInteractorImpl() {
        this.mRepo = new CategoryRepositoryImpl();
    }

    @Override
    public void agregarCategoria(Category category) {
        this.mRepo.agregarCategoria(category);
    }

    @Override
    public void actualizarCategoria(Category category) {
        this.mRepo.actualizarCategoria(category);
    }
}
