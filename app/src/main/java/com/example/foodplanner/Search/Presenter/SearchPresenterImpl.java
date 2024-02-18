package com.example.foodplanner.Search.Presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.CountryResponse;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.Search.View.SearchView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class SearchPresenterImpl implements  SearchPresenter   {

    MealsRepository mealsRepository ;
    SearchView searchView ;

    public SearchPresenterImpl(MealsRepository mealsRepository, SearchView searchView) {
        this.mealsRepository = mealsRepository;
        this.searchView = searchView;
    }


    @Override
    public void getIngredientsPresenter() {
        Observable<IngredientResponse> observable = mealsRepository.getIngredients();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IngredientResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull IngredientResponse ingredientResponse) {
                        searchView.ShowIngredients(ingredientResponse.meals);
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
    public void getCountriesPresenter() {
        Observable<CountryResponse> observable = mealsRepository.getCountries();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CountryResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CountryResponse countryResponse) {
                        searchView.ShowCountries(countryResponse.meals);
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
    public void getMealPresenter(String name) {
       // mealsRepository.getMeal(, name);
        Observable<MealResponse> observable = mealsRepository.getMeal(name);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        searchView.showMeal(mealResponse.meals);
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
