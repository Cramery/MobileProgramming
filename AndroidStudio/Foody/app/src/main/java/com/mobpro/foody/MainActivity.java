package com.mobpro.foody;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.mobpro.foody.Database.ShoppingListViewModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment());
    }

    public boolean loadFragment(Fragment fragment) {
        if (fragment != null){
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_container, fragment).
                    commit();
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch(menuItem.getItemId()){
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_add:
                fragment = new RecipeFragment();
                break;
            case R.id.navigation_shopping:
                fragment = new ShoppingFragment();
                break;
            case R.id.navigation_profile:
                GetProfileFragment getProfileFragment = new GetProfileFragment();
                fragment = getProfileFragment.GetProfileFragment();
                //fragment = new ProfileFragment();
                break;
        }

        return loadFragment(fragment);

    }
}
