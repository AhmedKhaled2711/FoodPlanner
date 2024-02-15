package com.example.foodplanner.Model;


import com.example.foodplanner.NetworkCall.NetworkCallBack;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRepository {

    //Observable<MealResponse> getRandomMeal();

    void getRandomMeal(NetworkCallBack networkCallBack);

    void getCategories(NetworkCallBack networkCallBack);

    void getMealsFromCategories (NetworkCallBack networkCallBack , String categoryName);


}
