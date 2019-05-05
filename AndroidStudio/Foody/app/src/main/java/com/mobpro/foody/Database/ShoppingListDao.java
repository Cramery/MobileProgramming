package com.mobpro.foody.Database;

import java.util.List;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.*;

@Dao
public interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ShoppingList shoppingLists);

    @Query("SELECT * FROM ShoppingList")
    LiveData<List<ShoppingList>> showAll();

    @Query("SELECT * FROM ShoppingList WHERE list_name LIKE :liste")
    LiveData<List<ShoppingList>> showList(String liste);

    @Query("UPDATE ShoppingList SET shopped = :shopped WHERE list_name LIKE :liste AND product LIKE :product")
    void updateShopped(String liste, String product, int shopped);

    @Query("UPDATE ShoppingList SET menge = :menge WHERE list_name LIKE :liste AND product LIKE :product")
    void updateMenge(String liste, String product, String menge);

    @Update
    void updateList(ShoppingList shoppingList);
}
