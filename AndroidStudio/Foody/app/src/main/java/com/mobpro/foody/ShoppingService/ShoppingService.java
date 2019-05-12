package com.mobpro.foody.ShoppingService;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.mobpro.foody.Database.ShoppingList;
import com.mobpro.foody.R;

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

            startForeground(1, createNotification());
            serviceRunning = true;
        }

        return Service.START_NOT_STICKY;
    }

    private Notification createNotification() {
        Intent intentActionEnd = new Intent(this,ActionReceiver.class);
        //This is optional if you have more than one buttons and want to differentiate between two
        intentActionEnd.putExtra("action","end");
        PendingIntent pIntentEnd = PendingIntent.getBroadcast(this,1,intentActionEnd,PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this, CHANEL_ID)
                .setContentTitle("Shopping List")
                .setContentText("Testing " + actualItem)
                .setSmallIcon(R.drawable.ic_shopping_black)
                .setWhen(System.currentTimeMillis())
                .addAction(R.drawable.ic_add_black, "End Shopping", pIntentEnd)
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
