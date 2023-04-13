// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.Domain.FoodDomain;
import com.example.foodorderingapp.Interface.ChangeNumberItemsListener;
import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<FoodDomain> foodDomains;

    // private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.foodDomains = foodDomains;
        // this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, int position) {
        holder.txtCartItemTitle.setText(foodDomains.get(position).getTitle());
        holder.txtCartItemOnePrice.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.txtCartItemsAllPrice.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart() * foodDomains.get(position).getFee())*100) / 100));
        holder.txtItemNumberInCart.setText(String.valueOf(foodDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        holder.ivCartItemPicture.setImageResource(drawableResourceId);

        holder.btnPlusCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Toteuta määrän muuttamis mahdollisuus!
            }
        });

        holder.btnMinusCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO Toteuta määrän muuttamis mahdollisuus!
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCartItemTitle, txtCartItemOnePrice;
        ImageView ivCartItemPicture, btnPlusCart, btnMinusCart;
        TextView txtCartItemsAllPrice, txtItemNumberInCart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCartItemTitle = itemView.findViewById(R.id.txtCartItemTitle);
            txtCartItemOnePrice = itemView.findViewById(R.id.txtCartItemOnePrice);
            ivCartItemPicture = itemView.findViewById(R.id.ivCartItemPicture);
            txtCartItemsAllPrice = itemView.findViewById(R.id.txtCartItemsAllPrice);
            txtItemNumberInCart = itemView.findViewById(R.id.txtItemNumberInCart);
            btnPlusCart = itemView.findViewById(R.id.btnPlusCart);
            btnMinusCart = itemView.findViewById(R.id.btnMinusCart);
        }
    }
}
