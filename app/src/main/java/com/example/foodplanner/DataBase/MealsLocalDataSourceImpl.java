package com.example.foodplanner.DataBase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealsLocalDataSourceImpl implements  MealsLocalDataSource{
    private Flowable<List<Meal>> storedMeals;
    private static MealsLocalDataSourceImpl connectToMeal = null ;
    private  MealDAO dao ;

   ;

    private MealsLocalDataSourceImpl(Context context) {
        AppDataBase db =AppDataBase.getInstance(context.getApplicationContext());
        dao = db.getMealDAO();
        storedMeals = dao.getAllMeals();
    }

    public static MealsLocalDataSourceImpl getInstance(Context context){
        if(connectToMeal==null){
            connectToMeal = new MealsLocalDataSourceImpl(context);
        }
        return connectToMeal;
    }
    @Override
    public Flowable<List<Meal>> getAllMealsData() {
        return storedMeals;
    }

    @Override
    public void insert(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                dao.insertMeal(meal);
            }
        }.start();
    }

    @Override
    public void delete(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                dao.deleteMeal(meal);
            }
        }.start();

    }
}
