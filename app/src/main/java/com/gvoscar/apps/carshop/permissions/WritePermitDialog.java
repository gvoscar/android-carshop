package com.gvoscar.apps.carshop.permissions;

import android.Manifest;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.gvoscar.apps.carshop.R;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class WritePermitDialog extends DialogFragment {

    private static final String TAG = WritePermitDialog.class.getSimpleName();
    private static final int REQUEST = 500;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                        return i == KeyEvent.KEYCODE_BACK;
                    }
                })
                .setView(R.layout.dialog_permit_write)
                .setPositiveButton("Ok, entiendo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (ActivityCompat.checkSelfPermission(getActivity(),
                                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(),
                                    new String[]{
                                            Manifest.permission.READ_EXTERNAL_STORAGE,
                                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                    REQUEST);
                            dismiss();

                        } else {
                            getDialog().dismiss();
                        }

                    }
                })
                .setNegativeButton("No quiero", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    public static boolean isPermissionGranted(Context context) {
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            return false;
        } else {
            return true;
        }
    }
}