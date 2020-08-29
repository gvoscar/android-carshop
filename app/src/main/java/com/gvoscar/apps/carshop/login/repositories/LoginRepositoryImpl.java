package com.gvoscar.apps.carshop.login.repositories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.gvoscar.apps.carshop.analytics.CarShopAnalytics;
import com.gvoscar.apps.carshop.database.Database;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.loader.events.LoaderEvent;
import com.gvoscar.apps.carshop.login.events.LoginEvent;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.pojos.User;
import com.gvoscar.apps.carshop.utils.Util;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class LoginRepositoryImpl implements LoginRepository {

    private static final String TAG = LoginRepositoryImpl.class.getSimpleName();
    private static final String defaulImg = "https://firebasestorage.googleapis.com/v0/b/pasili.appspot.com/o/default%2Fusuario.png?alt=media&token=a412b8d9-d8b3-4ec4-8e21-d95e404e723b";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private Database mDatabase;
    private DatabaseReference mUserRef;

    private CarShopAnalytics mAnalytics;


    public LoginRepositoryImpl(Context context) {
        mDatabase = Database.getInstance();
        mAuth = mDatabase.getAuthInstance();
        mAnalytics = CarShopAnalytics.getInstance(context);
    }

    @Override
    public void signinWithGoogle(final GoogleSignInAccount acct) {
        try {
            mAuth = mDatabase.getAuthInstance();
            if (mAuth != null) {
                AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    checkAuth(acct);
                                } else {
                                    SimpleLog.e(TAG, "signinWithGoogle().onComplete:    No se completo la tarea");
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                SimpleLog.e(TAG, "signinWithGoogle().onFailure:     Ocurrio un ERROR.  " + e.getLocalizedMessage(), e);
                            }
                        });
            }
        } catch (Exception e) {
            SimpleLog.e(TAG, "signinWithGoogle().    Ocurrio una excepcion. " + e.getLocalizedMessage(), e);
        }
    }

    private void checkAuth(final GoogleSignInAccount acct) {
        SimpleLog.i(TAG, "checkAuthentication().    Comprobar autenticacion de usuario.");
        try {
            mUserRef = mDatabase.getUserRef();
            mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Comprobar si la refeencia existe.
                    if (dataSnapshot.exists()) {
                        // Actualizar usuario
                        saveUser(acct);
                    } else {
                        saveUser(acct);
                        checkUser();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    SimpleLog.e(TAG, "checkAuthentication().    Ocurrio un ERROR.   " + databaseError.getMessage(), databaseError.toException());
                }
            });
        } catch (Exception e) {
            SimpleLog.e(TAG, "checkAuthentication().    Ocurrio una excepcion.  " + e.getLocalizedMessage(), e);
        }
    }

    private void saveUser(final GoogleSignInAccount acct) {
        SimpleLog.i(TAG, "saveUser().    Guardar usuario");
        try {
            mAnalytics.loginEvent();
            mUserRef = mDatabase.getUserRef();

            User newUser = new User();
            newUser.setCreateAt(Util.getCurrentTimeMillis());
            newUser.setUid(mDatabase.getUserUid());
            newUser.setEmail(mDatabase.getUserEmail());
            newUser.setPhotoUrl((acct.getPhotoUrl() != null) ? acct.getPhotoUrl().toString() : defaulImg);
            newUser.setDisplayName((acct.getDisplayName() != null) ? acct.getDisplayName() : mDatabase.getUserEmail());

            mUserRef.setValue(newUser, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                    if (error != null) {
                        SimpleLog.e(TAG, "saveUser().onComplete():  Ocurrio un error al guardar usuario");
                    }
                }
            });
        } catch (Exception e) {
            SimpleLog.e(TAG, "checkAuthentication().    Ocurrio una excepcion.  " + e.getLocalizedMessage(), e);
            // postEvent(LoginEvent.ERROR, "¡Ups! Algo salio mal, intenta de nuevo en unos minutos");
        }
    }

    private void checkUser() {
        SimpleLog.i(TAG, "checkUser().  Comprobar usuario autenticado.");
        try {
            mUserRef = mDatabase.getUserRef();
            mUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);
                        postEvent(LoginEvent.SUCCESS);
                    } else {
                        postEvent(LoginEvent.ERROR, "Tu cuenta tiene un problema, por favor comunicate con soporte");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    SimpleLog.e(TAG, "checkUser().  Ocurrio un ERROR.  " + databaseError.getDetails(), databaseError.toException());
                }
            });
        } catch (Exception e) {
            SimpleLog.e(TAG, "checkUser().  Ocurrio una excepcion.  " + e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void subscribeSigninChanges() {
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser == null) {
                    // postEvent(LoginEvent.user_not_authenticated);
                } else {
                    // Comprobar usuario.
                    checkUser();
                }

            }
        };

        // Agregar oyente de firebase.
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void unSubscribeSigninChanges() {
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null);
    }

    private void postEvent(int eventType, String message) {
        SimpleLog.i(TAG, "postEvent():  Publicar evento: " + eventType);
        GreenRobotEventBus.getInstance().post(new LoginEvent(eventType, message));
    }
}
