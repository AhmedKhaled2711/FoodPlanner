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
    public void getCategories(NetworkCallBack networkCallBack) {
        mealsRemoteDataSource.makeNetworkCall_getCategories(networkCallBack);
    }

    @Override
    public void getMealsFromCategories(NetworkCallBack networkCallBack , String categoryName) {
        mealsRemoteDataSource.makeNetworkCall_getMealFromCategories(networkCallBack , categoryName);
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
    public void getCountries(NetworkCallBack networkCallBack) {
        mealsRemoteDataSource.makeNetworkCall_getCountries(networkCallBack);
    }

    @Override
    public void getIngredients(NetworkCallBack networkCallBack) {
        mealsRemoteDataSource.makeNetworkCall_getIngredients(networkCallBack);
    }
}
