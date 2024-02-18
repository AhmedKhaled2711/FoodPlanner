package com.example.foodplanner.Search.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.Model.Country;
import com.example.foodplanner.R;
import com.example.foodplanner.home.View.HomeFragmentAdapter;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    private OnItemClickListenerCountry mListener;
    Context context;
    List<Country> countryList ;

    public CountryAdapter(Context context , List <Country> countries )
    {
        this.context = context ;
        this.countryList = countries ;
    }

    public void setMyList(List<Country> myList){
        this.countryList = myList ;
    }


    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.country_card,parent,false);
        CountryAdapter.ViewHolder myViewHolder = new CountryAdapter.ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.tv_countryName.setText(country.getStrArea());

        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onItemClick(country.getStrArea()); // Pass the clicked item data
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_countryName ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_countryName = itemView.findViewById(R.id.tv_country_card);
        }
    }

    public interface OnItemClickListenerCountry {
        void onItemClick(String item);
    }

    public void setOnItemClickListener(OnItemClickListenerCountry listener) {
        mListener = listener;
    }
}
