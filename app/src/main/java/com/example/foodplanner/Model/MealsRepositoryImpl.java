package com.example.foodplanner.Model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.DataBase.MealsLocalDataSource;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSource;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.NetworkCall.NetworkCallBack;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class MealsRepositoryImpl implements MealsRepository {

    MealsRemoteDataSource mealsRemoteDataSource ;
    MealsLocalDataSource mealsLocalDataSource ;

    static MealsRepositoryImpl mealsRepositoryImpl = null ;



    private MealsRepositoryImpl(MealsRemoteDataSource mealsRemoteDataSource
            ) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;

    }

    private MealsRepositoryImpl(MealsRemoteDataSource mealsRemoteDataSource,
                                MealsLocalDataSource mealsLocalDataSource)
    {
        this.mealsRemoteDataSource = mealsRemoteDataSource;
        this.mealsLocalDataSource = mealsLocalDataSource ;
    }

    public static MealsRepositoryImpl getInstance(MealsRemoteDataSource mealsRemoteDataSource){
        if(mealsRepositoryImpl ==null){
            mealsRepositoryImpl = new MealsRepositoryImpl(
                    mealsRemoteDataSource);
        }
        return mealsRepositoryImpl;
    }

    public static MealsRepositoryImpl getInstance(MealsRemoteDataSource mealsRemoteDataSource,
                                                  MealsLocalDataSource mealsLocalDataSource){
        if(mealsRepositoryImpl ==null){
            mealsRepositoryImpl = new MealsRepositoryImpl(
                    mealsRemoteDataSource ,mealsLocalDataSource);
        }
        return mealsRepositoryImpl;
    }
/*
    @Override
    public void getRandomMeal(NetworkCallBack networkCallBack) {
        mealsRemoteDataSource.makeNetworkCall(networkCallBack);
    }*/


    @Override
    public Observable<MealResponse> getRandomMeal() {
        return mealsRemoteDataSource.makeNetworkCall();
    }

    @Override
    public  Observable<CategoryResponse> getCategories() {
        return mealsRemoteDataSource.makeNetworkCall_getCategories();
    }

    @Override
    public Observable<MealResponse> getMealsFromCategories(String categoryName) {
        return mealsRemoteDataSource.makeNetworkCall_getMealFromCategories(categoryName);
    }

    @Override
    public Observable<MealResponse> getMealsFromCountry( String countryName) {
        return  mealsRemoteDataSource.makeNetworkCall_getMealsOfCountry(countryName);
    }

    @Override
    public Observable<MealResponse> getMeal(String MealName) {
        return mealsRemoteDataSource.makeNetworkCall_SearchByName(MealName);
    }

    @Override
    public void insertMeal(Meal meal) {
        mealsLocalDataSource.insert(meal);
    }

    @Override
    public LiveData<List<Meal>> getStoredMeals() {
        return mealsLocalDataSource.getAllMealsData();
    }

    @Override
    public void deleteMeal(Meal meal) {
        mealsLocalDataSource.delete(meal);
    }

    @Override
    public Observable<CountryResponse> getCountries() {
        return  mealsRemoteDataSource.makeNetworkCall_getCountries();
    }

    @Override
    public Observable<IngredientResponse> getIngredients() {
        return  mealsRemoteDataSource.makeNetworkCall_getIngredients();
    }
}
