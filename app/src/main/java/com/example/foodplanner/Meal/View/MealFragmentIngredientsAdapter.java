package com.example.foodplanner.Meal.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.home.View.HomeFragmentAdapter;

import java.util.List;

public class MealFragmentIngredientsAdapter extends RecyclerView.Adapter<MealFragmentIngredientsAdapter.ViewHolder> {
    private List<String> ingredientNames;
    private List<String> ingredientMeasures;
    private Context context;

    public MealFragmentIngredientsAdapter(Context context, List<String> ingredientNames, List<String> ingredientMeasures) {
        this.ingredientNames = ingredientNames;
        this.ingredientMeasures = ingredientMeasures;
        this.context = context;
    }
    @NonNull
    @Override
    public MealFragmentIngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.ingredient_card,parent,false);
        MealFragmentIngredientsAdapter.ViewHolder myViewHolder = new MealFragmentIngredientsAdapter.ViewHolder(view);
        return myViewHolder;
    }

    public void setList(List<String> ingredientNames, List<String> ingredientMeasure){
        this.ingredientNames = ingredientNames;
        this.ingredientMeasures = ingredientMeasure;
    }

    @Override
    public void onBindViewHolder(@NonNull MealFragmentIngredientsAdapter.ViewHolder holder, int position) {
        String currentIngredientName = ingredientNames.get(position);
        String currentIngredientMeasure = ingredientMeasures.get(position);
        Glide.with(context).load(currentIngredientName).into(holder.iv_ingredient);
        holder.tv_name.setText(currentIngredientName);
        holder.tv_measure.setText(currentIngredientMeasure);
    }

    @Override
    public int getItemCount() {
        return ingredientNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_measure;
        private ImageView iv_ingredient;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_ingredient_name);
            tv_measure = itemView.findViewById(R.id.tv_ingredient_measure);
            iv_ingredient = itemView.findViewById(R.id.iv_ingredient);
        }
    }
}
