package com.example.foodplanner.DataBase;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealsLocalDataSource {
    Flowable<List<Meal>> getAllMealsData();

    void insert(Meal meal);

    void delete(Meal meal);

    void insertMeal(Meal meal) ;

    Flowable<List<Meal>> getAllPlanedMeal(String day);
}
