package com.mobpro.foody.Database;

import android.arch.persistence.room.*;

@Entity(tableName = "shoppingList", indices = {@Index(value = {"list_name", "product"},unique = true)})
public class ShoppingList {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "list_name")
    private String listName;

    @ColumnInfo(name = "product")
    private String productName;

    @ColumnInfo(name = "menge")
    private String menge;

    @ColumnInfo(name = "shopped")
    private int shopped;

    public ShoppingList(String listName, String productName, String menge, int shopped) {
        this.listName = listName;
        this.productName = productName;
        this.menge = menge;
        this.shopped = shopped;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getListName() {
        return listName;
    }

    public String getProductName() {
        return productName;
    }

    public String getMenge() {
        return menge;
    }

    public int getShopped() {
        return shopped;
    }
}
