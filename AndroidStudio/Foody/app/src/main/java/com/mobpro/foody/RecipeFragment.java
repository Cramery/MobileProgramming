package com.mobpro.foody;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RecipeFragment extends Fragment {

    private static final String TAG = "MainActivity";

    private static final String KEY_TITLE = "title";
    private static final String KEY_DESCRIPTION  = "description";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FirebaseApp.initializeApp(getContext());

        return inflater.inflate(R.layout.fragment_recipe, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final Button button = (Button) getView().findViewById(R.id.btn_saveRecipe);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveRecipe(v);
            }
        });


        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        db.collection("Recipes").document("Recipes").set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }



    public void SaveRecipe(View view) {
        Log.w(TAG, "Button");

        Toast.makeText(getContext(), "Button Clicked", Toast.LENGTH_LONG).show();
        EditText eT_Title = (EditText) getView().findViewById(R.id.eT_Title);
        EditText eT_Description = (EditText) getView().findViewById(R.id.eT_Description);
        EditText eT_Ingredient = (EditText) getView().findViewById(R.id.eT_Ingredients);
        EditText eT_Instructions = (EditText) getView().findViewById(R.id.eT_instructions);

        String title = eT_Title.getText().toString();
        String desctiption = eT_Description.getText().toString();
        String ingredient = eT_Ingredient.getText().toString();
        String instructions = eT_Instructions.getText().toString();

        Map<String, Object> recipe = new HashMap<>();
        recipe.put("title", title);
        recipe.put("desctiption", desctiption);
        recipe.put("ingredient", ingredient);
        recipe.put("instructions", instructions);

        db.collection("Recipes").document("Recipes").set(recipe)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Succeeded", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }
}
