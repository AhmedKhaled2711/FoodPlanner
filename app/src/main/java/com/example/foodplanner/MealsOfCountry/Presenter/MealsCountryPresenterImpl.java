package com.example.foodplanner.MealsOfCountry.Presenter;

import com.example.foodplanner.MealsOfCountry.View.MealsCountryView;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.NetworkCall.NetworkCallBack;

import java.util.List;

public class MealsCountryPresenterImpl implements  MealsCountryPresenter , NetworkCallBack {
    MealsRepository mealsRepository ;
    MealsCountryView mealsCountryView ;

    public MealsCountryPresenterImpl(MealsRepository mealsRepository, MealsCountryView mealsCountryView) {
        this.mealsRepository = mealsRepository;
        this.mealsCountryView = mealsCountryView;
    }

    @Override
    public void getMealsFromCountryPresenter(String country) {
        mealsRepository.getMealsFromCountry(this , country);
    }

    @Override
    public void addFavMeal(Meal meal) {
        mealsRepository.insertMeal(meal);
    }

    @Override
    public void onSuccess(List<Meal> meals) {

    }

    @Override
    public void onSuccess_getCategories(List<Category> categories) {

    }

    @Override
    public void onSuccessMealFromCategories(List<Meal> meals) {

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {

    }

    @Override
    public void onSuccessIngredients(List<Ingredient> ingredients) {

    }

    @Override
    public void onSuccessMealsFromCountry(List<Meal> meals) {
        mealsCountryView.showMealsOfCountry(meals);
    }

    @Override
    public void onSuccessSearchMeal(List<Meal> meals) {

    }

    @Override
    public void onFail(String err) {

    }
}
