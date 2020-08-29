package com.gvoscar.apps.carshop.database;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class Database {
    private static final String TAG = Database.class.getSimpleName();
    private static final String DBA_ORIENTATION = SetupDatabase.getOrientation();
    private static final String FUN_ORIENTATION = SetupDatabase.getFunctionOrientation();


    private static final String REF_CATEGORIES = "categories";
    private static final String REF_USERS = "users";
    private static final String REF_VEHICLES = "vehicles";

    private DatabaseReference db;
    private StorageReference st;

    public Database() {
        db = FirebaseDatabase.getInstance().getReference().child(DBA_ORIENTATION);
        st = FirebaseStorage.getInstance().getReference().child(FUN_ORIENTATION);
    }

    private static class SingletonHolder {
        private static final Database INSTANCE = new Database();
    }

    public static Database getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * Obtener instancia de autenticacion.
     */
    public FirebaseAuth getAuthInstance() {
        return FirebaseAuth.getInstance();
    }

    /**
     * Obtener instancia para invocaciones de cloud functions
     */
    public FirebaseFunctions getFunctionsInstance() {
        return FirebaseFunctions.getInstance();
    }

    /**
     * Obtener email de usuario
     */
    public String getUserEmail() {
        return (FirebaseAuth.getInstance().getCurrentUser() != null)
                ? FirebaseAuth.getInstance().getCurrentUser().getEmail()
                : null;
    }

    /**
     * Obtener identificador unico de usuario
     */
    public String getUserUid() {
        return (getUserEmail() != null)
                ? FirebaseAuth.getInstance().getCurrentUser().getUid()
                : null;
    }

    /**
     * Obtener nombre para mostrar de usuario.
     */
    public String getUserDisplayName() {
        String displayName = null;
        FirebaseUser fUser = getAuthInstance().getCurrentUser();
        if (fUser != null) {
            if (fUser.getDisplayName() != null) {
                displayName = fUser.getDisplayName();
            }
        }
        return displayName;
    }

    /**
     * Obtener referencia de usuarios
     */
    public DatabaseReference getUsersRef() {
        return (getUserEmail() != null)
                ? db.getRoot().child(DBA_ORIENTATION).child(REF_USERS)
                : null;
    }

    /**
     * Obtener referencia de usuario en la dba.
     */
    public DatabaseReference getUserRef() {
        return (getUserEmail() != null)
                ? db.getRoot().child(DBA_ORIENTATION).child(REF_USERS).child(getUserUid())
                : null;
    }

    /**
     * Obtener referencia de categorias
     */
    public DatabaseReference getCategoriesRef() {
        return (getUserEmail() != null)
                ? db.getRoot().child(DBA_ORIENTATION).child(REF_CATEGORIES)
                : null;
    }

    /**
     * Obtener referencia de categoria
     */
    public DatabaseReference getCategoryRef(String categoryId) {
        return (getUserEmail() != null)
                ? db.getRoot().child(DBA_ORIENTATION).child(REF_CATEGORIES).child(categoryId)
                : null;
    }

    /**
     * Obtener referencia de vehiculos
     */
    public DatabaseReference getVehiclesRef() {
        return (getUserEmail() != null)
                ? db.getRoot().child(DBA_ORIENTATION).child(REF_VEHICLES)
                : null;
    }

    /**
     * Obtener referencia de vehiculo
     */
    public DatabaseReference getVehicleRef(String vehicleId) {
        return (getUserEmail() != null)
                ? db.getRoot().child(DBA_ORIENTATION).child(REF_VEHICLES).child(vehicleId)
                : null;
    }

    /**
     * Obtener almacenamiento de vehiculos.
     */
    public StorageReference getVehiclesSto() {
        return (getUserEmail() != null)
                ? st.getRoot().child(DBA_ORIENTATION).child(REF_VEHICLES)
                : null;
    }

    /**
     * Obtener almacenamiento de vehiculo.
     */
    public StorageReference getVehicleSto(String vehicleId) {
        return (getUserEmail() != null)
                ? st.getRoot().child(DBA_ORIENTATION).child(REF_VEHICLES).child(vehicleId)
                : null;
    }
}
