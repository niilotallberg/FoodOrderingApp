// Ohjelman koodaamisessa käytetty apuna seuraavia lähteitä:
// https://www.youtube.com/watch?v=9nWcPPHBzMk
// https://www.youtube.com/watch?v=BLfqZlUI_MM&t=122s
// https://www.youtube.com/watch?v=9CkpMm-n5iA

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

    private TextView twCountdown;
    private Button btnHome;
    private Button submitButton;
    private RatingBar rbRatingbar;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_screen);

        twCountdown = findViewById(R.id.twCountdown);

        rbRatingbar = findViewById(R.id.rbRatingbar);

        btnHome = findViewById(R.id.btnHome);

        submitButton = findViewById(R.id.btnSubmit);

        startTimer();

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WaitingScreenActivity.this, HomePageActivity.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitStars(view);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "You can't go back, please return to main menu", Toast.LENGTH_SHORT).show();
    }

    public void submitStars(View view){
        float rating = rbRatingbar.getRating();
        String message = rating + "/5 stars. " + "Thanks for the rating! ";
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
                twCountdown.setText("Sorry, we are a bit late!");

            }
        }.start();


    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        twCountdown.setText(timeLeftFormatted);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}



