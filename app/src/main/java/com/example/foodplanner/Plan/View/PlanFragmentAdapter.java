package com.example.foodplanner.Plan.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.home.View.HomeFragmentAdapter;
import com.example.foodplanner.home.View.OnAddClickListener;

import java.util.ArrayList;
import java.util.List;

public class PlanFragmentAdapter extends RecyclerView.Adapter<PlanFragmentAdapter.MyViewHolder>{

    Context context;
    List<Meal> mealList ;
    public PlanFragmentAdapter (Context context , List <Meal> categories )
    {
        this.mealList = categories ;
        this.context = context ;
    }


    public void setMyList(List<Meal> myList){
        this.mealList = myList ;
    }

    public void addItem(Meal item) {
        if (mealList == null) {
            mealList = new ArrayList<>();
        }
        mealList.add(item);
        notifyDataSetChanged(); // Notify adapter of dataset change
    }
    @NonNull
    @Override
    public PlanFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.plan_card,parent,false);
        PlanFragmentAdapter.MyViewHolder myViewHolder = new PlanFragmentAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlanFragmentAdapter.MyViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.titleMeal.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.iv_Meal);

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleMeal;
        private ImageView iv_Meal ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleMeal = itemView.findViewById(R.id.tv_plan_meal);
            iv_Meal = itemView.findViewById(R.id.iv_plan_meal);
        }
    }


}
