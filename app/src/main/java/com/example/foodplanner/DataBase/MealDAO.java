package com.example.foodplanner.DataBase;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM MealsTable")
    Flowable<List<Meal>> getRandomMeal() ;
}
