package com.example.foodplanner.NetworkCall;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.CountryResponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.MealResponse;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealService {

     @GET("random.php")
     Observable<MealResponse> getRandomMeal();
     @GET("categories.php")
     Observable<CategoryResponse> getCategories();

    @GET("filter.php")
    Observable<MealResponse> getMealByCategory(@Query("c")String category);

    @GET("list.php?a=list")
    Observable<CountryResponse> getCountries();

    @GET("list.php?i=list")
    Observable<IngredientResponse> getIngredients();

    @GET("filter.php")
    Observable<MealResponse> getMealsOfCountry(@Query("a")String area);

    @GET("search.php")
    Observable<MealResponse> searchByName(@Query("s") String mealName);




}
