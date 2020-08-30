package com.gvoscar.apps.carshop.vehicles.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.gvoscar.apps.carshop.CarShopApp;
import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.permissions.CameraPermitDialog;
import com.gvoscar.apps.carshop.permissions.WritePermitDialog;
import com.gvoscar.apps.carshop.pojos.Category;
import com.gvoscar.apps.carshop.pojos.Vehicle;
import com.gvoscar.apps.carshop.utils.Util;
import com.gvoscar.apps.carshop.vehicles.presenters.VehiclePresenter;
import com.gvoscar.apps.carshop.vehicles.presenters.VehiclePresenterImpl;
import com.gvoscar.apps.carshop.widgets.CategoryCustomFilterAdapter;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class VehicleActivity extends AppCompatActivity implements VehicleView {
    private static final String TAG = VehicleActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imgFoto)
    ImageView imgFoto;
    @BindView(R.id.txtAsientos)
    EditText txtAsientos;
    @BindView(R.id.txtPrecio)
    EditText txtPrecio;
    @BindView(R.id.swUsado)
    Switch swUsado;
    @BindView(R.id.txtModelo)
    EditText txtModelo;
    @BindView(R.id.txtCategoria)
    AutoCompleteTextView txtCategoria;
    @BindView(R.id.containerVehicle)
    ConstraintLayout containerVehicle;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private CategoryCustomFilterAdapter autocompleteAdapter;
    private Vehicle vehicle;
    private CarShopApp mSession;
    private Category currentCategory;
    private VehiclePresenter mPresenter;
    private String pathPhoto = null;

    private static final String PREFIX = "PHOTO_VEHICLE";
    private static final String SUFFIX = ".jpg";
    private static final String AUTHORITY = "com.gvoscar.apps.carshop";
    private static final int REQUEST_CODE = 101;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        ButterKnife.bind(this);

        pathPhoto = null;
        mPresenter = new VehiclePresenterImpl(this,this);
        mPresenter.onCreate();
        vehicle = new Vehicle();
        mSession = (CarShopApp) getApplication();
        if (mSession.getVehicle() != null) {
            vehicle = mSession.getVehicle();
            cargardatos();
        }


        setToolbar();
        initAdapters();
    }

    @Override
    protected void onDestroy() {
        mSession.setVehicle(null);
        mPresenter.onDestroy();
        super.onDestroy();
    }

    private void cargardatos() {
        if (vehicle.getPhotoUrl() != null) {
            Glide.with(this).load(vehicle.getPhotoUrl()).into(imgFoto);
        }
        txtAsientos.setText(String.valueOf(vehicle.getOccupants()));
        txtPrecio.setText("" + vehicle.getPrice());
        swUsado.setChecked(vehicle.isUsed());
        txtModelo.setText(vehicle.getModel());
        if (vehicle.getCategory() != null) {
            txtCategoria.setText(vehicle.getCategory().getName());
            currentCategory = vehicle.getCategory();
        }
    }

    public void setToolbar() {
        toolbar.setTitle("Vehículo");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlacoClaro));
        toolbar.setNavigationIcon(R.drawable.ic_atras);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void initAdapters() {
        autocompleteAdapter = new CategoryCustomFilterAdapter(this, R.layout.content_item_category_custom_filter_adapter);
        txtCategoria.setThreshold(1);
        txtCategoria.setAdapter(autocompleteAdapter);
        txtCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentCategory = ((Category) parent.getAdapter().getItem(position));
                //vehicle.setCategory(((Category) parent.getAdapter().getItem(position)));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vehicle_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_camera:
                if (CameraPermitDialog.isPermissionGranted(this)) {
                    if (!WritePermitDialog.isPermissionGranted(this)) {
                        new WritePermitDialog().show(getFragmentManager(), "permiteEscribir");
                    } else {
                        // TOMAR FOTO!!!
                        onTakePhoto();
                    }
                } else {
                    new CameraPermitDialog().show(getFragmentManager(), "permisoCamara");
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void onTakePhoto() {
        try {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) == null) {
                showErrorMessage("Error de compatibilidad con ultima versión de Android.");
                return;
            }

            File photoFile = crearArchivoFoto();

            if (photoFile == null) {
                SimpleLog.e(TAG, "onClickBtnTakePhoto().    No se pudo crear archivo para la foto.");
                showErrorMessage("Error de compatibilidad con ultima versión de Android.");
                return;
            }

            Uri photoUri = FileProvider.getUriForFile(this, AUTHORITY, photoFile);

            // Indicar que se va guardar la imagen con permiso de lectura y iniciar la captura de la foto.
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
            takePictureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(takePictureIntent, REQUEST_CODE);

        } catch (Exception e) {
            SimpleLog.e(TAG, "onClickBtnTakePhoto().   No se pudo tomar la foto: " + e.getLocalizedMessage(), e.getCause());
            showErrorMessage("Error de compatibilidad con ultima versión de Android.");
        }
    }

    private File crearArchivoFoto() {
        try {
            // long timestamp = Util.getCurrentTimeMillis();
            File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File archivo = File.createTempFile(PREFIX, SUFFIX, directorio);
            pathPhoto = archivo.getAbsolutePath();
            return archivo;
        } catch (IOException io) {
            SimpleLog.e(TAG, "crearArchivoFoto().   No se pudo crear: " + io.getLocalizedMessage(), io.getCause());
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {
            pathPhoto = null;
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                getPhotoResult();
            }
        }
    }

    private void getPhotoResult() {
        // Nota: esto se valida despues por si ocurre algo, el usuario pueda repetir la foto:
        if (pathPhoto == null) {
            SimpleLog.e(TAG, "getPhotoResult().   No se pudo obtener resultado por que no hay foto capturada.");
            showErrorMessage("Algo salio mal, por favor intente tomar la foto de nuevo.");
            return;
        }

        addPhotoGallery();
        Glide.with(this).load(pathPhoto).into(imgFoto);
    }

    private void addPhotoGallery() {
        SimpleLog.i(TAG, "addPhotoGallery().    Agregar foto a la galeria.");
        try {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File file = new File(pathPhoto);
            Uri uri = Uri.fromFile(file);
            mediaScanIntent.setData(uri);
            sendBroadcast(mediaScanIntent);
        } catch (Exception e) {
            SimpleLog.e(TAG, "addPhotoGallery().   Ocurrio un error al intentar agregar foto a la galeria. " + e.getMessage(), e);
        }
    }

    @OnClick(R.id.btnSave)
    public void onClicSave() {
        if (txtAsientos.getText().toString().length() <= 0) {
            txtAsientos.setError("Dato requerido");
            return;
        }
        if (txtPrecio.getText().toString().length() <= 0) {
            txtPrecio.setError("Dato requerido");
            return;
        }
        if (txtModelo.getText().toString().length() <= 0) {
            txtModelo.setError("Dato requerido");
            return;
        }
        if (currentCategory == null) {
            txtCategoria.setError("Busca y selecciona una categoria");
            showErrorMessage("Selecciona una categoria");
            return;
        }
        if (vehicle.getCategory() != null) {
            if (!vehicle.getCategory().isEditPermission()) {
                showErrorMessage("No se puede editar el vehículo con categoría " + vehicle.getCategory().getName());
                return;
            }
        }

        vehicle.setOccupants(Integer.parseInt(txtAsientos.getText().toString().trim()));
        vehicle.setPrice(Double.parseDouble(txtPrecio.getText().toString().trim()));
        vehicle.setUsed(swUsado.isChecked());
        vehicle.setModel(txtModelo.getText().toString().trim());
        vehicle.setCategory(currentCategory);
        vehicle.setCreateAt(Util.getCurrentTimeMillis());

        progressBar.setVisibility(View.VISIBLE);
        btnSave.setEnabled(false);
        mPresenter.guardarVehiculo(vehicle, pathPhoto);
    }

    @Override
    public void onError(String message) {
        showErrorMessage(message);
        btnSave.setEnabled(true);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess() {
        mSession.setVehicle(null);
        finish();
    }

    public void showErrorMessage(String message) {
        Snackbar snackbar = Snackbar.make(containerVehicle, message, 5600);
        View sbView = snackbar.getView();
        //sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBarRed));
        snackbar.show();
    }
}
