package com.example.foodplanner.home.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder> {

    Context context;
    List<Category> categoryList ;
    private OnItemClickListener mListener;

    private OnAddClickListener onAddClickListener ;

    public HomeFragmentAdapter (Context context , List <Category> categories )
    {
        this.categoryList = categories ;
        this.context = context ;
    }

    public HomeFragmentAdapter (Context context , List <Category> categories , OnAddClickListener onAddClickListener )
    {
        this.categoryList = categories ;
        this.context = context ;
        this.onAddClickListener = onAddClickListener ;
        categories = new ArrayList<>();
    }


    public void setMyList(List<Category> myList){
        this.categoryList = myList ;
    }
    @NonNull
    @Override
    public HomeFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.category_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentAdapter.MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.titleCategory.setText(category.getStrCategory());
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.iv_Category);

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(category.getStrCategory()); // Pass the clicked item data
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView titleCategory;
        private ImageView iv_Category ;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleCategory = itemView.findViewById(R.id.tv_category);
            iv_Category = itemView.findViewById(R.id.iv_category);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(String item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
