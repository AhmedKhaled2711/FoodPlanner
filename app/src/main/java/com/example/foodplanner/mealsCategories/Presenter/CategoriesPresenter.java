package com.example.foodplanner.mealsCategories.Presenter;

import com.example.foodplanner.Model.Meal;

public interface CategoriesPresenter {
    public  void  getMealsFromCategoriesPresenter(String category);

    public void addFavMeal(Meal meal);

    public void addPlanMeal(Meal meal);
}
