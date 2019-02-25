package ch.hslu.mobpro.firstapp;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

/**
 * Logs lifecycle transitions into the LogCat view of the ADT-Debugger.
 *
 * @author Ruedi Arnold
 */

public class LifecycleLogActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onCreate() aufgerufen");
    }

    // TODO: Add further implementions of onX-methods.
    public void onStart() {
        super.onStart();
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onCreate() aufgerufen");
    }

    public void onRestart() {
        super.onRestart();
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onRestart() aufgerufen");
    }

    public void onResume() {
        super.onResume();
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onResume() aufgerufen");
    }

    public void onPause() {
        super.onPause();
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onPause() aufgerufen");
    }

    public void onStop() {
        super.onStop();
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onStop() aufgerufen");
    }

    public void onDestroy() {
        super.onDestroy();
        setContentView(R.layout.lifecycle_logger);
        Log.i("hslu_mobApp", "onDestroy() aufgerufen");
    }



}