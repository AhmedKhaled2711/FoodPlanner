package com.example.foodplanner.home.Presenter;

import com.example.foodplanner.Model.Meal;

public interface HomePresenter {
    public void getRandomMealPresenter();
    public void getCategoriesPresenter();

    public void addFavMeal(Meal meal);

    public void addPlanMeal(Meal meal);

}
