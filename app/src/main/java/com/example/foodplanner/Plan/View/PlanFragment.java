package com.example.foodplanner.Plan.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.R;


public class PlanFragment extends Fragment {
    TextView tv_Saturday;
    private static final int REQUEST_CODE_FAVORITE = 101;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_Saturday = view.findViewById(R.id.tv_Saturday);
        tv_Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Navigation.findNavController(v).navigate(R.id.action_planFragment_to_favoriteFragment);


            }
        });


    }

}