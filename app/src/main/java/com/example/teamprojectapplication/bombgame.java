package com.example.teamprojectapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.teamprojectapplication.R;

import java.util.Random;

public class bombgame extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer2;
    private CountDownTimer countDownTimer;
    private ImageView bombImage,bombingImage;
    private ImageView button1;

    private ImageView howtoplay;

    private boolean isClickable=true;
    private boolean isClickable2=true;
    private TextView gametext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bombgame);


        bombImage=findViewById(R.id.bombimage);
        bombingImage=findViewById(R.id.bombingimage);
        button1=findViewById(R.id.backbutton_1);
        gametext=findViewById(R.id.gametext);
        howtoplay=findViewById(R.id.howtoplay);


        mediaPlayer = MediaPlayer.create(this, R.raw.bombsound);
        mediaPlayer2 = MediaPlayer.create(this, R.raw.bombingsound);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howtoplay.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.VISIBLE);
            }
        });

        bombImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClickable) {
                    isClickable=false;
                    isClickable2=true;
                    gametext.setText("폭탄 점화중");
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    startTimer();
                }
            }
        });

        bombingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClickable2) {
                    isClickable2=false;
                    isClickable=true;
                    gametext.setText("폭탄 점화중");
                    bombingImage.setVisibility(View.INVISIBLE);
                    bombImage.setVisibility(View.VISIBLE);
                    mediaPlayer.seekTo(0);
                    mediaPlayer.start();
                    startTimer();
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });

    }

    private void startTimer() {
        // 랜덤한 시간 설정 (10초 ~ 60초)
        Random random = new Random();
        final long totalTimeInMillis = (random.nextInt(11) + 10) * 1000;

        countDownTimer = new CountDownTimer(totalTimeInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                performActionOnTimerFinish();
            }
        };

        countDownTimer.start();
    }



    private void performActionOnTimerFinish() {
        mediaPlayer.pause();
        mediaPlayer2.seekTo(0);
        mediaPlayer2.start();
        bombImage.setVisibility(View.INVISIBLE);
        bombingImage.setVisibility(View.VISIBLE);
        gametext.setText("폭탄이 터졌습니다!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (mediaPlayer2 != null) {
            mediaPlayer2.release();
        }
    }
}