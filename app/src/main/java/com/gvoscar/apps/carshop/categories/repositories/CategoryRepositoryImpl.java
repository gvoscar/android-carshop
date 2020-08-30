package com.gvoscar.apps.carshop.categories.repositories;

import com.google.firebase.database.DatabaseReference;
import com.gvoscar.apps.carshop.database.Database;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.pojos.Category;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CategoryRepositoryImpl implements CategoryRepository {

    Database db;

    public CategoryRepositoryImpl() {
        db = Database.getInstance();
    }

    @Override
    public void agregarCategoria(Category category) {
        try {
            DatabaseReference ref = db.getCategoriesRef();
            category.setId(ref.push().getKey());
            ref.child(category.getId()).setValue(category);
        } catch (Exception e) {
            //SimpleLog.e();
        }
    }

    @Override
    public void actualizarCategoria(Category category) {
        DatabaseReference ref = db.getCategoriesRef();
        ref.child(category.getId()).updateChildren(category.toMap());
    }
}
