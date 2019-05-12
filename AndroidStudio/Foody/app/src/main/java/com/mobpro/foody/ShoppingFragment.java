package com.mobpro.foody;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobpro.foody.Database.ShoppingList;
import com.mobpro.foody.Database.ShoppingListAdapter;
import com.mobpro.foody.Database.ShoppingListViewModel;
import com.mobpro.foody.ShoppingService.ShoppingService;

import java.util.List;

public class ShoppingFragment extends Fragment {

    private String DEFAULT_LIST = "list";
    private ShoppingListViewModel viewModel;
    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shopping, null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        final ShoppingListAdapter adapter = new ShoppingListAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(ShoppingListViewModel.class);
        viewModel.showList(DEFAULT_LIST).observe(this, new Observer<List<ShoppingList>>() {
            @Override
            public void onChanged(@Nullable List<ShoppingList> shoppingLists) {
                adapter.setItems(shoppingLists);
            }
        });

        final Button button = (Button) getView().findViewById(R.id.btn_AddToList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveList(v);
            }
        });

        final Button button_start = (Button) getView().findViewById(R.id.start_service);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_service();
            }
        });
    }

    public void SaveList(View v){
        EditText eT_ingredient = (EditText) getView().findViewById(R.id.eT_NameIngredient);
        String ingredient = eT_ingredient.getText().toString();
        EditText eT_amount = (EditText) getView().findViewById(R.id.eT_AmountIngredient);
        String amount = eT_amount.getText().toString();
        viewModel.insert(new ShoppingList(DEFAULT_LIST, ingredient, amount,0));
    }

    public void start_service(){
        Intent intent = new Intent(this.getActivity(), ShoppingService.class);
        this.getActivity().startService(intent);
    }
}
