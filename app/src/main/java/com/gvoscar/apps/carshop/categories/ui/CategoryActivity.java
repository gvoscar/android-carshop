package com.gvoscar.apps.carshop.categories.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.gvoscar.apps.carshop.CarShopApp;
import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.categories.presenters.CategoryPresenter;
import com.gvoscar.apps.carshop.categories.presenters.CategoryPresenterImpl;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.pojos.Category;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CategoryActivity extends AppCompatActivity implements CategoryView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtName)
    EditText txtName;
    @BindView(R.id.swWrite)
    Switch swWrite;
    @BindView(R.id.swRead)
    Switch swRead;
    @BindView(R.id.swEdit)
    Switch swEdit;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.containerCategory)
    ConstraintLayout containerCategory;
    private CarShopApp mSession;
    private Category categoria;
    private CategoryPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        mPresenter = new CategoryPresenterImpl(this);
        categoria = new Category();
        mSession = (CarShopApp) getApplication();
        if (mSession.getCategory() != null) {
            categoria = mSession.getCategory();
            loadCategoria();
        }

        setToolbar();
    }

    private void loadCategoria() {
        txtName.setText(categoria.getName());
        swWrite.setChecked(categoria.isWritePermission());
        swRead.setChecked(categoria.isReadPermission());
        swEdit.setChecked(categoria.isEditPermission());
    }

    public void setToolbar() {
        toolbar.setTitle("Categoría");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorBlacoClaro));
        toolbar.setNavigationIcon(R.drawable.ic_atras);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @OnClick(R.id.btnSubmit)
    public void onClickBtnSubmit() {
        categoria.setName(txtName.getText().toString().trim());
        categoria.setWritePermission(swWrite.isChecked());
        categoria.setReadPermission(swRead.isChecked());
        categoria.setEditPermission(swEdit.isChecked());
        if (categoria.getId() == null) {
            mPresenter.agregarCategoria(categoria);
        } else {
            mPresenter.actualizarCategoria(categoria);
        }

        mSession.setCategory(null);
        finish();
    }

    @Override
    protected void onDestroy() {
        mSession.setCategory(null);
        super.onDestroy();
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onSuccess() {

    }
}
