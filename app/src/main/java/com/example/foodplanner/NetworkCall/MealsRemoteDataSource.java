package com.example.foodplanner.NetworkCall;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.CountryResponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.MealResponse;

import io.reactivex.rxjava3.core.Observable;

public interface MealsRemoteDataSource {

    Observable<MealResponse>  makeNetworkCall();
    Observable<CategoryResponse>  makeNetworkCall_getCategories();
    Observable<MealResponse>  makeNetworkCall_getMealFromCategories( String categoryName);
    Observable<CountryResponse>  makeNetworkCall_getCountries();
    Observable<IngredientResponse>  makeNetworkCall_getIngredients();
    Observable<MealResponse>  makeNetworkCall_getMealsOfCountry( String CountryName);
    Observable<MealResponse>  makeNetworkCall_SearchByName(String mealName);


}
