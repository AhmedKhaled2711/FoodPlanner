package com.example.foodplanner.home.View;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface HomeView {
    public void showRandomMeal(List<Meal> meals);

    public  void showCategories(List<Category> categories);
}
