package com.example.foodplanner.Model;


import androidx.lifecycle.LiveData;

import com.example.foodplanner.NetworkCall.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {

    void getRandomMeal(NetworkCallBack networkCallBack);

    void getCategories(NetworkCallBack networkCallBack);

    void getMealsFromCategories (NetworkCallBack networkCallBack , String categoryName);

    void insertMeal(Meal meal);
    LiveData<List<Meal>> getStoredMeals();
    public void deleteMeal(Meal meal);



}
