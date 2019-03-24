package com.example.servicereveiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;

import javax.security.auth.Destroyable;

public class DemoMusicPlayerService extends Service{

    //@androidx.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startPlayer();
        return Service.START_NOT_STICKY;
    }

    private void startPlayer(){
        if(playThread != null && playThread.isAlive()) return;
        startPlayThread();
        startForeground(NOTIFICATION_ID, createNotification("Playing..."));
    }

    @Override
    public void onDestroy(){
        stopPlayThread();
        stopForeground(true);
    }


}
