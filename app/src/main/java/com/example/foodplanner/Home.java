package com.example.foodplanner;

import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContentProviderCompat.requireContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.foodplanner.home.View.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends AppCompatActivity  {
    private BottomNavigationView bottomNavigationView;
    FloatingActionButton btn_logout ;
    HomeFragment homeFragment ;
    public static final String SHARED_PREF = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        btn_logout =  findViewById(R.id.btn_logout);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name", "");
                editor.apply();

                // Start the Login activity
                Intent intent = new Intent(Home.this , Main.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        NavController navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }


}
