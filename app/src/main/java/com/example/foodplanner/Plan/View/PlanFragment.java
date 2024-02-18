package com.example.foodplanner.Plan.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.SharedViewModel;
import com.example.foodplanner.home.View.HomeFragmentAdapter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PlanFragment extends Fragment {
    TextView tv_Saturday , tv_Sunday, tv_Saturday_show , tv_Sunday_show ,tv_Monday_show,tv_Tuesday_show,tv_Wednesday_show ,tv_Thursday_show,tv_Friday_show  ;
    TextView tv_Monday , tv_Tuesday ,tv_Wednesday, tv_Thursday, tv_Friday ;
    private SharedViewModel sharedViewModel;
    public static final String IS_NAME = "KEY_IS";
    String Monday = "Monday";
    String Tuesday = "Tuesday";
    String Wednesday = "Wednesday";
    String Thursday = "Thursday";
    String Friday = "Friday";
    String Saturday = "Saturday";
    String Sunday = "Sunday";

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
        tv_Sunday  = view.findViewById(R.id.tv_Sunday);
        tv_Monday = view.findViewById(R.id.tv_Monday);
        tv_Tuesday = view.findViewById(R.id.tv_Tuesday);
        tv_Wednesday = view.findViewById(R.id.tv_Wednesday);
        tv_Thursday = view.findViewById(R.id.tv_Thursday);
        tv_Friday = view.findViewById(R.id.tv_Friday);


        tv_Saturday_show = view.findViewById(R.id.Saturday);
        tv_Sunday_show = view.findViewById(R.id.Sunday);
        tv_Monday_show = view.findViewById(R.id.Monday);
        tv_Tuesday_show = view.findViewById(R.id.Tuesday);
        tv_Wednesday_show = view.findViewById(R.id.Wednesday);
        tv_Thursday_show = view.findViewById(R.id.Thursday);
        tv_Friday_show = view.findViewById(R.id.Friday);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);



        tv_Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Saturday);
                Navigation.findNavController(v).navigate(action);
            }
        });
        sharedViewModel.getSelectedItemSaturday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                // Update UI with the selected item for Saturday
                //oast.makeText(getContext(), "Data for Saturday: " + selectedItem.getStrMeal(), Toast.LENGTH_SHORT).show();
                // Update any other UI elements as needed
                tv_Saturday_show.setVisibility(View.VISIBLE);
                tv_Saturday_show.setText(selectedItem.getStrMeal().toString());
            }
        });
        tv_Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Sunday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemSunday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                // Update UI with the selected item for Sunday
                //Toast.makeText(getContext(), "Data for Sunday: " + selectedItem.getStrMeal(), Toast.LENGTH_SHORT).show();
                // Update any other UI elements as needed
                tv_Sunday_show.setVisibility(View.VISIBLE);
                tv_Sunday_show.setText(selectedItem.getStrMeal().toString());
            }
        });

        tv_Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Monday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemMonday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {

                tv_Monday_show.setVisibility(View.VISIBLE);
                tv_Monday_show.setText(selectedItem.getStrMeal().toString());
            }
        });

        tv_Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Tuesday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemTuesday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                tv_Tuesday_show.setVisibility(View.VISIBLE);
                tv_Tuesday_show.setText(selectedItem.getStrMeal().toString());
            }
        });

        tv_Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Wednesday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemWednesday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                tv_Wednesday_show.setVisibility(View.VISIBLE);
                tv_Wednesday_show.setText(selectedItem.getStrMeal().toString());
            }
        });

        tv_Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Thursday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemThursday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {

                tv_Thursday_show.setVisibility(View.VISIBLE);
                tv_Thursday_show.setText(selectedItem.getStrMeal().toString());
            }
        });

        tv_Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Sunday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemFriday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                tv_Friday_show.setVisibility(View.VISIBLE);
                tv_Friday_show.setText(selectedItem.getStrMeal().toString());
            }
        });
    }

}