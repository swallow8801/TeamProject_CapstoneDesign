package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class KingGameReal extends AppCompatActivity {
    int num1=1;
    private ArrayList<Integer> getRandomNumbers(int start, int end, int count) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        Random random = new Random();
        while (result.size() < count) {
            int randomNumber = random.nextInt(end - start + 1) + start;
            if (!result.contains(randomNumber)) {
                result.add(randomNumber);
            }
        }
        return result;
    }

    private ArrayList<Integer> getRandomShuffledNumbers(int start, int end) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_king_game_real);


        Intent intent = getIntent();
        int num2 = intent.getIntExtra("인원수", 0); // 기본값은 0으로 설정


        ImageView button1 = findViewById(R.id.backbutton_1);
        TextView textViewCenter = findViewById(R.id.textViewCenter);
        ImageView imageView = findViewById(R.id.imageView);
        TextView touchTextView = findViewById(R.id.touchTextView);
        ImageView bottomButton = findViewById(R.id.bottomButton);


        textViewCenter.setText(num1+" / "+num2);

        ArrayList<Integer> randomNumbers = getRandomNumbers(1, num2, 1);
        ArrayList<Integer> randomChoice = getRandomShuffledNumbers(1,num2);
        int Choice = randomChoice.get(0);

        if(!randomNumbers.contains(num1)){
            touchTextView.setTextColor(Color.parseColor("#000000"));
            touchTextView.setText(""+Choice);
        }
        else{
            touchTextView.setTextColor(Color.parseColor("#FF0000"));
            touchTextView.setText("왕");
        }


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 터치 시 이미지뷰를 invisible로 변경, 텍스트뷰를 visible로 변경
                        imageView.setVisibility(View.INVISIBLE);
                        touchTextView.setVisibility(View.VISIBLE);
                        return true;
                    case MotionEvent.ACTION_UP:
                        // 터치 시 이미지뷰를 invisible로 변경, 텍스트뷰를 visible로 변경
                        imageView.setVisibility(View.VISIBLE);
                        touchTextView.setVisibility(View.INVISIBLE);
                        return true;
                }
                return false;
            }
        });

        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1++;
                textViewCenter.setText(num1+" / "+num2);
                if(!randomNumbers.contains(num1)){
                    int Choice = randomChoice.get(num1-1);
                    touchTextView.setTextColor(Color.parseColor("#000000"));
                    touchTextView.setText(""+Choice);
                }
                else{
                    touchTextView.setTextColor(Color.parseColor("#FF0000"));
                    touchTextView.setText("왕");
                }
                if(num1==num2){
                    bottomButton.setVisibility(View.INVISIBLE);
                }
            }
        });



    }
}

