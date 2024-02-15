package com.example.foodplanner.NetworkCall;

import com.example.foodplanner.Model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET
    Observable<MealResponse> getMeals();

    @GET("random.php")
    Call<MealResponse> getRandomMeal();
}
