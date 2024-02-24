package com.example.foodplanner.Plan.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.foodplanner.DataBase.MealsLocalDataSourceImpl;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealsRepositoryImpl;
import com.example.foodplanner.NetworkCall.MealsRemoteDataSourceImpl;
import com.example.foodplanner.Plan.Presenter.PlanPresenter;
import com.example.foodplanner.Plan.Presenter.PlanPresenterImpl;
import com.example.foodplanner.R;
import com.example.foodplanner.Model.SharedViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PlanFragment extends Fragment implements PlanView {
    TextView tv_Saturday , tv_Sunday, tv_Saturday_show , tv_Sunday_show ,tv_Monday_show,
            tv_Tuesday_show,tv_Wednesday_show ,tv_Thursday_show,tv_Friday_show  ;
    TextView tv_Monday , tv_Tuesday ,tv_Wednesday, tv_Thursday, tv_Friday ;
    private SharedViewModel sharedViewModel;
    private PlanPresenter planPresenter ;
    String Monday = "Monday";
    String Tuesday = "Tuesday";
    String Wednesday = "Wednesday";
    String Thursday = "Thursday";
    String Friday = "Friday";
    String Saturday = "Saturday";
    String Sunday = "Sunday";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_Saturday = view.findViewById(R.id.tv_Saturday);
        tv_Sunday  = view.findViewById(R.id.tv_Sunday);
        tv_Monday = view.findViewById(R.id.tv_Monday);
        tv_Tuesday = view.findViewById(R.id.tv_Tuesday);
        tv_Wednesday = view.findViewById(R.id.tv_Wednesday);
        tv_Thursday = view.findViewById(R.id.tv_Thursday);
        tv_Friday = view.findViewById(R.id.tv_Friday);


        tv_Saturday_show = view.findViewById(R.id.Saturday);
        tv_Sunday_show = view.findViewById(R.id.Sunday);
        tv_Monday_show = view.findViewById(R.id.Monday);
        tv_Tuesday_show = view.findViewById(R.id.Tuesday);
        tv_Wednesday_show = view.findViewById(R.id.Wednesday);
        tv_Thursday_show = view.findViewById(R.id.Thursday);
        tv_Friday_show = view.findViewById(R.id.Friday);

        planPresenter = new PlanPresenterImpl( MealsRepositoryImpl.getInstance(MealsRemoteDataSourceImpl.getInstance() ,
                MealsLocalDataSourceImpl.getInstance(getContext())) , this);

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        getPlanData();

        tv_Saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Saturday);
                Navigation.findNavController(v).navigate(action);
            }
        });
        sharedViewModel.getSelectedItemSaturday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                selectedItem.setMealDate(Saturday);

                tv_Saturday_show.setVisibility(View.VISIBLE);
                tv_Saturday_show.setText(selectedItem.getStrMeal());

                planPresenter.addToPlan(selectedItem);
            }
        });
        tv_Sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Sunday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemSunday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {

                selectedItem.setMealDate(Sunday);
                tv_Sunday_show.setVisibility(View.VISIBLE);
                tv_Sunday_show.setText(selectedItem.getStrMeal());
                planPresenter.addToPlan(selectedItem);
            }
        });

        tv_Monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Monday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemMonday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                selectedItem.setMealDate(Monday);
                tv_Monday_show.setVisibility(View.VISIBLE);
                tv_Monday_show.setText(selectedItem.getStrMeal());
                planPresenter.addToPlan(selectedItem);
            }
        });

        tv_Tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Tuesday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemTuesday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                selectedItem.setMealDate(Tuesday);
                tv_Tuesday_show.setVisibility(View.VISIBLE);
                tv_Tuesday_show.setText(selectedItem.getStrMeal());
                planPresenter.addToPlan(selectedItem);
            }
        });

        tv_Wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Wednesday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemWednesday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                selectedItem.setMealDate(Wednesday);
                tv_Wednesday_show.setVisibility(View.VISIBLE);
                tv_Wednesday_show.setText(selectedItem.getStrMeal());
                planPresenter.addToPlan(selectedItem);
            }
        });

        tv_Thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Thursday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemThursday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                selectedItem.setMealDate(Thursday);
                tv_Thursday_show.setVisibility(View.VISIBLE);
                tv_Thursday_show.setText(selectedItem.getStrMeal());
                planPresenter.addToPlan(selectedItem);
            }
        });

        tv_Friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to searchFragment when Saturday TextView is clicked
                PlanFragmentDirections.ActionPlanFragmentToSearchFragment action =
                        PlanFragmentDirections.actionPlanFragmentToSearchFragment(Friday);
                Navigation.findNavController(v).navigate(action);
            }
        });

        sharedViewModel.getSelectedItemFriday().observe(getViewLifecycleOwner(), selectedItem -> {
            if (selectedItem != null) {
                selectedItem.setMealDate(Friday);
                tv_Friday_show.setVisibility(View.VISIBLE);
                tv_Friday_show.setText(selectedItem.getStrMeal());
                planPresenter.addToPlan(selectedItem);
            }
        });
    }

    @Override
    public void getPlanData() {
        Flowable<List<Meal>> dataSaturday = planPresenter.getStoredPlan(Saturday);
        dataSaturday.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    if(meals.get(0) != null)
                    {
                        tv_Saturday_show.setVisibility(View.VISIBLE);
                        tv_Saturday_show.setText(meals.get(0).getStrMeal());
                    }
                    else {
                        tv_Saturday_show.setVisibility(View.VISIBLE);
                        tv_Saturday_show.setText("No Data Found");
                    }

                } ,error -> error.printStackTrace());

        Flowable<List<Meal>> dataSunday = planPresenter.getStoredPlan(Sunday);
        dataSunday.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    if(meals.get(0) != null)
                    {
                        tv_Sunday_show.setVisibility(View.VISIBLE);
                        tv_Sunday_show.setText(meals.get(0).getStrMeal());
                    }
                    else {
                        tv_Sunday_show.setVisibility(View.VISIBLE);
                        tv_Sunday_show.setText("No Data Found");
                    }
                } ,error -> error.printStackTrace());

        Flowable<List<Meal>> dataMonday = planPresenter.getStoredPlan(Monday);
        dataMonday.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {

                    if(meals.get(0) != null)
                    {
                        tv_Monday_show.setVisibility(View.VISIBLE);
                        tv_Monday_show.setText(meals.get(0).getStrMeal());
                    }
                    else {
                        tv_Monday_show.setVisibility(View.VISIBLE);
                        tv_Monday_show.setText("No Data Found");
                    }
                } ,error -> error.printStackTrace());

        Flowable<List<Meal>> dataTuesday = planPresenter.getStoredPlan(Tuesday);
        dataTuesday.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {
                    if(meals.get(0) != null)
                    {
                        tv_Tuesday_show.setVisibility(View.VISIBLE);
                        tv_Tuesday_show.setText(meals.get(0).getStrMeal());
                    }
                    else {
                        tv_Tuesday_show.setVisibility(View.VISIBLE);
                        tv_Tuesday_show.setText("No Data Found");
                    }
                } ,error -> error.printStackTrace());

        Flowable<List<Meal>> dataWednesday = planPresenter.getStoredPlan(Wednesday);
        dataWednesday.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {

                    if(meals.get(0) != null)
                    {
                        tv_Wednesday_show.setVisibility(View.VISIBLE);
                        tv_Wednesday_show.setText(meals.get(0).getStrMeal());
                    }
                    else {
                        tv_Wednesday_show.setVisibility(View.VISIBLE);
                        tv_Wednesday_show.setText("No Data Found");
                    }
                } ,error -> error.printStackTrace());

        Flowable<List<Meal>> dataThursday = planPresenter.getStoredPlan(Thursday);
        dataThursday.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {

                    if(meals.get(0) != null)
                    {
                        tv_Thursday_show.setVisibility(View.VISIBLE);
                        tv_Thursday_show.setText(meals.get(0).getStrMeal());
                    }
                    else {
                        tv_Thursday_show.setVisibility(View.VISIBLE);
                        tv_Thursday_show.setText("No Data Found");
                    }
                } ,error -> error.printStackTrace());

        Flowable<List<Meal>> dataFriday = planPresenter.getStoredPlan(Friday);
        dataFriday.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> {

                    if(meals.get(0) != null)
                    {
                        tv_Friday_show.setVisibility(View.VISIBLE);
                        tv_Friday_show.setText(meals.get(0).getStrMeal());
                    }
                    else {
                        tv_Friday_show.setVisibility(View.VISIBLE);
                        tv_Friday_show.setText("No Data Found");
                    }
                }
                ,error -> error.printStackTrace()
                );


    }

    @Override
    public void removePlanMeal(Meal meal) {

    }

    @Override
    public void showDataPlanMeal(Flowable<List<Meal>> meals) {

    }
}