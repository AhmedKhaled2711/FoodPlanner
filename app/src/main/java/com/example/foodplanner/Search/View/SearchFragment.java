package com.example.foodplanner.Search.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DataBase.MealsLocalDataSourceImpl;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Adapter.CountryAdapter;
import com.example.foodplanner.Search.Adapter.IngredientsAdapter;
import com.example.foodplanner.Search.Presenter.SearchPresenter;
import com.example.foodplanner.Search.Presenter.SearchPresenterImpl;
import com.example.foodplanner.Model.SharedViewModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.divider.MaterialDivider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;

public class SearchFragment extends Fragment implements SearchView , CountryAdapter.OnItemClickListenerCountry ,OnAddClickListenerSearch {
    GridLayoutManager gridLayoutManagerCountry ;

    RecyclerView rv_Country ;

    RecyclerView rv_Ingredient;
    LinearLayoutManager linearManager_Country ;
    LinearLayoutManager linearManager_Ingredient ;
    private SharedViewModel sharedViewModel;
    SearchPresenter searchPresenter ;
    CountryAdapter countryAdapter ;
    CountryAdapter countryAdapterAfter ;
    IngredientsAdapter ingredientsAdapter  , ingredientsAdapterAfter;
    ChipGroup chipGroup ;
    CardView cardView ;
    Chip chipCountry , chipMeals ;
    Button btnFavorite , btnPlan  ;
    TextView tv_country;
    ImageView iv_card_meal ;
    TextView tv_ingredients , meal_view , tv_card_meal;
    MaterialDivider divider , divider_second ;
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
        cardView = view.findViewById(R.id.cardView_show_meal_Search);
        divider_second = view.findViewById(R.id.divider_second);
        cardView = view.findViewById(R.id.cardView_show_meal_Search);
        meal_view = view.findViewById(R.id.meal_view);
        iv_card_meal =  view.findViewById(R.id.iv_card_meal);
        tv_card_meal =  view.findViewById(R.id.tv_card_meal);
        btnFavorite = view.findViewById(R.id.btn_Card_save);
        btnPlan = view.findViewById(R.id.btn_Card_plan);



        searchPresenter = new SearchPresenterImpl(MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance() ,
                        MealsLocalDataSourceImpl.getInstance(getContext())) , this) ;

        searchPresenter.getCountriesPresenter();
        searchPresenter.getIngredientsPresenter();
        searchPresenter.getMealPresenter(etSearch.getText().toString());


        linearManager_Country = new LinearLayoutManager(view.getContext());
        linearManager_Country.setOrientation(LinearLayoutManager.HORIZONTAL);

        countryAdapter = new CountryAdapter(view.getContext() , new ArrayList<>());
        countryAdapter.setOnItemClickListener(this);
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
                        Toast.makeText(getContext(), "press on Country", Toast.LENGTH_SHORT).show();
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
                                    //countryAdapterAfter.setOnItemClickListener(this);
                                    rv_Country.setAdapter(countryAdapterAfter);
                                    rv_Country.setLayoutManager(gridLayoutManagerCountry);
                                });

                    } else if (chip.getId() == R.id.chipIngredients) {
                        rv_Ingredient.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "press on Ingredients", Toast.LENGTH_SHORT).show();
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
                                                    )
                                            //|| ingredient.getStrIngredient().toLowerCase().contains(item.toString().toLowerCase())
                                            .collect(Collectors.toList());
                                    Log.i("TAG", "onCheckedChanged:  data filtered ");
                                    ingredientsAdapterAfter = new IngredientsAdapter(getContext() , afterSearch);
                                    gridLayoutManagerCountry = new GridLayoutManager(getContext() , 2);
                                    gridLayoutManagerCountry.setOrientation(RecyclerView.VERTICAL);
                                    rv_Ingredient.setAdapter(ingredientsAdapterAfter);
                                    rv_Ingredient.setLayoutManager(gridLayoutManagerCountry);
                                });

                    } else if (chip.getId() == R.id.chipMeals) {
                        Toast.makeText(getContext(), "press on Meals", Toast.LENGTH_SHORT).show();
                        cardView.setVisibility(View.VISIBLE);
                        etSearch.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                                String searchText = s.toString().toLowerCase();
                                searchPresenter.getMealPresenter(searchText);

                            }

                            @Override
                            public void afterTextChanged(Editable s) {

                            }
                        });
                    }

                }
            }
        });

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

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

    @Override
    public void showMeal(List<Meal> meals) {
        Meal serachMeal = meals.get(0);
        if(serachMeal !=null)
        {
            tv_card_meal.setText(serachMeal.getStrMeal());
            Glide.with(this).load(serachMeal.getStrMealThumb()).into(iv_card_meal);
        }
        else
        {
            Toast.makeText(getContext(), "Meal Not Found", Toast.LENGTH_SHORT).show();
        }


        tv_card_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFragmentToMealFragment actionSearchFragmentToMealFragment
                        = SearchFragmentDirections.actionSearchFragmentToMealFragment(serachMeal);
                Navigation.findNavController(v).navigate(actionSearchFragmentToMealFragment);
            }
        });

        iv_card_meal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragmentDirections.ActionSearchFragmentToMealFragment actionSearchFragmentToMealFragment
                        = SearchFragmentDirections.actionSearchFragmentToMealFragment(serachMeal);
                Navigation.findNavController(v).navigate(actionSearchFragmentToMealFragment);

            }
        });
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteClick(serachMeal);
                Toast.makeText(getContext(), serachMeal.getStrMeal()+" added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        btnPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyFromPlan = SearchFragmentArgs.fromBundle(getArguments()).getNameOfDay();
                switch (keyFromPlan) {
                    case "Monday":
                        sharedViewModel.setSelectedItemMonday(serachMeal);
                        break;
                    case "Tuesday":
                        sharedViewModel.setSelectedItemTuesday(serachMeal);
                        break;
                    case "Wednesday":
                        sharedViewModel.setSelectedItemWednesday(serachMeal);
                        break;
                    case "Thursday":
                        sharedViewModel.setSelectedItemThursday(serachMeal);
                        break;
                    case "Friday":
                        sharedViewModel.setSelectedItemFriday(serachMeal);
                        break;
                    case "Saturday":
                        sharedViewModel.setSelectedItemSaturday(serachMeal);
                        break;
                    case "Sunday":
                        sharedViewModel.setSelectedItemSunday(serachMeal);
                        break;
                    default:
                        // Handle invalid day of the week (optional)
                        break;
                }
                Navigation.findNavController(getView()).navigateUp();
            }
        });


    }

    private void hideAll() {
        rv_Country.setVisibility(View.GONE);
        rv_Ingredient.setVisibility(View.GONE);
        tv_country.setVisibility(View.GONE);
        tv_ingredients.setVisibility(View.GONE);
        divider.setVisibility(View.GONE);
        divider_second.setVisibility(View.GONE);
        cardView.setVisibility(View.GONE);
        meal_view.setVisibility(View.GONE);
    }

    @Override
    public void onItemClick(String item) {
       // Toast.makeText(getActivity(), "YA RAB "+item, Toast.LENGTH_SHORT).show();

        SearchFragmentDirections.ActionSearchFragmentToMealsOfCountery action =
                SearchFragmentDirections.actionSearchFragmentToMealsOfCountery(item);
        Navigation.findNavController(getView()).navigate(action);

    }

    @Override
    public void onFavoriteClick(Meal meal) {
        searchPresenter.addFavMeal(meal);
    }

    @Override
    public void onPlanClick(Meal meal) {

    }
}