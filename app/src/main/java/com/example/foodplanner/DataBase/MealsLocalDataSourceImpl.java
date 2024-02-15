package com.example.foodplanner.DataBase;

import android.content.Context;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealsLocalDataSourceImpl implements  MealsLocalDataSource{
    private Flowable<List<Meal>> storedMeals;
    private static MealsLocalDataSourceImpl connectToMeal = null ;
    private  MealDAO dao ;

    private MealsLocalDataSourceImpl(Context context) {
        AppDataBase db =AppDataBase.getInstance(context.getApplicationContext());
        dao = db.getMealDAO();
        storedMeals = dao.getRandomMeal();
    }

    public static MealsLocalDataSourceImpl getInstance(Context context){
        if(connectToMeal==null){
            connectToMeal = new MealsLocalDataSourceImpl(context);
        }
        return connectToMeal;
    }

    @Override
    public Flowable<List<Meal>> getRandomMeal() {
        return storedMeals;
    }
}
