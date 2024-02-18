package com.example.foodplanner.mealsCategories.Presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.mealsCategories.View.CategoriesView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class CategoriesPresenterImpl implements CategoriesPresenter  {
    MealsRepository mealsRepository ;
    CategoriesView categoriesView ;


    public CategoriesPresenterImpl(CategoriesView categoriesView , MealsRepositoryImpl mealsRepositoryImpl){
        this.categoriesView = categoriesView ;
        this.mealsRepository = mealsRepositoryImpl ;
    }


    @Override
    public void getMealsFromCategoriesPresenter(String category) {
        //mealsRepository.getMealsFromCategories(category);
        Observable<MealResponse> observable = mealsRepository.getMealsFromCategories(category);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        categoriesView.showMealsOfCategory(mealResponse.meals);
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
