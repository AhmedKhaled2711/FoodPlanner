package com.example.foodplanner.Search.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.R;

import java.util.List;

import retrofit2.http.GET;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private String urlImage = "https://www.themealdb.com/images/ingredients/";
    Context context;
    List<Ingredient> ingredientList ;

    public IngredientsAdapter(Context context , List <Ingredient> ingredients )
    {
        this.context = context ;
        this.ingredientList = ingredients ;
    }

    public void setMyList(List<Ingredient> myList){
        this.ingredientList = myList ;
    }


    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.ingredients_card_search,parent,false);
        IngredientsAdapter.ViewHolder myViewHolder = new IngredientsAdapter.ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.tv_ingredientName.setText(ingredient.getStrIngredient());
        Glide.with(context).load(urlImage+ingredient.getStrIngredient()+".png").into(holder.iv_ingredientImage);
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ingredientName ;
        ImageView iv_ingredientImage ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ingredientName = itemView.findViewById(R.id.tv_ingredient_card);
            iv_ingredientImage = itemView.findViewById(R.id.iv_ingredient_card);
        }
    }
}
