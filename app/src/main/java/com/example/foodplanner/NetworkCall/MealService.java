package com.example.foodplanner.NetworkCall;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {
    @GET
    Observable<MealResponse> getMeals();

    @GET("random.php")
    Call<MealResponse> getRandomMeal();
    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("filter.php")
    Call<MealResponse> getMealByCategory(@Query("c")String category);


}
