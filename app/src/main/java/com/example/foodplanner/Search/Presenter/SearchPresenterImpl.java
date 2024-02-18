package com.example.foodplanner.Search.Presenter;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.NetworkCall.NetworkCallBack;
import com.example.foodplanner.Search.View.SearchView;

import java.util.List;

public class SearchPresenterImpl implements  SearchPresenter , NetworkCallBack  {

    MealsRepository mealsRepository ;
    SearchView searchView ;

    public SearchPresenterImpl(MealsRepository mealsRepository, SearchView searchView) {
        this.mealsRepository = mealsRepository;
        this.searchView = searchView;
    }


    @Override
    public void getIngredientsPresenter() {
        mealsRepository.getIngredients(this);
    }

    @Override
    public void getCountriesPresenter() {
        mealsRepository.getCountries(this);
    }

    @Override
    public void getMealPresenter(String name) {
        mealsRepository.getMeal(this, name);
    }

    @Override
    public void addFavMeal(Meal meal) {
        mealsRepository.insertMeal(meal);
    }

    @Override
    public void addPlanMeal(Meal meal) {

    }

    @Override
    public void onSuccess(List<Meal> meals) {

    }

    @Override
    public void onSuccess_getCategories(List<Category> categories) {

    }

    @Override
    public void onSuccessMealFromCategories(List<Meal> meals) {

    }

    @Override
    public void onSuccessCountries(List<Country> countries) {
        searchView.ShowCountries(countries);
    }

    @Override
    public void onSuccessIngredients(List<Ingredient> ingredients) {
        searchView.ShowIngredients(ingredients);
    }

    @Override
    public void onSuccessMealsFromCountry(List<Meal> meals) {

    }

    @Override
    public void onSuccessSearchMeal(List<Meal> meals) {
        searchView.showMeal(meals);
    }

    @Override
    public void onFail(String err) {

    }
}
