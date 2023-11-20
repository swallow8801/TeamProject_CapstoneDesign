package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class touchgame extends AppCompatActivity {
    private TextView scoreTextView,onlyscore;
    private TextView timeTextView;
    private TextView startButton;
    private int score = 0;
    private boolean gameStarted = false;

    private ImageView touchImageView,repeatbutton;
    private ImageView backbutton,howtoplay;
    private final long gameDuration = 10000; // 10 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touchgame);

        scoreTextView = findViewById(R.id.scoreTextView);
        startButton = findViewById(R.id.startButton);
        touchImageView = findViewById(R.id.handd);
        timeTextView = findViewById(R.id.timeTextView);
        backbutton = findViewById(R.id.backbutton_1);
        repeatbutton=findViewById(R.id.repeatbutton);
        onlyscore=findViewById(R.id.onlyscore);
        howtoplay=findViewById(R.id.howtoplay);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backbutton.setVisibility(View.VISIBLE);
                timeTextView.setVisibility(View.VISIBLE);
                scoreTextView.setVisibility(View.VISIBLE);
                onlyscore.setVisibility(View.VISIBLE);
                howtoplay.setVisibility(View.INVISIBLE);
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startButton.setVisibility(View.INVISIBLE);
                startGame();
            }
        });

        touchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameStarted) {
                    incrementScore();
                }
            }
        });

        repeatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repeatbutton.setVisibility(View.INVISIBLE);
                scoreTextView.setText("0");
                onlyscore.setText("score");
                startButton.setVisibility(View.VISIBLE);
            }
        });

    }

    private void startGame() {
        score = 0;
        scoreTextView.setText("0");
        startButton.setVisibility(View.INVISIBLE);
        gameStarted = true;

        new CountDownTimer(gameDuration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                seconds %= 60;
                String time = String.format("%02d:%02d", minutes, seconds);
                timeTextView.setText(time);
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void incrementScore() {
        score++;
        scoreTextView.setText("" + score);
    }

    private void endGame() {
        scoreTextView.setText("" + score);
        onlyscore.setText("게임종료");
        gameStarted = false;
        repeatbutton.setVisibility(View.VISIBLE);
    }

}
