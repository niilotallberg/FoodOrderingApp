// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodorderingapp.General.FoodDomain;
import com.example.foodorderingapp.Manager.CartManager;
import com.example.foodorderingapp.Interface.ChangeNumberItemsListener;
import com.example.foodorderingapp.R;
import java.util.Map;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private Map<FoodDomain, Integer> cartItems;
    private CartManager cartManager;
    private ChangeNumberItemsListener changeNumberItemsListener;
    private Context context;

    public CartListAdapter(CartManager cartManager, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.cartItems = cartManager.getCartItems();
        this.context = context;
        this.changeNumberItemsListener = changeNumberItemsListener;
        this.cartManager = cartManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(view);
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodDomain foodDomain = (FoodDomain) cartItems.keySet().toArray()[position];
        holder.txtCartItemTitle.setText(foodDomain.getTitle());
        holder.txtCartItemOnePrice.setText(String.valueOf(foodDomain.getFee()));
        holder.txtItemNumberInCart.setText(String.valueOf(cartItems.get(foodDomain)));
        holder.txtCartItemsAllPrice.setText(String.format("%.2f", Math.round((cartItems.get(foodDomain) * foodDomain.getFee()) * 100) / 100.0));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(foodDomain.getPic(), "drawable", holder.itemView.getContext().getPackageName());
        holder.ivCartItemPicture.setImageResource(drawableResourceId);

        holder.btnPlusCart.setOnClickListener(new View.OnClickListener() { // Handles the case where user wants to increase the quantity of a certain item in cart
            @Override
            public void onClick(View view) {
                Integer currentQuantity = cartItems.get(foodDomain);
                if (currentQuantity == null) {
                    currentQuantity = 0;
                }
                currentQuantity += 1;
                cartManager.addCartItem(context, foodDomain, 1);
                changeNumberItemsListener.changed();
                notifyItemChanged(position);
            }
        });

        holder.btnMinusCart.setOnClickListener(new View.OnClickListener() { // Handles the case where user wants to decrease the quantity of a certain item in cart
            @Override
            public void onClick(View view) {
                Integer currentQuantity = cartItems.get(foodDomain);
                if (currentQuantity == null || currentQuantity == 0) {
                    return;
                }

                cartManager.removeCartItem(context, foodDomain);
                changeNumberItemsListener.changed();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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