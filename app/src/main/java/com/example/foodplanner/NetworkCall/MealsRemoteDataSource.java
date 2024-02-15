package com.example.foodplanner.NetworkCall;

import com.example.foodplanner.Model.MealResponse;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRemoteDataSource {
    void  makeNetworkCall(NetworkCallBack networkCallBack);
    void  makeNetworkCall_getCategories(NetworkCallBack networkCallBack);
    void  makeNetworkCall_getMealFromCategories(NetworkCallBack networkCallBack , String categoryName);


}
