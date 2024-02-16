package com.example.foodplanner.Favorite.View;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface FavoriteView {
    void showData(LiveData<List<Meal>> meals);
}
