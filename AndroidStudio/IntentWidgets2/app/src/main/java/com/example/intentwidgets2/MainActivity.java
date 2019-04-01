package com.example.intentwidgets2;

import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.intentwidgets2.App.SHOW_TEXT;

public class MainActivity extends AppCompatActivity {
    Intent browerCall = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browerCall.setAction(Intent.ACTION_VIEW);
        browerCall.setData(Uri.parse("http://hslu.ch"));


        Intent startingIntent = getIntent();
        if (startingIntent.getAction() != null && startingIntent.getAction().equals(SHOW_TEXT)) {
            TextView textView = findViewById(R.id.show_intentText);
            textView.setText(startingIntent.getStringExtra("text"));
        }
    }

    public void startBrowser(View v) {
        startActivity(browerCall);
    }

    public void browserAbfragen(View v) {
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(browerCall, PackageManager.MATCH_DEFAULT_ONLY);
        List<String> resolveNames = new ArrayList<>();
        resolveInfos.forEach(resolveInfo -> resolveNames.add(resolveInfo.activityInfo.name));
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Alle Browser Activities gemäss Intent-Abfrage")
                .setItems(resolveNames.toArray(new String[]{}), null)
                .setPositiveButton("Danke, Packagemanager!", null).show();
    }

    public void startCustomIntentOnClick(final View v){
        final Intent customIntent = new Intent();
        customIntent.setAction(SHOW_TEXT);
        final String myText = "Activity getstartet drch folgende Intent-ACTION:\n" + SHOW_TEXT + "\nJetzt = " + new Date();
        customIntent.putExtra("text",myText);
        startActivity(customIntent);
    }

    public void onUpdate(View v){

    }
}
