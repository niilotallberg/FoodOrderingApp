package com.example.foodorderingapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderingapp.R;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaitingScreenActivity extends AppCompatActivity {

    private TextView textViewCountdown;
    private Button buttonStart;

    private RatingBar rb_ratingbar;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);

        textViewCountdown = findViewById(R.id.text_view_countdown);
        buttonStart = findViewById(R.id.button_start);
        rb_ratingbar= findViewById(R.id.rb_ratingbar);

        // Check if the user came from the OrderConfirmation activity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("fromOrderConfirmation")) {
            // Start the timer
            startTimer();
        } else {
            buttonStart.setVisibility(View.INVISIBLE);
        }

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });
    }

    public void submitStars(View view){
        float rating = rb_ratingbar.getRating();
        String message = "Rating: " + rating;
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    private void startTimer() {
        Random random = new Random();
        int randomTime = random.nextInt(11) + 20; // Generates random time between 10-30 minutes
        timeLeftInMillis = TimeUnit.MINUTES.toMillis(randomTime);

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;

                updateCountdownText();
            }

            @Override
            public void onFinish() {
                textViewCountdown.setText("Tilauksesi pitäisi saapua näillä minuuteilla");

            }
        }.start();

        buttonStart.setVisibility(View.INVISIBLE);
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        textViewCountdown.setText(timeLeftFormatted);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}



