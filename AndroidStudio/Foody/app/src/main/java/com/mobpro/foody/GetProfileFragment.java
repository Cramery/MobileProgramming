package com.mobpro.foody;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Context.MODE_PRIVATE;

public class GetProfileFragment extends Fragment {

    public static final String SHARED_PREFERENCES = "sharedPrefLogin";
    public static final String TEXT = "email";
    Fragment fragment = new ProfileFragment();

    private static FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public <fragment> fragment GetProfileFragment() {
        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null){
            fragment = new UserFragment();
        }else{
            fragment = new ProfileFragment();
        }

        return (fragment) fragment;
    }
}
