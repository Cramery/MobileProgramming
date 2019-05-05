package com.mobpro.foody.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.*;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities = {ShoppingList.class}, version = 1)
public abstract class ShoppingListDatabase extends RoomDatabase {
    public abstract ShoppingListDao shoppingListDao();

    private static volatile ShoppingListDatabase databaseInstance;

    public static synchronized ShoppingListDatabase getInstance(final Context context){
        if (databaseInstance == null){
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    ShoppingListDatabase.class, "shoppingList_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return databaseInstance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(databaseInstance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private ShoppingListDao dao;

        private PopulateDbAsyncTask(ShoppingListDatabase db){
            dao = db.shoppingListDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.insert(new ShoppingList("list", "Mehl", "1kg", 0));
            dao.insert(new ShoppingList("list", "Milch", "1l", 0));
            dao.insert(new ShoppingList("list", "Eier", "6", 0));
            return null;
        }
    }
}
