package com.mobpro.foody.ShoppingService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mobpro.foody.ShoppingFragment;

public class ActionReceiver extends BroadcastReceiver {

    private static ShoppingService service;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action=intent.getStringExtra("action");
        if(action.equals("end")){
            stop_service();
        }
        else if(action.equals("previous")){
            previous_item();
        }
        else if(action.equals("next")){
            next_item();
        }
        //This is used to close the notification tray
        Intent it = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        context.sendBroadcast(it);
    }

    public static void setService(ShoppingService service) {
        ActionReceiver.service = service;
    }

    private void next_item() {
        Log.v(this.getClass().toString(), "next_item()");
        service.showNextItem();
    }

    private void previous_item() {
        Log.v(this.getClass().toString(), "previous_item()");
        service.showPreviousItem();
    }

    private void stop_service(){
        Log.v(this.getClass().toString(), "stop_service()");
        service.stopShopping();
    }
}
