package com.example.foodplanner.MealsOfCountry.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;


import java.util.List;

public class MealsCountryAdapter extends RecyclerView.Adapter<MealsCountryAdapter.MyViewHolder>{

    Context context;
    List<Meal> mealList ;
    OnAddClickListenerMOC onAddClickListenerMOC ;


    public MealsCountryAdapter(Context context , List <Meal> meals , OnAddClickListenerMOC onAddClickListenerMOC ){
        this.context = context ;
        this.mealList = meals ;
        this.onAddClickListenerMOC = onAddClickListenerMOC ;

    }

    public void setMyList(List<Meal> myList){
        this.mealList = myList ;
    }
    @NonNull
    @Override
    public MealsCountryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.meal_card,parent,false);
        MealsCountryAdapter.MyViewHolder myViewHolder = new MealsCountryAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealsCountryAdapter.MyViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.titleMeal.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.iv_Meal);

        holder.btn_Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClickListenerMOC.onFavoriteClick(meal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleMeal;
        private ImageView iv_Meal ;

        Button btn_Fav , btn_Plan ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleMeal = itemView.findViewById(R.id.tv_card_meal);
            iv_Meal = itemView.findViewById(R.id.iv_card_meal);
            btn_Fav = itemView.findViewById(R.id.btn_Card_save);
            btn_Plan = itemView.findViewById(R.id.btn_Card_plan);
        }
    }
}
