package com.example.foodplanner.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDAO {
    @Query("SELECT * FROM MealsTable")
    Flowable<List<Meal>> getAllMeals() ;

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal (Meal meal);
    @Delete
    void deleteMeal (Meal meal);

    @Query("select * from MealsTable where mealDate= :day")
    Flowable<List<Meal>> getPlanMeal(String day);


}
