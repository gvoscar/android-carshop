package com.gvoscar.apps.carshop.categories.interactors;

import com.gvoscar.apps.carshop.categories.repositories.CategoriesRepository;
import com.gvoscar.apps.carshop.categories.repositories.CategoriesRepositoryImpl;

public class CategoriesInteractorImpl implements CategoriesInteractor {

    CategoriesRepository mRepo;

    public CategoriesInteractorImpl() {
        this.mRepo = new CategoriesRepositoryImpl();
    }

    public CategoriesInteractorImpl(CategoriesRepository mRepo) {
        this.mRepo = mRepo;
    }

    @Override
    public void getData() {
        this.mRepo.getData();
    }
}
