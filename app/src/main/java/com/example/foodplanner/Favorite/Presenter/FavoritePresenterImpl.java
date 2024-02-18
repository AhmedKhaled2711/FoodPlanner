package com.example.foodplanner.Favorite.Presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class FavoritePresenterImpl implements FavoritePresenter {
    private MealsRepository mealsRepository ;

    public FavoritePresenterImpl (MealsRepository mealsRepository )
    {
        this.mealsRepository = mealsRepository ;
    }
    @Override
    public Flowable<List<Meal>> getStoredDataDB() {
        return mealsRepository.getStoredMeals();
    }

    @Override
    public void removeFavMeal(Meal meal) {
        mealsRepository.deleteMeal(meal);
    }
}
