package com.mobpro.foody;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
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
import com.mobpro.foody.ShoppingService.ActionReceiver;
import com.mobpro.foody.ShoppingService.ShoppingService;
import com.mobpro.foody.ShoppingService.ShoppingServiceApi;

import java.util.ArrayList;
import java.util.List;

public class ShoppingFragment extends Fragment {

    private String DEFAULT_LIST = "list";
    private ShoppingListViewModel viewModel;
    private View rootView;

    private int itemCount;
    private List<ShoppingList> shoppingItems;

    private ShoppingService myService;
    private boolean isBound;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_shopping, null);
        Intent intent = new Intent(this.getActivity(), ShoppingService.class);
        this.getActivity().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
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
                itemCount = adapter.getItemCount();
                shoppingItems = shoppingLists;
            }
        });

        final Button button = (Button) getView().findViewById(R.id.btn_AddToList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveList(v);
            }
        });

        final Button button_start = (Button) getView().findViewById(R.id.btn_start_service);
        button_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_service();
            }
        });

        final Button button_stop = (Button) getView().findViewById(R.id.btn_stop_service);
        button_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop_service();
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
        List<String> shoppingListItems = new ArrayList<>();
        for(int i = 0; i < itemCount; i++){
            ShoppingList item = shoppingItems.get(i);
            shoppingListItems.add(item.getMenge()+ " " + item.getProductName());
        }

        Intent intent = new Intent(this.getActivity(), ShoppingService.class);
        intent.putExtra("itemCount", itemCount);
        intent.putStringArrayListExtra("items", (ArrayList<String>) shoppingListItems);
        this.getActivity().startService(intent);
        ActionReceiver.setService(myService);
    }

    public void stop_service(){
        myService.stopShopping();
    }

    public void showNextItem(){
        myService.showNextItem();
    }

    public void showPreviousItem(){
        myService.showPreviousItem();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ShoppingService.LocalBinder binder = (ShoppingService.LocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };
}
