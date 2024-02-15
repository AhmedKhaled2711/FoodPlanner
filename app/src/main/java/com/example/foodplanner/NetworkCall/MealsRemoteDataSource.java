package com.example.foodplanner.NetworkCall;

import com.example.foodplanner.Model.MealResponse;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRemoteDataSource {
    void  makeNetworkCall(NetworkCallBack networkCallBack);


}
