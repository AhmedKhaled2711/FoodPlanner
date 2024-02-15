package com.example.foodplanner;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.home.View.HomeFragment;


public class WelcomeFragment extends Fragment {
    private View overlayView;
    private TextView tv_signIn ;
    private Button btn_guest ,btn_google ,btn_facebook , btn_mail ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_signIn = view.findViewById(R.id.tv_welcome_signIn);
        btn_guest = view.findViewById(R.id.btn_guest);
        btn_google = view.findViewById(R.id.btn_welcome_google);
        btn_facebook = view.findViewById(R.id.btn_welcome_facebook);
        btn_mail = view.findViewById(R.id.btn_welcome_email);

        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_signUpFragment2);
            }
        });

        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_welcomeFragment_to_loginFragment2);
            }
        });

        btn_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* HomeFragment homeFragment = new HomeFragment();
                FragmentManager fragManager = getSupportFragmentManager();
                FragmentTransaction transFragment = fragManager.beginTransaction();
                transFragment.add(R.id.homeFragment, homeFragment, "DYNAMIC");
                transFragment.commit();*/
            }
        });
    }
}