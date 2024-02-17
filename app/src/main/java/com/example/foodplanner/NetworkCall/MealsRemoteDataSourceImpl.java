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
    public void makeNetworkCall_getCategories(NetworkCallBack networkCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
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
       });
    }

    @Override
    public void makeNetworkCall_getMealFromCategories(NetworkCallBack networkCallBack , String categoryName) {
        Log.i("TAG", "response.isSuccessful: Last categoryName "+categoryName);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
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
        });

    }

    @Override
    public void makeNetworkCall_getCountries(NetworkCallBack networkCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
        mealService.getCountries().enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {

                if (response.isSuccessful()) {
                    CountryResponse countryResponse = response.body();
                    if (countryResponse != null && countryResponse.getCountries() != null) {
                        List<Country> countries = countryResponse.getCountries();
                        Log.i("TAG", "onResponse: CountryResponse countryResponse = response.body(); " +countries.size());
                    } else {
                        Log.i("TAG", "onResponse: is null CountryResponse  ");
                    }
                } else {
                    // Handle unsuccessful response (e.g., server error)
                }

            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: ");
                networkCallBack.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void makeNetworkCall_getIngredients(NetworkCallBack networkCallBack) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSON_URL_RETROFIT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        MealService mealService = retrofit.create(MealService.class);
        mealService.getIngredients().enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                Log.i("TAG", "response.isSuccessful: getIngredients ");
                networkCallBack.onSuccessIngredients(response.body().ingredients);
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                Log.i("TAG", "onFailure: ");
                networkCallBack.onFail(t.getMessage());
            }
        });
    }
}
