package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class Main extends AppCompatActivity {
    //public static int SPLASH_TIMER = 3000 ;
    NavigationView navigationView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        NavController navController = Navigation.findNavController(this ,R.id.nav_host_fragment);

    }
}