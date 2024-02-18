package com.example.foodplanner.Favorite.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoritePresenter {
    public Flowable<List<Meal>> getStoredDataDB();
    public void removeFavMeal(Meal meal);

}
