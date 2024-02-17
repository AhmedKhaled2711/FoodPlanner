package com.example.foodplanner.Search.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplanner.DataBase.MealsLocalDataSourceImpl;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Adapter.CountryAdapter;
import com.example.foodplanner.Search.Adapter.IngredientsAdapter;
import com.example.foodplanner.Search.Presenter.SearchPresenter;
import com.example.foodplanner.Search.Presenter.SearchPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView {
    EditText et_search ;

    RecyclerView rv_Country ;

    RecyclerView rv_Ingredient;
    LinearLayoutManager linearManager ;
    SearchPresenter searchPresenter ;
    CountryAdapter countryAdapter ;
    IngredientsAdapter ingredientsAdapter ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_Country = view.findViewById(R.id.country_rv_search);
        rv_Ingredient = view.findViewById(R.id.ingredients_rv_search);

        searchPresenter = new SearchPresenterImpl(MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance() ,
                        MealsLocalDataSourceImpl.getInstance(getContext())) , this) ;

        searchPresenter.getCountriesPresenter();
        //searchPresenter.getIngredientsPresenter();

        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        countryAdapter = new CountryAdapter(view.getContext() , new ArrayList<>());
        rv_Country.setLayoutManager(linearManager);
        rv_Country.setAdapter(countryAdapter);

        //ingredientsAdapter = new IngredientsAdapter(view.getContext() , new ArrayList<>());
        //rv_Ingredient.setLayoutManager(linearManager);
        //rv_Ingredient.setAdapter(ingredientsAdapter);



    }

    @Override
    public void ShowCountries(List<Country> countryList) {
        countryAdapter.setMyList(countryList);
        countryAdapter.notifyDataSetChanged();

    }

    @Override
    public void ShowIngredients(List<Ingredient> ingredientList) {
        ingredientsAdapter.setMyList(ingredientList);
        ingredientsAdapter.notifyDataSetChanged();

    }
}