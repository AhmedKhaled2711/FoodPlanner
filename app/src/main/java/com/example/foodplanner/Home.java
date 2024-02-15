package com.example.foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.foodplanner.home.View.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity  {
    private BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
            /*
            homeFragment = new HomeFragment();
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction transFragment = fragManager.beginTransaction();
            transFragment.add(R.id.homeFragment, homeFragment, "DYNAMIC");
            transFragment.commit();*/


        NavController navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }


}
