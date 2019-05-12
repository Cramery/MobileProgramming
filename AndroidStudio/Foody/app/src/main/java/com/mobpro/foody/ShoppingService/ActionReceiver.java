package com.mobpro.foody.ShoppingService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ActionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action=intent.getStringExtra("action");
        if(action.equals("end")){
            stop_service(context);
        }
        //This is used to close the notification tray
        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);
    }

    public void stop_service(Context context){
        Intent intent = new Intent(context, ShoppingService.class);
        context.stopService(intent);
    }
}
