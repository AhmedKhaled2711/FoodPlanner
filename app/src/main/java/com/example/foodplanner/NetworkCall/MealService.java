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

    //@GET("random.php")
    //Call<MealResponse> getRandomMeal();
    @GET("random.php")
    Observable<MealResponse> getRandomMeal();
    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("filter.php")
    Call<MealResponse> getMealByCategory(@Query("c")String category);

    @GET("list.php?a=list")
    Call<CountryResponse> getCountries();

    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredients();

    @GET("filter.php")
    Call<MealResponse> getMealsOfCountry(@Query("a")String area);

    @GET("search.php")
    Call<MealResponse> searchByName(@Query("s") String mealName);




}
