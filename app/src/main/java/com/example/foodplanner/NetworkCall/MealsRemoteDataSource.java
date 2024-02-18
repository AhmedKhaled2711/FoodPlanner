package com.example.foodplanner.NetworkCall;

import com.example.foodplanner.Model.MealResponse;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRemoteDataSource {
    Observable<MealResponse>  makeNetworkCall();

    void  makeNetworkCall_getCategories(NetworkCallBack networkCallBack);
    void  makeNetworkCall_getMealFromCategories(NetworkCallBack networkCallBack , String categoryName);

    void  makeNetworkCall_getCountries(NetworkCallBack networkCallBack);
    void  makeNetworkCall_getIngredients(NetworkCallBack networkCallBack);
    void  makeNetworkCall_getMealsOfCountry(NetworkCallBack networkCallBack , String CountryName);

    void  makeNetworkCall_SearchByName(NetworkCallBack networkCallBack , String mealName);


}
