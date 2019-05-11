package com.mobpro.foody;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Details extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private TextView tV_Title;
    private TextView tV_Desc;
    private TextView tV_DIngredients;
    private TextView tV_DInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String message = intent.getStringExtra(HomeFragment.EXTRA_MESSAGE);

        FirebaseApp.initializeApp(this);

        tV_Title  = findViewById(R.id.tV_DTitle);
        tV_Desc  = findViewById(R.id.tV_DDescription);
        tV_DIngredients  = findViewById(R.id.tV_DIngredients);
        tV_DInstructions  = findViewById(R.id.tV_DInstructions);

        loadNote(message);
    }

    public void loadNote(String recipe){
        db.collection("Recipes").document(recipe).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            String title = documentSnapshot.getString("title");
                            String description = documentSnapshot.getString("description");
                            String ingredients = documentSnapshot.getString("ingredient");
                            String instructions = documentSnapshot.getString("instructions");
                            tV_Title.setText(title);
                            tV_Desc.setText(description);
                            tV_DIngredients.setText(ingredients);
                            tV_DInstructions.setText(instructions);
                        }else{
                            Toast.makeText(Details.this, "Document does not exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Details.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void backHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
