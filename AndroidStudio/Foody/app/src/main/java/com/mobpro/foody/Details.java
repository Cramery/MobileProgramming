package com.mobpro.foody;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String message = intent.getStringExtra(HomeFragment.EXTRA_MESSAGE);

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }
}
