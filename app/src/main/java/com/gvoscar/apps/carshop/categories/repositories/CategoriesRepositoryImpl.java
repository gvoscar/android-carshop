package com.gvoscar.apps.carshop.categories.repositories;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.gvoscar.apps.carshop.categories.events.CategoriesEvent;
import com.gvoscar.apps.carshop.database.Database;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.pojos.Category;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CategoriesRepositoryImpl implements CategoriesRepository {

    private static final String TAG = CategoriesRepositoryImpl.class.getSimpleName();
    private Database mDatabase;

    public CategoriesRepositoryImpl() {
        mDatabase = Database.getInstance();
    }

    @Override
    public void getData() {
        try {
            DatabaseReference reference = mDatabase.getCategoriesRef();
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        List<Category> list = new ArrayList<>();
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Category category = snapshot.getValue(Category.class);
                            list.add(category);
                        }

                        // Invertir orden.
                        Collections.reverse(list);
                        postEvent(CategoriesEvent.DATA_LOADED, "Datos cargados", list);
                    } else {
                        postEvent(CategoriesEvent.NOT_FOUND, "No hay categorias");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    SimpleLog.e(TAG, "getData().   Se cancelo la operacion de recuperar datos: " + databaseError.getMessage(), databaseError.toException());
                }
            });
        } catch (Exception e) {
            SimpleLog.e(TAG, "getData().   Ocurrio una excepcion al recuperar datos: " + e.getLocalizedMessage(), e);
        }
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null, null);
    }

    private void postEvent(int eventType, String message) {
        postEvent(eventType, message, null);
    }

    private void postEvent(int eventType, String message, List<Category> list) {
        GreenRobotEventBus.getInstance().post(new CategoriesEvent(eventType, message, list));
    }
}
