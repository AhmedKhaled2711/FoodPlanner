package com.example.foodplanner.Meal.View;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MealFragment extends Fragment {
    private Meal receivedMeal ;
    Toolbar toolbar;
    ImageView ivMeal ;
    TextView tvNameMeal ;
    RecyclerView rvIngredients ;
    TextView tvInstructions ;
    LinearLayoutManager linearManager;

    YouTubePlayerView youTubePlayer ;
    MealFragmentIngredientsAdapter mealFragmentIngredientsAdapter ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivMeal = view.findViewById(R.id.img_meal);
        rvIngredients = view.findViewById(R.id.rv_ingredients);
        tvNameMeal = view.findViewById(R.id.tv_mealName);
        tvInstructions = view.findViewById(R.id.tv_instructions);
        youTubePlayer = view.findViewById(R.id.youtube_player);

        //to received data from home fragment
        receivedMeal =MealFragmentArgs.fromBundle(getArguments()).getMeal();
        tvNameMeal.setText(receivedMeal.getStrMeal());
        Glide.with(view).load(receivedMeal.getStrMealThumb()).into(ivMeal);

        String []instructions = receivedMeal.getStrInstructions().split("\\.");
        for(int i = 1 ; i<= instructions.length ; i++){
            tvInstructions.append("\n"+instructions[i-1]+".\n");
        }
        toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(receivedMeal.getStrMeal());
        toolbar.setNavigationIcon(R.drawable.arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mealFragmentIngredientsAdapter = new MealFragmentIngredientsAdapter(view.getContext() ,getIngredients() ,getMeasures());
        rvIngredients.setLayoutManager(linearManager);
        rvIngredients.setAdapter(mealFragmentIngredientsAdapter);

        //play Video
        youTubePlayer.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {

            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String url = receivedMeal.getStrYoutube() ;
                if (url != null && url.split("\\?v=").length > 1)
                    url =  url.split("\\?v=")[1];
                youTubePlayer.cueVideo(url , 0);
            }
        });
    }

    private List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add(receivedMeal.getStrIngredient1());
        ingredients.add(receivedMeal.getStrIngredient2());
        ingredients.add(receivedMeal.getStrIngredient3());
        ingredients.add(receivedMeal.getStrIngredient4());
        ingredients.add(receivedMeal.getStrIngredient5());
        ingredients.add(receivedMeal.getStrIngredient6());
        ingredients.add(receivedMeal.getStrIngredient7());
        ingredients.add(receivedMeal.getStrIngredient8());
        ingredients.add(receivedMeal.getStrIngredient9());
        ingredients.add(receivedMeal.getStrIngredient10());
        ingredients.add(receivedMeal.getStrIngredient11());
        ingredients.add(receivedMeal.getStrIngredient12());
        ingredients.add(receivedMeal.getStrIngredient13());
        ingredients.add(receivedMeal.getStrIngredient14());
        ingredients.add(receivedMeal.getStrIngredient15());
        ingredients.add(receivedMeal.getStrIngredient16());
        ingredients.add(receivedMeal.getStrIngredient17());
        ingredients.add(receivedMeal.getStrIngredient18());
        ingredients.add(receivedMeal.getStrIngredient19());
        ingredients.add(receivedMeal.getStrIngredient20());
        ingredients = ingredients.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toList());
        System.out.println("Ingredients: " + ingredients);
        return ingredients;
    }

    private List<String> getMeasures() {
        List<String> measures = new ArrayList<>();
        measures.add(receivedMeal.getStrMeasure1());
        measures.add(receivedMeal.getStrMeasure2());
        measures.add(receivedMeal.getStrMeasure3());
        measures.add(receivedMeal.getStrMeasure4());
        measures.add(receivedMeal.getStrMeasure5());
        measures.add(receivedMeal.getStrMeasure6());
        measures.add(receivedMeal.getStrMeasure7());
        measures.add(receivedMeal.getStrMeasure8());
        measures.add(receivedMeal.getStrMeasure9());
        measures.add(receivedMeal.getStrMeasure10());
        measures.add(receivedMeal.getStrMeasure11());
        measures.add(receivedMeal.getStrMeasure12());
        measures.add(receivedMeal.getStrMeasure13());
        measures.add(receivedMeal.getStrMeasure14());
        measures.add(receivedMeal.getStrMeasure15());
        measures.add(receivedMeal.getStrMeasure16());
        measures.add(receivedMeal.getStrMeasure17());
        measures.add(receivedMeal.getStrMeasure18());
        measures.add(receivedMeal.getStrMeasure19());
        measures.add(receivedMeal.getStrMeasure20());
        measures = measures.stream()
                .filter(s -> s != null && !s.isEmpty())
                .collect(Collectors.toList());
        System.out.println("Measures: " + measures);
        return measures;
    }
}