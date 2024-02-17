package com.example.foodplanner.Search.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

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
    }

    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_ingredientName ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ingredientName = itemView.findViewById(R.id.tv_ingredient_name);
        }
    }
}
