package com.example.foodplanner.Favorite.View;



import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoriteView {
    void showData(Flowable<List<Meal>> meals);
}
