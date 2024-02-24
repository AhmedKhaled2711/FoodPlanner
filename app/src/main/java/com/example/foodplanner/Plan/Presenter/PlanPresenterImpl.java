package com.example.foodplanner.Plan.Presenter;

import com.example.foodplanner.DataBase.MealsLocalDataSource;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.Plan.View.PlanView;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenterImpl implements PlanPresenter{
    MealsRepository mealsRepository ;
    PlanView planView ;

    public PlanPresenterImpl(MealsRepository mealsRepository, PlanView planView) {
        this.mealsRepository = mealsRepository;
        this.planView = planView;
    }

    @Override
    public void addToPlan(Meal meal) {
        mealsRepository.insertMeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getStoredPlan(String day) {
        return mealsRepository.getPlanedMeal(day);
    }
}
