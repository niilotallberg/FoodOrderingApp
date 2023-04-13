// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA


package com.example.foodorderingapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodorderingapp.Domain.FoodDomain;
import com.example.foodorderingapp.R;

public class ShowDetailActivity extends AppCompatActivity {
    private TextView btnAddToCart;
    private TextView txtDetailTitle, txtDetailPrice, txtAmount, txtDescription;
    private ImageView ivMinusButton, ivPlusButton, ivDetailPic;
    private FoodDomain object;
    private int orderAmount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        ivDetailPic.setImageResource(drawableResourceId);

        txtDetailTitle.setText(object.getTitle());
        txtDetailPrice.setText(object.getFee() + "€");
        txtDescription.setText(object.getDescription());
        txtAmount.setText(String.valueOf(orderAmount));

        ivPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderAmount = orderAmount+1;
                txtAmount.setText(String.valueOf(orderAmount));
            }
        });

        ivMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(orderAmount > 1) {
                    orderAmount = orderAmount-1;
                }
                txtAmount.setText(String.valueOf(orderAmount));
            }
        });
    }

    private void initView() {
        btnAddToCart = findViewById(R.id.btnAddToCart);
        txtDetailTitle = findViewById(R.id.txtDetailTitle);
        txtDetailPrice = findViewById(R.id.txtDetailPrice);
        txtDescription = findViewById(R.id.txtDescription);
        txtAmount = findViewById(R.id.txtAmount);
        ivMinusButton = findViewById(R.id.ivMinusButton);
        ivPlusButton = findViewById(R.id.ivPlusButton);
        ivDetailPic = findViewById(R.id.ivDetailPic);
    }
}