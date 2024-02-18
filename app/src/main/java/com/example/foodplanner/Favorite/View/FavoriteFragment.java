package com.example.foodplanner.Favorite.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.DataBase.MealsLocalDataSourceImpl;
import com.example.foodplanner.Favorite.Presenter.FavoritePresenter;
import com.example.foodplanner.Favorite.Presenter.FavoritePresenterImpl;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepository;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavoriteFragment extends Fragment implements FavoriteView , OnRemoveClickListener{
    FavoritePresenter favoritePresenter ;
    MealsRepository mealsRepository ;
    FavoriteAdapter favoriteAdapter ;
    RecyclerView recyclerView;
    LinearLayoutManager linearManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_fav);

        linearManager = new LinearLayoutManager(getContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);

        mealsRepository = MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance()
        , MealsLocalDataSourceImpl.getInstance(getContext()));
        favoritePresenter = new FavoritePresenterImpl(mealsRepository);
        favoriteAdapter = new FavoriteAdapter(getContext() , new ArrayList<>() , this);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(favoriteAdapter);

        showData(favoritePresenter.getStoredDataDB());



    }

    @Override
    public void showData(Flowable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productList -> {
                            favoriteAdapter.setMyList(productList);
                        }
                );
    }

    @Override
    public void onFavClickRemove(Meal meal) {
        favoritePresenter.removeFavMeal(meal);
    }
}