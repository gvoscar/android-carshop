package com.gvoscar.apps.carshop.vehicles.repositories;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.gvoscar.apps.carshop.database.Database;
import com.gvoscar.apps.carshop.libs.base.eventbus.GreenRobotEventBus;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.pojos.Vehicle;
import com.gvoscar.apps.carshop.vehicles.events.VehicleEvent;

import java.io.ByteArrayOutputStream;
import java.io.File;

import id.zelory.compressor.Compressor;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class VehicleRepositoryImpl implements VehicleRepository {
    private final static String TAG = VehicleRepositoryImpl.class.getSimpleName();
    private Database mDatabase;
    private Context context;

    public VehicleRepositoryImpl(Context context) {
        this.mDatabase = Database.getInstance();
        this.context = context;
    }

    @Override
    public void guardarVehiculo(Vehicle vehicle, String file) {
        DatabaseReference ref = mDatabase.getVehiclesRef();

        if (vehicle.getId() == null) {
            vehicle.setId(ref.push().getKey());
        }

        // Consultar vehiculo y comprobar la categoria antes de continuar. (PENDIENTE)
        if (!vehicle.getCategory().isEditPermission()) {
            // postEvent(VehicleEvent.ERROR, "No se puede editar el vehículo con categoria " + vehicle.getCategory().getName());
            // return;
        }

        if (file != null) {
            uploadPhoto(vehicle);
        } else {
            update(vehicle);
        }
    }


    public void uploadPhoto(Vehicle vehicle) {
        try {
            StorageReference sto = mDatabase.getVehicleSto(vehicle.getId());
            Uri uri = Uri.parse(vehicle.getPhotoUrl());
            File filePhoto = new File(uri.getPath());
            Bitmap bitmapPhoto = new Compressor(this.context).setMaxHeight(640).setMaxWidth(640).setQuality(30).compressToBitmap(filePhoto);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapPhoto.compress(Bitmap.CompressFormat.JPEG, 30, byteArrayOutputStream);
            final byte[] photo_byte = byteArrayOutputStream.toByteArray();
            final StorageReference photoRef = sto.child("photos");
            UploadTask uploadTask = photoRef.putBytes(photo_byte);
            uploadTask
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            // Obtener progreso actual.
                            int uploadProgress = ((int) (100 * (taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount())));
                            // postEvent(VehicleEvent.UPLOAD_PROGRESS, uploadProgress);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // postEvent(VehicleEvent.UPLOAD_ERROR, mContext.getString(R.string.err));
                        }
                    })
                    .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            return photoRef.getDownloadUrl();
                        }
                    })
                    .addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri downloadUri = task.getResult();
                                if (downloadUri != null) {
                                    SimpleLog.i(TAG, "uploadPhoto().onComplete().  La foto se subio con exito.");
                                    vehicle.setPhotoUrl(downloadUri.toString());

                                    update(vehicle);

                                } else {
                                    SimpleLog.e(TAG, "uploadPhoto().onComplete().  La foto no se subio. No se pudo obtener url para descarga.");
                                    postEvent(VehicleEvent.ERROR, "¡Ups! Ocurrio un error");
                                }
                            } else {
                                SimpleLog.e(TAG, "uploadPhoto().onComplete().  Ocurrio un error al intentar subir la foto.  ", task.getException());
                                postEvent(VehicleEvent.ERROR, "¡Ups! Ocurrio un error");
                            }
                        }
                    });
        } catch (Exception e) {
            SimpleLog.e(TAG, "uploadPhoto().    Ocurrio un error al intentar subir foto.    " + e.getLocalizedMessage(), e);
            postEvent(VehicleEvent.ERROR, "¡Ups! Ocurrio un error");
        }
    }

    private void update(Vehicle vehicle) {
        DatabaseReference ref = mDatabase.getVehiclesRef();
        ref.child(vehicle.getId()).updateChildren(vehicle.toMap());
        postEvent(VehicleEvent.SUCCESS, "Guardado");
    }

    private void postEvent(int eventType, String message) {
        GreenRobotEventBus.getInstance().post(new VehicleEvent(eventType, message));
    }
}
