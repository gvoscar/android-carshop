package com.gvoscar.apps.carshop.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.categories.ui.CategoriesFragment;
import com.gvoscar.apps.carshop.categories.ui.CategoryActivity;
import com.gvoscar.apps.carshop.vehicles.ui.VehicleActivity;
import com.gvoscar.apps.carshop.vehicles.ui.VehiclesFragment;

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
public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.bottomFloating)
    FloatingActionButton bottomFloating;
    @BindView(R.id.home_coordinator_layout)
    CoordinatorLayout homeCoordinatorLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        showFragment(new VehiclesFragment());
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setToolbar() {
        // toolbar.setTitle("CarShop");

        //toolbar.setNavigationIcon(R.drawable.ic_atras);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_category:
                startActivity(new Intent(this, CategoryActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_menu_cars) {
            showFragment(new VehiclesFragment());
        }
        if (item.getItemId() == R.id.action_menu_categories) {
            showFragment(new CategoriesFragment());
        }
        if (item.getItemId() == R.id.action_menu_none) {

        }
        return true;
    }

    @OnClick(R.id.bottomFloating)
    public void onClickBottomFloating() {
        startActivity(new Intent(this, VehicleActivity.class));
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.homeFrame, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}