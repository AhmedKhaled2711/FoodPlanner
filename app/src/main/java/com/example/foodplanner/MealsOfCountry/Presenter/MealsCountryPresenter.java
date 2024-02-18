package com.example.foodplanner.MealsOfCountry.Presenter;

import com.example.foodplanner.Model.Meal;

public interface MealsCountryPresenter {

    public  void  getMealsFromCountryPresenter(String country);

    public void addFavMeal(Meal meal);

}
