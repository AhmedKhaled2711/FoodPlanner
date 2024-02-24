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
import com.example.foodplanner.DataBase.MealsLocalDataSourceImpl;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.home.Presenter.HomePresenter;
import com.example.foodplanner.home.Presenter.HomePresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnAddClickListener, HomeView , HomeFragmentAdapter.OnItemClickListener {

    ImageView ivRandomMeal ;
    TextView tvRandomMeal ;
    Button btnFavorite , btnPlan ;
    HomePresenter homePresenter ;
    RecyclerView recyclerView;
    HomeFragmentAdapter homeFragmentAdapter ;
    LinearLayoutManager linearManager;
    OnAddClickListener onAddClickListener ;
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
        btnFavorite = view.findViewById(R.id.btn_Card_save);
        recyclerView = view.findViewById(R.id.rv_home_categories);

        homePresenter = new HomePresenterImpl(this ,
                MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance() ,
                                        MealsLocalDataSourceImpl.getInstance(getContext())));
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
        Meal randomMeal = meals.get(0);
        tvRandomMeal.setText(randomMeal.getStrMeal());
        Glide.with(this).load(randomMeal.getStrMealThumb()).into(ivRandomMeal);

        btnFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onFavoriteClick(randomMeal);
                Toast.makeText(getContext(), randomMeal.getStrMeal()+" added successfully", Toast.LENGTH_SHORT).show();
            }
        });
        tvRandomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeFragmentDirections.ActionHomeFragmentToMealFragment action =
                        HomeFragmentDirections.actionHomeFragmentToMealFragment(randomMeal);
                Navigation.findNavController(v).navigate(action);
            }
        });

        ivRandomMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragmentDirections.ActionHomeFragmentToMealFragment action =
                        HomeFragmentDirections.actionHomeFragmentToMealFragment(randomMeal);
                Navigation.findNavController(v).navigate(action);
            }
        });

    }

    @Override
    public void showCategories(List<Category> categories) {
        homeFragmentAdapter.setMyList(categories);
        homeFragmentAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(String item) {
        //Toast.makeText(getActivity(), "YA RAB "+item, Toast.LENGTH_SHORT).show();

        //to send Data
        com.example.foodplanner.home.View.HomeFragmentDirections.ActionHomeFragmentToCategoriesFragment action =
                HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(item);
        Navigation.findNavController(getView()).navigate(action);

    }


    @Override
    public void onFavoriteClick(Meal meal) {
        homePresenter.addFavMeal(meal);
    }

    @Override
    public void onPlanClick(Meal meal) {

    }


}