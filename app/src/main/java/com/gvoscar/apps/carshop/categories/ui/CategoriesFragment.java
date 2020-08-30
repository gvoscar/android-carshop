package com.gvoscar.apps.carshop.categories.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import com.gvoscar.apps.carshop.categories.adapters.CategoriesAdapter;
import com.gvoscar.apps.carshop.categories.adapters.OnCategoriesListener;
import com.gvoscar.apps.carshop.categories.presenters.CategoriesPresenter;
import com.gvoscar.apps.carshop.categories.presenters.CategoriesPresenterImpl;
import com.gvoscar.apps.carshop.logs.SimpleLog;
import com.gvoscar.apps.carshop.pojos.Category;

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
public class CategoriesFragment extends Fragment implements CategoriesView, OnCategoriesListener {

    private static final String TAG = CategoriesFragment.class.getSimpleName();

    @BindView(R.id.shimmer)
    ShimmerFrameLayout shimmer;
    @BindView(R.id.containerLoader)
    ConstraintLayout containerLoader;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.containerInfo)
    ConstraintLayout containerInfo;

    @BindView(R.id.containerCategories)
    ConstraintLayout containerCategories;
    private CategoriesAdapter mAdapter;
    private CategoriesPresenter mPresenter;
    private CarShopApp mSession;

    public CategoriesFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);

        this.mPresenter = new CategoriesPresenterImpl(this);
        this.mPresenter.onCreate();
        this.mAdapter = new CategoriesAdapter(this);
        this.mSession = (CarShopApp) ((getActivity() != null) ? getActivity().getApplication() : null);

        shimmer.startShimmer();

        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.mAdapter);

        return view;
    }

    @Override
    public void onNotFound(String message) {
        shimmer.stopShimmer();
        containerLoader.setVisibility(View.GONE);
        containerInfo.setVisibility(View.VISIBLE);
        showErrorMessage(message);
    }

    @Override
    public void onDataLoaded(List<Category> list) {
        SimpleLog.i(TAG, "Cantidad de datos:    " + list.size());
        mAdapter.clear();
        mAdapter.addAll(list);
        shimmer.stopShimmer();

        containerLoader.setVisibility(View.GONE);
        containerInfo.setVisibility(View.VISIBLE);
    }

    @Override
    public void onCategorySelected(Category category) {
        if (mSession!=null){
            mSession.setCategory(category);
            startActivity(new Intent(getActivity(), CategoryActivity.class));
        }
    }

    public void showErrorMessage(String message) {
        Snackbar snackbar = Snackbar.make(containerCategories, message, 5600);
        View sbView = snackbar.getView();
        //sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBarRed));
        snackbar.show();
    }
}
