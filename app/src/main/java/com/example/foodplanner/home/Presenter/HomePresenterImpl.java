package com.example.foodplanner.home.Presenter;

import android.util.Log;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.home.View.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class HomePresenterImpl implements HomePresenter  {
    HomeView homeView ;
    MealsRepository mealsRepository ;
    public  HomePresenterImpl (HomeView homeView , MealsRepositoryImpl mealsRepositoryImpl)
    {
        this.homeView = homeView ;
        this.mealsRepository = mealsRepositoryImpl ;
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
        //mealsRepository.getCategories(this);
        Observable<CategoryResponse> observable = mealsRepository.getCategories();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.i("TAG", "onSubscribe: getCategories");
                    }

                    @Override
                    public void onNext(@NonNull CategoryResponse categoryResponse) {
                        homeView.showCategories(categoryResponse.categories);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addFavMeal(Meal meal) {
        mealsRepository.insertMeal(meal);
    }

    @Override
    public void addPlanMeal(Meal meal) {

    }

}
