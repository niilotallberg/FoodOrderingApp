// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

package com.example.foodorderingapp.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.R;

public class FaqActivity extends AppCompatActivity {

    private TextView answer1, answer2, answer3, answer4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        answer4 = findViewById(R.id.answer4);

        answer1.setVisibility(View.GONE);
        answer2.setVisibility(View.GONE);
        answer3.setVisibility(View.GONE);
        answer4.setVisibility(View.GONE);

        TextView question1 = findViewById(R.id.question1);
        question1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer1.getVisibility() == View.VISIBLE) {
                    answer1.setVisibility(View.GONE);
                } else {
                    answer1.setVisibility(View.VISIBLE);
                }
            }
        });

        TextView question2 = findViewById(R.id.question2);
        question2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer2.getVisibility() == View.VISIBLE) {
                    answer2.setVisibility(View.GONE);
                } else {
                    answer2.setVisibility(View.VISIBLE);
                }
            }
        });

        TextView question3 = findViewById(R.id.question3);
        question3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer3.getVisibility() == View.VISIBLE) {
                    answer3.setVisibility(View.GONE);
                } else {
                    answer3.setVisibility(View.VISIBLE);
                }
            }
        });

        TextView question4 = findViewById(R.id.question4);
        question4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer4.getVisibility() == View.VISIBLE) {
                    answer4.setVisibility(View.GONE);
                } else {
                    answer4.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
