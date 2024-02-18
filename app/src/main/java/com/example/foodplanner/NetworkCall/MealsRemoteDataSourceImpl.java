package com.example.foodplanner.NetworkCall;

import android.util.Log;

import com.example.foodplanner.Model.CategoryResponse;
import com.example.foodplanner.Model.Country;
import com.example.foodplanner.Model.CountryResponse;
import com.example.foodplanner.Model.IngredientResponse;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealResponse;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImpl implements MealsRemoteDataSource{
    private static final String JSON_URL_RETROFIT =
            "https://www.themealdb.com/api/json/v1/1/";
    private  static MealsRemoteDataSourceImpl connectToMeal = null ;
    public MealsRemoteDataSourceImpl(){

    }

    public static MealsRemoteDataSourceImpl getInstance(){
        if(connectToMeal==null){
            connectToMeal = new MealsRemoteDataSourceImpl();
        }
        return connectToMeal;
    }

    @Override
    public Observable<MealResponse> makeNetworkCall() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
        /*mealService.getRandomMeal().enqueue(new Callback<MealResponse>() {

                @Override
                public void onResponse(Call<MealResponse> call,
                                       Response<MealResponse> response) {
                    Log.i("TAG", "onResponse: ");
                    if (response.isSuccessful()) {
                        Log.i("TAG", "response.isSuccessful: ");
                        networkCallBack.onSuccess(response.body().meals);
                    }

                }

                @Override
                public void onFailure(Call<MealResponse> call, Throwable t) {
                    Log.i("TAG", "onFailure: ");
                    networkCallBack.onFail(t.getMessage());
                }
            });*/
        Observable <MealResponse> observable = mealService.getRandomMeal() ;

        return  observable.subscribeOn(Schedulers.io());

    }

    @Override
    public Observable<CategoryResponse> makeNetworkCall_getCategories() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
        Observable<CategoryResponse> observable = mealService.getCategories() ;
        return observable.subscribeOn(Schedulers.io());

        /*
       mealService.getCategories().enqueue(new Callback<CategoryResponse>() {
           @Override
           public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
               Log.i("TAG", "onResponse: ");
               if (response.isSuccessful()) {
                   Log.i("TAG", "response.isSuccessful: ");
                   networkCallBack.onSuccess_getCategories(response.body().categories);
               }
           }
           @Override
           public void onFailure(Call<CategoryResponse> call, Throwable t) {
               Log.i("TAG", "onFailure: ");
               networkCallBack.onFail(t.getMessage());
           }
       });*/
    }

    @Override
    public Observable<MealResponse> makeNetworkCall_getMealFromCategories(String categoryName) {
        Log.i("TAG", "response.isSuccessful: Last categoryName "+categoryName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
        Observable<MealResponse> observable = mealService.getMealByCategory(categoryName);
        return observable.subscribeOn(Schedulers.io());

        /*
        mealService.getMealByCategory(categoryName).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                Log.i("TAG", "response.isSuccessful: ");
                networkCallBack.onSuccessMealFromCategories(response.body().meals);

            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: ");
                networkCallBack.onFail(t.getMessage());
            }
        });*/

    }

    @Override
    public Observable<CountryResponse> makeNetworkCall_getCountries() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);

        Observable<CountryResponse> observable = mealService.getCountries();
        return observable.subscribeOn(Schedulers.io());

        /*
        mealService.getCountries().enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {

                if (response.isSuccessful()) {
                    networkCallBack.onSuccessCountries(response.body().meals);
                } else {
                    // Handle unsuccessful response (e.g., server error)
                }

            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: ");
                networkCallBack.onFail(t.getMessage());
            }
        });*/
    }

    @Override
    public Observable<IngredientResponse> makeNetworkCall_getIngredients() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
        Observable<IngredientResponse> observable = mealService.getIngredients();
        return observable.subscribeOn(Schedulers.io());

        /*
        mealService.getIngredients().enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                Log.i("TAG", "response.isSuccessful: getIngredients ");
                networkCallBack.onSuccessIngredients(response.body().meals);
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: ");
                networkCallBack.onFail(t.getMessage());
            }
        });*/
    }

    @Override
    public Observable<MealResponse>  makeNetworkCall_getMealsOfCountry(  String CountryName) {
        Log.i("TAG", "response.isSuccessful: Last categoryName "+CountryName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
        Observable<MealResponse>  observable = mealService.getMealsOfCountry(CountryName);
        return observable.subscribeOn(Schedulers.io());

        /*
        mealService.getMealsOfCountry(CountryName).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "response.isSuccessful: networkCallBack.onSuccessMealsFromCountry ");
                    networkCallBack.onSuccessMealsFromCountry(response.body().meals);
                }
            }
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: ");
                networkCallBack.onFail(t.getMessage());
            }
        });*/
    }

    @Override
    public Observable<MealResponse> makeNetworkCall_SearchByName(String mealName) {
        Log.i("TAG", "response.isSuccessful: search about "+mealName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
        Observable<MealResponse> observable = mealService.searchByName(mealName);
        return observable.subscribeOn(Schedulers.io());

        /*
        mealService.searchByName(mealName).enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful()) {
                    Log.i("TAG", "response.isSuccessful: return Meal");
                    networkCallBack.onSuccessSearchMeal(response.body().meals);
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: ");
                networkCallBack.onFail(t.getMessage());
            }
        });*/
    }
}
