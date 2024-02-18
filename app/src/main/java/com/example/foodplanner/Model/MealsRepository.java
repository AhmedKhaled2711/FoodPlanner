package com.example.foodplanner.Model;


import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {

    //void getRandomMeal(NetworkCallBack networkCallBack);
    Observable<MealResponse> getRandomMeal();
    Observable<CategoryResponse> getCategories();

    Observable<MealResponse> getMealsFromCategories (String categoryName);

    Observable<MealResponse> getMealsFromCountry (String countryName);

    Observable<MealResponse> getMeal (String mealName);
    void insertMeal(Meal meal);
    Flowable<List<Meal>> getStoredMeals();
    public void deleteMeal(Meal meal);

    Observable<CountryResponse> getCountries();

    Observable<IngredientResponse> getIngredients();


}
