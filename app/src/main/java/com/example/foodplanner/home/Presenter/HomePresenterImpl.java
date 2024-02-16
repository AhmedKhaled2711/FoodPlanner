package com.example.foodplanner.home.Presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.NetworkCallBack;
import com.example.foodplanner.home.View.HomeView;

import java.util.List;

public class HomePresenterImpl implements HomePresenter , NetworkCallBack {
    HomeView homeView ;
    MealsRepository mealsRepository ;





    public  HomePresenterImpl (HomeView homeView , MealsRepositoryImpl mealsRepositoryImpl)
    {
        this.homeView = homeView ;
        this.mealsRepository = mealsRepositoryImpl ;
    }

    @Override
    public void onSuccess(List<Meal> meals) {
        homeView.showRandomMeal(meals);
    }

    @Override
    public void onSuccess_getCategories(List<Category> categories) {
        homeView.showCategories(categories);
    }

    @Override
    public void onSuccessMealFromCategories(List<Meal> meals) {

    }


    @Override
    public void onFail(String err) {

    }

    @Override
    public void getRandomMealPresenter() {
        mealsRepository.getRandomMeal(this);
    }

    @Override
    public void getCategoriesPresenter() {
        mealsRepository.getCategories(this);
    }

    @Override
    public void addFavMeal(Meal meal) {
        mealsRepository.insertMeal(meal);
    }

    @Override
    public void addPlanMeal(Meal meal) {

    }



/*
    @Override
    public void getRandomMeal() {
        Observable<MealResponse> observable =mealsRepository.getRandomMeal();
        observable.observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("TAG", "onSubscribe: HomePresenterImpl");
                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        Log.i("TAG", "onNext:HomePresenterImpl ");
                        homeView.showRandomMeal((Meal) mealResponse.meals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("TAG", "onError: HomePresenterImpl");
                    }

                    @Override
                    public void onComplete() {

                    }
                }
        );

    }

    @Override
    public void onSuccess(List<Meal> meals) {

    }

    @Override
    public void onFail(String err) {

    }*/
}
