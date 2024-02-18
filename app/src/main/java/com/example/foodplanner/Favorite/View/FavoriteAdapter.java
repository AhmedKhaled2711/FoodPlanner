package com.example.foodplanner.Favorite.View;

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

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    Context context;
    List<Meal> mealList;
    OnRemoveClickListener onRemoveClickListener ;

    public FavoriteAdapter(Context context, List<Meal> mealList, OnRemoveClickListener onRemoveClickListener) {
        this.context = context;
        this.mealList = mealList;
        this.onRemoveClickListener = onRemoveClickListener;
        mealList = new ArrayList<>();
    }

    public void setMyList(List<Meal> myList){
        this.mealList=myList ;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavoriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.favorite_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.MyViewHolder holder, int position) {
        Meal meal =mealList.get(position);
        holder.tv_mealName.setText(meal.getStrMeal());
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.iv_meal);
        holder.btn_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRemoveClickListener.onFavClickRemove(meal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_meal ;
        Button btn_Remove , btn_Plan ;
        TextView tv_mealName ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            iv_meal = itemView.findViewById(R.id.iv_fav_meal);
            tv_mealName = itemView.findViewById(R.id.tv_fav_meal);
            btn_Remove = itemView.findViewById(R.id.btn_fav_delete);
            //btn_Plan = itemView.findViewById(R.id.btn_fav_plan);

        }
    }
}
