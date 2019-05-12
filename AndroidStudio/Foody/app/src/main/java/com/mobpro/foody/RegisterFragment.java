package com.mobpro.foody;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Context.MODE_PRIVATE;

public class RegisterFragment extends Fragment {

    private static final String TAG = "MainActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FirebaseApp.initializeApp(getContext());

        return inflater.inflate(R.layout.fragment_register, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final Button button = (Button) getView().findViewById(R.id.btn_Register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register(v);
            }
        });

    }

    public void Register(View v){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        EditText eT_REmail = (EditText) getView().findViewById(R.id.eT_REmail);
        EditText eT_RPassword = (EditText) getView().findViewById(R.id.eT_RPassword);

        String email = eT_REmail.getText().toString();
        String password = eT_RPassword.getText().toString();

        //Register
        

        Fragment fragment = new ProfileFragment();

        getFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, fragment).
                commit();
    }


}
