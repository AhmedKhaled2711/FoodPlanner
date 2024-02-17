package com.example.foodplanner.Search.View;

import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;

import java.util.List;

public interface SearchView {

    public void ShowCountries(List<Country> countryList);

    public  void  ShowIngredients(List<Ingredient> ingredientList);


}
