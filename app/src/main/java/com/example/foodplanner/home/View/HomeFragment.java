package com.example.foodplanner.home.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.home.Presenter.HomePresenter;
import com.example.foodplanner.home.Presenter.HomePresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements HomeView , HomeFragmentAdapter.OnItemClickListener {
    Meal randomMeal ;
    ImageView ivRandomMeal ;
    TextView tvRandomMeal ;
    Button btnFavorite , btnPlan ;
    HomePresenter homePresenter ;
    RecyclerView recyclerView;
    HomeFragmentAdapter homeFragmentAdapter ;
    LinearLayoutManager linearManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivRandomMeal = view.findViewById(R.id.iv_card_meal);
        tvRandomMeal = view.findViewById(R.id.tv_card_meal);

        recyclerView = view.findViewById(R.id.rv_home_categories);

        homePresenter = new HomePresenterImpl(this , MealsRepositoryImpl.getInstance
                (MealsRemoteDataSourceImpl.getInstance()));
        homePresenter.getRandomMealPresenter();
        homePresenter.getCategoriesPresenter();

        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        homeFragmentAdapter =new HomeFragmentAdapter(view.getContext() , new ArrayList<>());
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(homeFragmentAdapter);

        homeFragmentAdapter.setOnItemClickListener(this);


    }

    @Override
    public void showRandomMeal(List<Meal> meals) {
        tvRandomMeal.setText(meals.get(0).getStrMeal());
        Glide.with(this).load(meals.get(0).getStrMealThumb()).into(ivRandomMeal);

    }

    @Override
    public void showCategories(List<Category> categories) {
        homeFragmentAdapter.setMyList(categories);
        homeFragmentAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(String item) {
        Toast.makeText(getActivity(), "YA RAB "+item, Toast.LENGTH_SHORT).show();

        //to send Data
        com.example.foodplanner.home.View.HomeFragmentDirections.ActionHomeFragmentToCategoriesFragment action =
                HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(item);
        homePresenter.getMealsFromCategoriesPresenter(item);
        Navigation.findNavController(getView()).navigate(action);

    }
}