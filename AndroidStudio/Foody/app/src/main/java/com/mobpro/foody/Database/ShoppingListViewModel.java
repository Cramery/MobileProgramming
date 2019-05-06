package com.mobpro.foody.Database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class ShoppingListViewModel extends AndroidViewModel {
    private ShoppingListRepository repository;
    private LiveData<List<ShoppingList>> allNotes;

    public ShoppingListViewModel(@NonNull Application application) {
        super(application);
        repository = new ShoppingListRepository(application);
        allNotes = repository.showAll();
    }

    public void insert(ShoppingList shoppinglist){
        repository.insert(shoppinglist);
    }

    public LiveData<List<ShoppingList>> showAll(){
        return allNotes;
    }

    public LiveData<List<ShoppingList>> showList(String liste){
        return repository.showList(liste);
    }

    public void updateShopped(String liste, String product, int shopped){
        repository.updateShopped(liste, product, shopped);
    }

    void updateMenge(String liste, String product, String menge){
        repository.updateMenge(liste, product, menge);
    }

    void updateList(ShoppingList shoppingList){
        repository.updateList(shoppingList);
    }
}
