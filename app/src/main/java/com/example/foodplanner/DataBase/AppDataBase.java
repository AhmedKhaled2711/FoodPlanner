package com.example.foodplanner.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.Model.Meal;

@Database(entities = {Meal.class}, version = 2,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance = null;
    public abstract MealDAO getMealDAO();
    public static synchronized AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class, "mealsdb")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
