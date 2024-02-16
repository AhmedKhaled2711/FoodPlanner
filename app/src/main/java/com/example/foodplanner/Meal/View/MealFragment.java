package com.example.foodplanner.Meal.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;


public class MealFragment extends Fragment {
    private Meal receivedMeal ;
    Toolbar toolbar;
    ImageView ivMeal ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivMeal = view.findViewById(R.id.img_meal);
        receivedMeal =MealFragmentArgs.fromBundle(getArguments()).getMeal();
        Glide.with(view).load(receivedMeal.getStrMealThumb()).into(ivMeal);

        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(receivedMeal.getStrMeal());
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }
}