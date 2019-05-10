package com.mobpro.foody;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {
    public final static String CHANEL_ID = "com.mobpro.foody.ShoppingService.ShoppingService";
    public final static String EXTRA_SHOPPING = "ShoppingList";

    @Override
    public void onCreate(){
        super.onCreate();

        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel serviceChannel = new NotificationChannel(CHANEL_ID, "Shopping List Service", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
}
