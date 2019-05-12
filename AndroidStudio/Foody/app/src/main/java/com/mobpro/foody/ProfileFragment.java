package com.mobpro.foody;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private static final String TAG = "MainActivity";

    private static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private EditText eT_Email, eT_Password;

    public static final String SHARED_PREFERENCES = "sharedPrefLogin";
    public static final String TEXT = "email";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener(){

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Log.d(TAG, "onAuthStateChanged:signed_in: " + user.getUid());
                    saveLogin();
                    //Intent intent = new Intent(ProfileFragment.this.getActivity(), UserPage.class);
                    //startActivity(intent);
                }else{
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        eT_Email = (EditText) getView().findViewById(R.id.eT_Email);
        eT_Password = (EditText) getView().findViewById(R.id.eT_Password);

        final Button btn_login = (Button) getView().findViewById(R.id.btn_Login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogIn(v);
            }
        });

        final TextView tV_Register = (TextView) getView().findViewById(R.id.tV_Register);
        tV_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register(v);
            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        if (mAuth != null){
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void LogIn(View v){
        String email = eT_Email.getText().toString();
        String password = eT_Password.getText().toString();
        if(!email.equals("") && !password.equals("")){
            mAuth.signInWithEmailAndPassword(email, password);

            Fragment fragment = new UserFragment();

            getFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_container, fragment).
                    commit();
        }else{
            Toast.makeText(getContext(), "Pls. enter all fields", Toast.LENGTH_LONG).show();
        }
    }

    public void Register(View v){
        Fragment fragment = new RegisterFragment();

        getFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, fragment).
                commit();
    }

    public void saveLogin(){
        FirebaseUser user = mAuth.getCurrentUser();
        String mail = user.getEmail();
        Context mContext = getContext();

        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, mail);
        editor.commit();
    }
}
