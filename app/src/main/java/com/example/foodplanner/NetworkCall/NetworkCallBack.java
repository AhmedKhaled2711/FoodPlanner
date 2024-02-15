package com.example.foodplanner.NetworkCall;

import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;

import java.util.List;

public interface NetworkCallBack {

    public void onSuccess(List<Meal> meals);

    public void onSuccess_getCategories(List<Category> categories);

    public void onSuccessMealFromCategories(List<Meal> meals);

    public void onFail(String err);
}
