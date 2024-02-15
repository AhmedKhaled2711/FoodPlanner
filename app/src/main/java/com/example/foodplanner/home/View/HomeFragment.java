package com.example.foodplanner.home.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.home.Presenter.HomePresenter;
import com.example.foodplanner.home.Presenter.HomePresenterImpl;

import java.util.List;


public class HomeFragment extends Fragment implements HomeView {
    Meal randomMeal ;
    ImageView ivRandomMeal ;
    TextView tvRandomMeal ;
    Button btnFavorite , btnPlan ;
    HomePresenter homePresenter ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivRandomMeal = view.findViewById(R.id.iv_card_meal);
        tvRandomMeal = view.findViewById(R.id.tv_card_meal);
        homePresenter = new HomePresenterImpl(this , MealsRepositoryImpl.getInstance
                (MealsRemoteDataSourceImpl.getInstance()));
        homePresenter.getRandomMealPresenter();
    }

    @Override
    public void showRandomMeal(List<Meal> meals) {

        tvRandomMeal.setText(meals.get(0).getStrMeal());
        Glide.with(this).load(meals.get(0).getStrMealThumb()).into(ivRandomMeal);

    }
}