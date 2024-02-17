package com.example.foodplanner.mealsCategories.Presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.NetworkCallBack;
import com.example.foodplanner.mealsCategories.View.CategoriesView;

import java.util.List;

public class CategoriesPresenterImpl implements CategoriesPresenter , NetworkCallBack {
    MealsRepository mealsRepository ;
    CategoriesView categoriesView ;


    public CategoriesPresenterImpl(CategoriesView categoriesView , MealsRepositoryImpl mealsRepositoryImpl){
        this.categoriesView = categoriesView ;
        this.mealsRepository = mealsRepositoryImpl ;
    }

    @Override
    public void onSuccess(List<Meal> meals) {

    }

    @Override
    public void onSuccess_getCategories(List<Category> categories) {

    }

    @Override
    public void onSuccessMealFromCategories(List<Meal> meals) {
        categoriesView.showMealsOfCategory(meals);
    }

    @Override
    public void onSuccessCountries(List<Country> countries) {

    }

    @Override
    public void onSuccessIngredients(List<Ingredient> ingredients) {

    }

    @Override
    public void onFail(String err) {

    }

    @Override
    public void getMealsFromCategoriesPresenter(String category) {
        mealsRepository.getMealsFromCategories(this ,category);
    }

    @Override
    public void addFavMeal(Meal meal) {
        mealsRepository.insertMeal(meal);
    }

    @Override
    public void addPlanMeal(Meal meal) {

    }
}
