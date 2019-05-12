package com.mobpro.foody;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class UserFragment extends Fragment {

    private static final String TAG = "MainActivity";

    public static final String SHARED_PREFERENCES = "sharedPrefLogin";
    public static final String TEXT = "email";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FirebaseApp.initializeApp(getContext());

        return inflater.inflate(R.layout.fragment_user, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        String email;
        Context mContext = getContext();
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        email = sharedPreferences.getString(TEXT, "");
        TextView tV_Username = (TextView) getView().findViewById(R.id.tV_UUsername);
        tV_Username.setText(email);

        final Button button = (Button) getView().findViewById(R.id.btn_LogOut);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut(v);
            }
        });
    }

    public void LogOut(View v){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        Fragment fragment = new ProfileFragment();

        getFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container, fragment).
                commit();
    }


}
