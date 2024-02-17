package com.example.foodplanner.Search.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.divider.MaterialDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements SearchView {
    GridLayoutManager gridLayoutManagerCountry ;

    RecyclerView rv_Country ;

    RecyclerView rv_Ingredient;
    LinearLayoutManager linearManager_Country ;
    LinearLayoutManager linearManager_Ingredient ;

    SearchPresenter searchPresenter ;
    CountryAdapter countryAdapter ;
    CountryAdapter countryAdapterAfter ;
    IngredientsAdapter ingredientsAdapter  , ingredientsAdapterAfter;
    ChipGroup chipGroup ;
    Chip chipCountry ;
    TextView tv_country;
    TextView tv_ingredients;
    MaterialDivider divider ;
    EditText etSearch ;
    List<Country> tempListCountry ;
    List<Ingredient> tempListIngredient;
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
        chipGroup = view.findViewById(R.id.chipGroup);
        chipCountry = view.findViewById(R.id.chipCountry);
        tv_country = view.findViewById(R.id.country_view);
        tv_ingredients = view.findViewById(R.id.ingredients_view);
        divider = view.findViewById(R.id.divider);
        etSearch = view.findViewById(R.id.etSearch);

        searchPresenter = new SearchPresenterImpl(MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance() ,
                        MealsLocalDataSourceImpl.getInstance(getContext())) , this) ;

        searchPresenter.getCountriesPresenter();
        searchPresenter.getIngredientsPresenter();

        linearManager_Country = new LinearLayoutManager(view.getContext());
        linearManager_Country.setOrientation(LinearLayoutManager.HORIZONTAL);

        countryAdapter = new CountryAdapter(view.getContext() , new ArrayList<>());
        rv_Country.setLayoutManager(linearManager_Country);
        rv_Country.setAdapter(countryAdapter);

        linearManager_Ingredient = new LinearLayoutManager(view.getContext());
        linearManager_Ingredient.setOrientation(LinearLayoutManager.HORIZONTAL);

        ingredientsAdapter = new IngredientsAdapter(view.getContext() , new ArrayList<>());
        rv_Ingredient.setLayoutManager(linearManager_Ingredient);
        rv_Ingredient.setAdapter(ingredientsAdapter);


        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, int checkedId) {
                Chip chip = view.findViewById(checkedId);
                hideAll();
                if (chip != null) {
                    if (chip.getId() == R.id.chipCountry) {
                        Toast.makeText(getContext(), "press on chipCountry", Toast.LENGTH_SHORT).show();
                        rv_Country.setVisibility(View.VISIBLE);
                        Observable.create(i ->{
                                    etSearch.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            i.onNext(etSearch.getText().toString());
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {

                                        }
                                    });
                                }).debounce(500, TimeUnit.MILLISECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(item -> {
                                    List<Country> afterSearch =  tempListCountry.stream()
                                            .filter(country -> country.getStrArea().toLowerCase().startsWith(item.toString().toLowerCase())
                                                    || country.getStrArea().toLowerCase().contains(item.toString().toLowerCase()))
                                                    .collect(Collectors.toList());


                                    Log.i("TAG", "onCheckedChanged:  data filtered ");
                                    countryAdapterAfter = new CountryAdapter(getContext() , afterSearch);
                                    gridLayoutManagerCountry = new GridLayoutManager(getContext() , 2);
                                    gridLayoutManagerCountry.setOrientation(RecyclerView.VERTICAL);
                                    rv_Country.setAdapter(countryAdapterAfter);
                                    rv_Country.setLayoutManager(gridLayoutManagerCountry);
                                });

                    } else if (chip.getId() == R.id.chipIngredients) {
                        rv_Ingredient.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "press on chipIngredients", Toast.LENGTH_SHORT).show();
                        Observable.create(i ->{
                                    etSearch.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            i.onNext(etSearch.getText().toString());
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {

                                        }
                                    });
                                }).debounce(500, TimeUnit.MILLISECONDS)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(item -> {
                                    List<Ingredient> afterSearch =  tempListIngredient.stream()
                                            .filter(ingredient -> ingredient.getStrIngredient().toLowerCase().startsWith(item.toString().toLowerCase())
                                                    || ingredient.getStrIngredient().toLowerCase().contains(item.toString().toLowerCase()))
                                            .collect(Collectors.toList());
                                    Log.i("TAG", "onCheckedChanged:  data filtered ");
                                    ingredientsAdapterAfter = new IngredientsAdapter(getContext() , afterSearch);
                                    gridLayoutManagerCountry = new GridLayoutManager(getContext() , 2);
                                    gridLayoutManagerCountry.setOrientation(RecyclerView.VERTICAL);
                                    rv_Ingredient.setAdapter(ingredientsAdapterAfter);
                                    rv_Ingredient.setLayoutManager(gridLayoutManagerCountry);
                                });

                    }

                }
            }
        });


    }

    @Override
    public void ShowCountries(List<Country> countryList) {
        tempListCountry = countryList ;
        countryAdapter.setMyList(countryList);
        countryAdapter.notifyDataSetChanged();


    }

    @Override
    public void ShowIngredients(List<Ingredient> ingredientList) {
        tempListIngredient = ingredientList ;
        ingredientsAdapter.setMyList(ingredientList);
        ingredientsAdapter.notifyDataSetChanged();

    }

    private void hideAll() {
        rv_Country.setVisibility(View.GONE);
        rv_Ingredient.setVisibility(View.GONE);
        tv_country.setVisibility(View.GONE);
        tv_ingredients.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
    }
}