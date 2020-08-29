package com.gvoscar.apps.carshop.loader.interactors;

import com.gvoscar.apps.carshop.loader.repositories.LoaderRepository;
import com.gvoscar.apps.carshop.loader.repositories.LoaderRepositoryImpl;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoaderInteractorImpl implements LoaderInteractor {
    private LoaderRepository mRepo;

    public LoaderInteractorImpl() {
        this.mRepo = new LoaderRepositoryImpl();
    }

    public LoaderInteractorImpl(LoaderRepository mRepo) {
        this.mRepo = mRepo;
    }

    @Override
    public void checkAuth() {
        this.mRepo.checkAuth();
    }
}
