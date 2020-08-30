package com.gvoscar.apps.carshop.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.gvoscar.apps.carshop.R;
import com.gvoscar.apps.carshop.database.Database;
import com.gvoscar.apps.carshop.pojos.Category;

import java.util.ArrayList;

/**
 * @author Oscar Giraldo
 * Instagram    : gvoscar_
 * Twitter      : gvoscar_
 * GitHub       : gvoscar
 * Linkedin     : gvoscar20
 * Facebook     : gvoscarr
 */
public class CategoryCustomFilterAdapter extends ArrayAdapter<Category> {

    private static final String TAG = CategoryCustomFilterAdapter.class.getSimpleName();
    private ArrayList<Category> lista = null;
    private ArrayList<Category> suggestions = null;
    private Database mDatabase = null;
    private DatabaseReference mReference = null;

    public CategoryCustomFilterAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        lista = new ArrayList<>();
        suggestions = new ArrayList<>();
        mDatabase = Database.getInstance();
        mReference = mDatabase.getCategoriesRef();
        mReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Category value = snapshot.getValue(Category.class);
                        if (value != null) {
                            lista.add(value);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Error
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category category = getItem(position);

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.content_item_category_custom_filter_adapter, parent, false);
        }

        if (category != null) {
            TextView txtName = view.findViewById(R.id.txtName);
            txtName.setText(category.getName());

            TextView extra1 = view.findViewById(R.id.extra1);
            extra1.setText((category.isWritePermission()) ? "Escritura" : "");

            TextView extra2 = view.findViewById(R.id.extra2);
            extra2.setText((category.isReadPermission()) ? "Lectura" : "");

            TextView extra3 = view.findViewById(R.id.extra3);
            extra3.setText((category.isEditPermission()) ? "Edición" : "");
        }

        return view;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            return ((Category) resultValue).getName();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null) {
                suggestions.clear();

                for (Category categoria : lista) {
                    if (categoria.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(categoria);
                    }
                }

                results.values = suggestions;
                results.count = suggestions.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results != null && results.count > 0) {
                try {
                    ArrayList<Category> values = (ArrayList<Category>) results.values;
                    clear();
                    addAll(values);
                } catch (Exception e) {
                    ///
                }
            } else {
                notifyDataSetInvalidated();
            }
        }
    };
}
