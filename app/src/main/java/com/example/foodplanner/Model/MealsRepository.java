package com.example.foodplanner.Model;


import androidx.lifecycle.LiveData;

import com.example.foodplanner.NetworkCall.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {

    //void getRandomMeal(NetworkCallBack networkCallBack);
    Observable<MealResponse> getRandomMeal();
    Observable<CategoryResponse> getCategories();

    Observable<MealResponse> getMealsFromCategories (String categoryName);

    Observable<MealResponse> getMealsFromCountry (String countryName);

    Observable<MealResponse> getMeal (String mealName);
    void insertMeal(Meal meal);
    LiveData<List<Meal>> getStoredMeals();
    public void deleteMeal(Meal meal);

    Observable<CountryResponse> getCountries();

    Observable<IngredientResponse> getIngredients();


}
