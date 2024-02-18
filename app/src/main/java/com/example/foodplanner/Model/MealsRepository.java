package com.example.foodplanner.Model;


import androidx.lifecycle.LiveData;

import com.example.foodplanner.NetworkCall.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {

    //void getRandomMeal(NetworkCallBack networkCallBack);
    Observable<MealResponse> getRandomMeal();
    void getCategories(NetworkCallBack networkCallBack);

    void getMealsFromCategories (NetworkCallBack networkCallBack , String categoryName);

    void getMealsFromCountry (NetworkCallBack networkCallBack , String countryName);

    void getMeal (NetworkCallBack networkCallBack , String mealName);
    void insertMeal(Meal meal);
    LiveData<List<Meal>> getStoredMeals();
    public void deleteMeal(Meal meal);

    void getCountries(NetworkCallBack networkCallBack);

    void getIngredients(NetworkCallBack networkCallBack);


}
