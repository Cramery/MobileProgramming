package com.mobpro.foody.ShoppingService;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.mobpro.foody.Database.ShoppingList;

import java.util.ArrayList;
import java.util.List;

import static com.mobpro.foody.App.CHANEL_ID;
import static com.mobpro.foody.App.EXTRA_SHOPPING;

public class ShoppingService extends Service implements ShoppingServiceApi {
    private static final String LOG_TAG = "ShoppingService";

    private boolean serviceRunning = false;
    ArrayList<String> shoppingList = new ArrayList<>();
    private final IBinder iBinder = new LocalBinder();
    int maxItems;
    int actualItem;
    Notification notification;

    public class LocalBinder extends Binder{
        ShoppingService getService(){
            return ShoppingService.this;
        }
    }

    @Override
    public String showPreviousItem() {
        if(actualItem > 0){
            actualItem--;
        }
        return null;
    }

    @Override
    public String showNextItem() {
        if(actualItem < maxItems-1){
            actualItem++;
        }
        return null;
    }

    @Override
    public IBinder onBind(Intent intent){
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        if(!serviceRunning){
            actualItem = 0;
            //shoppingList = intent.getStringArrayListExtra(EXTRA_SHOPPING);
            Log.v(LOG_TAG, "onStartCommand()");

            notification = createNotification();
            startForeground(2, notification);
            serviceRunning = true;
        }

        return Service.START_NOT_STICKY;
    }

    private Notification createNotification() {
        Notification notification = new NotificationCompat.Builder(this, CHANEL_ID)
                .setContentTitle("Shopping List")
                .setContentText("Testing " + actualItem)
                .setWhen(System.currentTimeMillis())
                .build();
        return notification;
    }

    @Override
    public void onDestroy(){
        Log.v(LOG_TAG, "onDestroy()");
        stopForeground(true);
        serviceRunning = false;
    }
}
