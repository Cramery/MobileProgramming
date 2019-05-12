package com.mobpro.foody.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ShoppingListRepository {
    private ShoppingListDao dao;
    private LiveData<List<ShoppingList>> allNotes;

    public ShoppingListRepository(Application application){
        ShoppingListDatabase database = ShoppingListDatabase.getInstance(application);
        dao = database.shoppingListDao();
        allNotes = dao.showAll();
    }

    public void insert(ShoppingList shoppinglist){
        new InsertAsyncTask(dao).execute(shoppinglist);
    }

    public LiveData<List<ShoppingList>> showAll() {
        return allNotes;
    }

    public LiveData<List<ShoppingList>> showList(String liste){
        return dao.showList(liste);
    }

    public void updateShopped(String liste, String product, int shopped){
        new UpdateShoppedAsyncTask(dao, liste,product,shopped).execute();
    }

    void updateMenge(String liste, String product, String menge){
        new UpdateMengeAsyncTask(dao, liste, product, menge);
    }

    void updateList(ShoppingList shoppingList){
        new UpdateAsyncTask(dao).execute(shoppingList);
    }

    private static class InsertAsyncTask extends AsyncTask<ShoppingList, Void, Void>{
        private ShoppingListDao dao;

        private InsertAsyncTask(ShoppingListDao dao){
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            dao.insert(shoppingLists[0]);
            return null;
        }
    }

    private static class UpdateShoppedAsyncTask extends AsyncTask<Void, Void, Void>{
        private ShoppingListDao dao;
        private String list;
        private String product;
        private int shopped;

        private UpdateShoppedAsyncTask(ShoppingListDao dao, String list, String product, int shopped){
            this.dao = dao;
            this.list = list;
            this.product = product;
            this.shopped = shopped;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.updateShopped(this.list,this.product,this.shopped);
            return null;
        }
    }

    private static class UpdateMengeAsyncTask extends AsyncTask<Void, Void, Void>{
        private ShoppingListDao dao;
        private String list;
        private String product;
        private String menge;

        private UpdateMengeAsyncTask(ShoppingListDao dao, String list, String product, String menge){
            this.dao = dao;
            this.list = list;
            this.product = product;
            this.menge = menge;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.updateMenge(this.list, this.product, this.menge);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<ShoppingList, Void, Void> {
        private ShoppingListDao dao;

        private UpdateAsyncTask(ShoppingListDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ShoppingList... shoppingLists) {
            dao.updateList(shoppingLists[0]);
            return null;
        }
    }
}
