package com.example.foodplanner.MealsOfCountry.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.MealsOfCountry.Presenter.MealsCountryPresenter;
import com.example.foodplanner.MealsOfCountry.Presenter.MealsCountryPresenterImpl;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.mealsCategories.View.CategoriesFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MealsOfCountery extends Fragment  implements MealsCountryView , OnAddClickListenerMOC{

    String keyFromSearch ;
    Toolbar toolbar;
    MealsCountryPresenter mealsCountryPresenter ;
    MealsCountryAdapter mealsCountryAdapter ;
    RecyclerView recyclerView ;
    LinearLayoutManager linearManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meals_of_countery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_CountryMeals);
        keyFromSearch = MealsOfCounteryArgs.fromBundle(getArguments()).getCountryMeals();
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(keyFromSearch + "Categories");
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        mealsCountryPresenter = new MealsCountryPresenterImpl( MealsRepositoryImpl.getInstance
                (MealsRemoteDataSourceImpl.getInstance()) , this);

        mealsCountryPresenter.getMealsFromCountryPresenter(keyFromSearch);

        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);

        mealsCountryAdapter = new MealsCountryAdapter(view.getContext()  , new ArrayList<>() , this);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(mealsCountryAdapter);
    }

    @Override
    public void showMealsOfCountry(List<Meal> meals) {
        mealsCountryAdapter.setMyList(meals);
        mealsCountryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFavoriteClick(Meal meal) {
        mealsCountryPresenter.addFavMeal(meal);
        Toast.makeText(getContext(),"Meal added successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPlanClick(Meal meal) {

    }
}