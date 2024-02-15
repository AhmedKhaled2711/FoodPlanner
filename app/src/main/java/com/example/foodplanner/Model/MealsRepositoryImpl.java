package com.example.foodplanner.Model;

import com.example.foodplanner.NetworkCall.MealsRemoteDataSource;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.NetworkCall.NetworkCallBack;

import io.reactivex.rxjava3.core.Observable;

public class MealsRepositoryImpl implements MealsRepository {

    MealsRemoteDataSource mealsRemoteDataSource ;

    static MealsRepositoryImpl mealsRepositoryImpl = null ;



    private MealsRepositoryImpl(MealsRemoteDataSource mealsRemoteDataSource
            ) {
        this.mealsRemoteDataSource = mealsRemoteDataSource;

    }

    public static MealsRepositoryImpl getInstance(MealsRemoteDataSource mealsRemoteDataSource){
        if(mealsRepositoryImpl ==null){
            mealsRepositoryImpl = new MealsRepositoryImpl(
                    mealsRemoteDataSource);
        }
        return mealsRepositoryImpl;
    }
    @Override
    public void getRandomMeal(NetworkCallBack networkCallBack) {
        mealsRemoteDataSource.makeNetworkCall(networkCallBack);
    }

    @Override
    public void getCategories(NetworkCallBack networkCallBack) {
        mealsRemoteDataSource.makeNetworkCall_getCategories(networkCallBack);
    }

    @Override
    public void getMealsFromCategories(NetworkCallBack networkCallBack , String categoryName) {
        mealsRemoteDataSource.makeNetworkCall_getMealFromCategories(networkCallBack , categoryName);
    }
}
