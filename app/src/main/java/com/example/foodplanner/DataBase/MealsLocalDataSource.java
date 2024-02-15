package com.example.foodplanner.DataBase;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealsLocalDataSource {
    Flowable<List<Meal>> getRandomMeal();
}
