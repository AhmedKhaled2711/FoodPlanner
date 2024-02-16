package com.example.foodplanner.Favorite.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface FavoritePresenter {
    public LiveData<List<Meal>> getStoredDataDB();

    public void removeFavMeal(Meal meal);

}
