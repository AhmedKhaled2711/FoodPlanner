package com.example.foodplanner.MealsOfCountry.Presenter;

import com.example.foodplanner.MealsOfCountry.View.MealsCountryView;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.MealsRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MealsCountryPresenterImpl implements  MealsCountryPresenter  {
    MealsRepository mealsRepository ;
    MealsCountryView mealsCountryView ;

    public MealsCountryPresenterImpl(MealsRepository mealsRepository, MealsCountryView mealsCountryView) {
        this.mealsRepository = mealsRepository;
        this.mealsCountryView = mealsCountryView;
    }

    @Override
    public void getMealsFromCountryPresenter(String country) {
        Observable<MealResponse> observable = mealsRepository.getMealsFromCountry(country);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        mealsCountryView.showMealsOfCountry(mealResponse.meals);
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


}
