package com.example.foodorderingapp.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Activity.ShowDetailActivity;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.R;

import java.util.List;

public class DetailCategoryAdapter extends RecyclerView.Adapter<DetailCategoryAdapter.ViewHolder> {
    private List<FoodDomain> foodList;

    public DetailCategoryAdapter(List<FoodDomain> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_detail_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDomain food = foodList.get(position);

        int imageResource = holder.ivCategoryFoodPic.getContext().getResources().getIdentifier(food.getPic(), "drawable", holder.ivCategoryFoodPic.getContext().getPackageName());
        holder.ivCategoryFoodPic.setImageResource(imageResource);
        holder.txtCategoryFoodTitle.setText(food.getTitle());
        holder.txtCategoryFoodFee.setText(String.format("%.2f", food.getFee()));

        holder.txtAddToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", food);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivCategoryFoodPic;
        private TextView txtCategoryFoodTitle;
        private TextView txtCategoryFoodFee;
        private TextView txtAddToCartButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCategoryFoodPic = itemView.findViewById(R.id.ivCategoryFoodPic);
            txtCategoryFoodTitle = itemView.findViewById(R.id.txtCategoryFoodTitle);
            txtCategoryFoodFee = itemView.findViewById(R.id.txtCategoryFoodFee);
            txtAddToCartButton = itemView.findViewById(R.id.txtAddToCartButton);
        }
    }
}