package com.gvoscar.apps.carshop.categories.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.pojos.Category;

import java.util.ArrayList;
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
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    private static final String TAG = CategoriesAdapter.class.getSimpleName();


    private List<Category> lista;
    private OnCategoriesListener listener;

    public CategoriesAdapter(OnCategoriesListener listener) {
        this.lista = new ArrayList<>();
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_item_categories_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = lista.get(position);
        holder.txtTitle.setText(category.getName());
        holder.txtDescription.setText(category.getId());
        holder.setOnCategoriesListener(category, listener);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void addAll(List<Category> list) {
        lista.addAll(list);
        notifyDataSetChanged();
    }

    public void add(Category category) {
        lista.add(category);
        notifyDataSetChanged();
    }

    public void remove(Category category) {
        lista.remove(category);
        notifyDataSetChanged();
    }

    public void clear() {
        lista.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.txtDescription)
        TextView txtDescription;
        @BindView(R.id.item)
        LinearLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        /**
         * Establecer un oyente de categorias
         *
         * @param category Categoria.
         * @param listener Oyente.
         */
        public void setOnCategoriesListener(Category category, OnCategoriesListener listener) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCategorySelected(category);
                }
            });
        }
    }
}
