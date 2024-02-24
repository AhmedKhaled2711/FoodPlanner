package com.example.foodplanner.DataBase;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealsLocalDataSourceImpl implements  MealsLocalDataSource{
    private Flowable<List<Meal>> storedMeals;
    private static MealsLocalDataSourceImpl connectToMeal = null ;
    private  MealDAO dao ;
    private Flowable<List<Meal>> storedPlanMeal;

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

    @Override
    public void insertMeal(Meal meal) {
        new Thread(){
            @Override
            public void run() {
                dao.insertMeal(meal);
            }
        }.start();
    }

    @Override
    public Flowable<List<Meal>> getAllPlanedMeal(String day) {
        setDay(day);
        return storedPlanMeal;
    }

    void setDay(String day){
        switch (day){
            case "Saturday":
                storedPlanMeal=dao.getPlanMeal("Saturday");
                break;
            case "Sunday":
                storedPlanMeal=dao.getPlanMeal("Sunday");
                break;
            case "Monday":
                storedPlanMeal=dao.getPlanMeal("Monday");
                break;
            case "Tuesday":
                storedPlanMeal=dao.getPlanMeal("Tuesday");
                break;
            case "Wednesday":
                storedPlanMeal=dao.getPlanMeal("Wednesday");
                break;
            case "Thursday":
                storedPlanMeal=dao.getPlanMeal("Thursday");
                break;
            case "Friday":
                storedPlanMeal=dao.getPlanMeal("Friday");
                break;
        }
    }
}
