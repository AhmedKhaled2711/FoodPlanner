package com.example.foodplanner.mealsCategories.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.home.View.HomeFragment;
import com.example.foodplanner.mealsCategories.Presenter.CategoriesPresenter;
import com.example.foodplanner.mealsCategories.Presenter.CategoriesPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class CategoriesFragment extends Fragment implements CategoriesView , OnAddClickListenerCategories {
    CategoriesPresenter categoriesPresenter ;
    String keyFromHome ;
    Toolbar toolbar;
    CategoriesFragmentAdapter categoriesFragmentAdapter ;
    RecyclerView recyclerView ;
    LinearLayoutManager linearManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);
         recyclerView = view.findViewById(R.id.rv_Categories);
         //to Receive data
         keyFromHome = CategoriesFragmentArgs.fromBundle(getArguments()).getCategoryName();
         toolbar = view.findViewById(R.id.toolbar);
         toolbar.setTitle(keyFromHome + "Categories");
         toolbar.setNavigationIcon(R.drawable.arrow_back);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        //Toast.makeText(getContext(), "Item After "+temp, Toast.LENGTH_SHORT).show();
        categoriesPresenter = new CategoriesPresenterImpl(this , MealsRepositoryImpl.getInstance
                (MealsRemoteDataSourceImpl.getInstance()));
        categoriesPresenter.getMealsFromCategoriesPresenter(keyFromHome);

        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesFragmentAdapter = new CategoriesFragmentAdapter(view.getContext()  , new ArrayList<>() , this);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(categoriesFragmentAdapter);


    }

    @Override
    public void showMealsOfCategory(List<Meal> meals) {
        categoriesFragmentAdapter.setMyList(meals);
        categoriesFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFavoriteClick(Meal meal) {
        categoriesPresenter.addFavMeal(meal);
        Toast.makeText(getContext(),"Meal added successfully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPlanClick(Meal meal) {

    }
}