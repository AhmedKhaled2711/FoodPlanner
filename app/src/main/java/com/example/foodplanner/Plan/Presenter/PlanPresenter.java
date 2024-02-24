package com.example.foodplanner.Plan.Presenter;

import com.example.foodplanner.Model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface PlanPresenter {
    void addToPlan (Meal meal);

    Flowable<List<Meal>> getStoredPlan(String day);
}
