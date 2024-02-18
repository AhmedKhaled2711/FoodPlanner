package com.example.foodplanner.Search.Presenter;

import com.example.foodplanner.Model.Meal;

public interface SearchPresenter {

    public void getIngredientsPresenter();
    public void getCountriesPresenter();

    public void getMealPresenter(String name);

    public void addFavMeal(Meal meal);

    public void addPlanMeal(Meal meal);

}
