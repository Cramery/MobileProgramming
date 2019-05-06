package com.mobpro.foody.Database;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobpro.foody.R;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListAdapter extends RecyclerView.Adapter<ShoppingListAdapter.ListHolder> {

    private List<ShoppingList> items = new ArrayList<>();

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        ShoppingList currentItem = items.get(position);
        holder.textViewProduct.setText(currentItem.getProductName());
        holder.textViewMenge.setText(currentItem.getMenge());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<ShoppingList> items){
        this.items = items;
        notifyDataSetChanged();
    }

    class ListHolder extends RecyclerView.ViewHolder{
        private TextView textViewProduct;
        private TextView textViewMenge;

        public ListHolder(View itemView){
            super(itemView);
            textViewProduct = itemView.findViewById(R.id.text_view_product);
            textViewMenge = itemView.findViewById(R.id.text_view_menge);
        }
    }
}
