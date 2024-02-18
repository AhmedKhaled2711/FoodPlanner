package com.example.foodplanner.home.Presenter;

import android.util.Log;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.NetworkCallBack;
import com.example.foodplanner.home.View.HomeView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

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
    public void onSuccessCountries(List<Country> countries) {

    }

    @Override
    public void onSuccessIngredients(List<Ingredient> ingredients) {

    }

    @Override
    public void onSuccessMealsFromCountry(List<Meal> meals) {

    }

    @Override
    public void onSuccessSearchMeal(List<Meal> meals) {

    }


    @Override
    public void onFail(String err) {

    }

    @Override
    public void getRandomMealPresenter() {
        //mealsRepository.getRandomMeal(this);
        Observable<MealResponse> observable = mealsRepository.getRandomMeal();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("TAG", "onSubscribe: getRandomMealPresenter");

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        homeView.showRandomMeal(mealResponse.meals);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.i("TAG", "onError: getRandomMealPresenter");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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

}
