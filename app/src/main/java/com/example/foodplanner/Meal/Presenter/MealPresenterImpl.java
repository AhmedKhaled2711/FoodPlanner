package com.example.foodplanner.Meal.Presenter;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.home.View.HomeView;

public class MealPresenterImpl implements MealPresenter{

    MealsRepository mealsRepository ;

    public MealPresenterImpl(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    @Override
    public void addFavMeal(Meal meal) {
        mealsRepository.insertMeal(meal);
    }

    @Override
    public void addPlanMeal(Meal meal) {

    }
}
