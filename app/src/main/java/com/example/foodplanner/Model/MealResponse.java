package com.example.foodplanner.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MealResponse {
    @SerializedName("meals")
    public List<Meal> meals ;
}
