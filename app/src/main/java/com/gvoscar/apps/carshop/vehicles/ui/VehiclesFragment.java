package com.gvoscar.apps.carshop.vehicles.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.snackbar.Snackbar;
import com.gvoscar.apps.carshop.CarShopApp;
import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.pojos.Vehicle;
import com.gvoscar.apps.carshop.vehicles.adapters.OnVehiclesListener;
import com.gvoscar.apps.carshop.vehicles.adapters.VehiclesAdapter;
import com.gvoscar.apps.carshop.vehicles.presenters.VehiclesPresenter;
import com.gvoscar.apps.carshop.vehicles.presenters.VehiclesPresenterImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class VehiclesFragment extends Fragment implements VehiclesView, OnVehiclesListener {
    private static final String TAG = VehiclesFragment.class.getSimpleName();
    @BindView(R.id.shimmer)
    ShimmerFrameLayout shimmer;
    @BindView(R.id.containerLoader)
    ConstraintLayout containerLoader;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.containerInfo)
    ConstraintLayout containerInfo;
    @BindView(R.id.framgeVehicles)
    FrameLayout framgeVehicles;
    @BindView(R.id.txtNotFound)
    TextView txtNotFound;

    private VehiclesAdapter mAdapter;
    private VehiclesPresenter mPresenter;
    private CarShopApp mSession;

    public VehiclesFragment() {
    }

    @Override
    public void onDestroyView() {
        this.mPresenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mPresenter.getData();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicles, container, false);
        ButterKnife.bind(this, view);

        this.mPresenter = new VehiclesPresenterImpl(this);
        this.mPresenter.onCreate();

        this.mAdapter = new VehiclesAdapter(getActivity(), this);
        this.mSession = (CarShopApp) ((getActivity() != null) ? getActivity().getApplication() : null);

        shimmer.startShimmer();

        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.mAdapter);

        return view;
    }

    @Override
    public void onNotFound(String message) {
        // shimmer.stopShimmer();
        // containerLoader.setVisibility(View.GONE);
        // containerInfo.setVisibility(View.VISIBLE);
        // txtNotFound.setVisibility(View.VISIBLE);
        //showMessage(message);
    }

    @Override
    public void onDataLoaded(List<Vehicle> list) {
        SimpleLog.i(TAG, "Cantidad de datos:    " + list.size());
        mAdapter.clear();
        mAdapter.addAll(list);
        shimmer.stopShimmer();
        txtNotFound.setVisibility(View.GONE);


        containerLoader.setVisibility(View.GONE);
        containerInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onVehiclesSelected(Vehicle vehicle) {
        if (mSession != null) {
            mSession.setVehicle(vehicle);
            startActivity(new Intent(getActivity(), VehicleActivity.class));
        }
    }

    public void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(framgeVehicles, message, 5600);
        View sbView = snackbar.getView();
        //sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBarRed));
        snackbar.show();
    }

}
