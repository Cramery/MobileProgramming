package com.mobpro.foody;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView tV_RLogin = (TextView) getView().findViewById(R.id.tV_RLogin);
        tV_RLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(v);
            }
        });

    }

    public void Login(View v){
        Fragment fragment = new ProfileFragment();

        getFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, fragment).
                commit();
    }

    public void Register(View v){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        EditText eT_REmail = (EditText) getView().findViewById(R.id.eT_REmail);
        EditText eT_RPassword = (EditText) getView().findViewById(R.id.eT_RPassword);

        String email = eT_REmail.getText().toString();
        String password = eT_RPassword.getText().toString();

        //Register
        if (email.isEmpty()){
            eT_REmail.setError("Email is Required");
            eT_REmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            eT_REmail.setError("Please enter a valid email");
            eT_REmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            eT_RPassword.setError("Password is Required");
            eT_RPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            eT_RPassword.setError("Minimum length of password is 6");
            eT_RPassword.requestFocus();
            return;
        }

        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if(task.isSuccessful()){
                              Fragment fragment = new UserFragment();

                              getFragmentManager().
                                      beginTransaction().
                                      replace(R.id.fragment_container, fragment).
                                      commit();
                          }
                      }
                  }
                );
    }


}
