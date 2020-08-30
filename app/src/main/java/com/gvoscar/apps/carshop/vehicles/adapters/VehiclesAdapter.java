package com.gvoscar.apps.carshop.vehicles.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.categories.adapters.CategoriesAdapter;
import com.gvoscar.apps.carshop.pojos.Category;
import com.gvoscar.apps.carshop.pojos.Vehicle;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.ViewHolder> {
    private static final String TAG = VehiclesAdapter.class.getSimpleName();

    private Context context;
    private List<Vehicle> lista;
    private OnVehiclesListener listener;

    public VehiclesAdapter(Context context, OnVehiclesListener listener) {
        this.context = context;
        this.lista = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_item_vehicles_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle vehicle = lista.get(position);
        if (vehicle.getPhotoUrl() != null) {
            Glide.with(context).load(vehicle.getPhotoUrl()).into(holder.img);
        }
        holder.txtTitle.setText(vehicle.getModel());
        holder.txtPrice.setText(String.format(context.getString(R.string.app_format_currency_cop), vehicle.getPrice()));
        holder.txtUsed.setText(((vehicle.isUsed()) ? "Usado" : "Nuevo"));
        if(vehicle.getCategory()!=null){
            holder.txtCategory.setText(vehicle.getCategory().getName());
        }
        holder.setOnVehiclesListener(vehicle, listener);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void addAll(List<Vehicle> list) {
        lista.addAll(list);
        notifyDataSetChanged();
    }

    public void add(Vehicle vehicle) {
        lista.add(vehicle);
        notifyDataSetChanged();
    }

    public void remove(Vehicle vehicle) {
        lista.remove(vehicle);
        notifyDataSetChanged();
    }

    public void clear() {
        lista.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;

        @BindView(R.id.txtTitle)
        TextView txtTitle;

        @BindView(R.id.txtPrice)
        TextView txtPrice;

        @BindView(R.id.txtUsed)
        TextView txtUsed;

        @BindView(R.id.txtCategory)
        TextView txtCategory;

        @BindView(R.id.item)
        LinearLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOnVehiclesListener(Vehicle vehicle, OnVehiclesListener listener) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onVehiclesSelected(vehicle);
                }
            });
        }
    }
}
