package com.gvoscar.apps.carshop.loader.repositories;

import com.google.firebase.database.DatabaseReference;
import com.gvoscar.apps.carshop.database.Database;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.loader.events.LoaderEvent;
import com.gvoscar.apps.carshop.logs.SimpleLog;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoaderRepositoryImpl implements LoaderRepository {
    private static final String TAG = LoaderRepositoryImpl.class.getSimpleName();

    private Database db;
    private DatabaseReference userRef;

    public LoaderRepositoryImpl() {
        db = Database.getInstance();
        userRef = db.getUserRef();
    }

    @Override
    public void checkAuth() {
        SimpleLog.i(TAG, "checkAuth():  Comprobar autenticacion");
        if (userRef == null) {
            postEvent(LoaderEvent.UNAUTHENTICATED, "Usuario no autenticado");
            return;
        }

        postEvent(LoaderEvent.AUTHENTICATED, "Usuario autenticado");
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null);
    }

    private void postEvent(int eventType, String message) {
        SimpleLog.i(TAG, "postEvent():  " + eventType);
        GreenRobotEventBus.getInstance().post(new LoaderEvent(eventType, message));
    }
}
