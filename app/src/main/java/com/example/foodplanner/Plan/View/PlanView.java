package com.example.foodplanner.Plan.View;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlanView {
    void getPlanData();
    void removePlanMeal(Meal meal);

    void showDataPlanMeal(Flowable<List<Meal>> meals);
}
