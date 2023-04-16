package com.example.foodorderingapp.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Activity.ShowDetailActivity;
import com.example.foodorderingapp.Domain.FoodDomain;
import com.example.foodorderingapp.R;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    private List<FoodDomain> favoriteProducts;

    public FavoritesAdapter(List<FoodDomain> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }

    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_favorite, parent, false);
        return new FavoritesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        FoodDomain food = favoriteProducts.get(position);
        holder.txtFavoriteTitle.setText(food.getTitle());
        holder.txtFavoriteFee.setText(String.format("%.2f", food.getFee()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(food.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        holder.ivFavoritePic.setImageResource(drawableResourceId);

        holder.txtAddToCartButton.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("object", food);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (favoriteProducts == null) {
            return 0;
        }
        return favoriteProducts.size();
    }

    static class FavoritesViewHolder extends RecyclerView.ViewHolder {
        TextView txtFavoriteTitle;
        TextView txtFavoriteFee;
        ImageView ivFavoritePic;
        TextView txtAddToCartButton;

        FavoritesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtFavoriteTitle = itemView.findViewById(R.id.txtFavoriteTitle);
            txtFavoriteFee = itemView.findViewById(R.id.txtFavoriteFee);
            ivFavoritePic = itemView.findViewById(R.id.ivFavoritePic);
            txtAddToCartButton = itemView.findViewById(R.id.txtAddToCartButton);
        }
    }
}