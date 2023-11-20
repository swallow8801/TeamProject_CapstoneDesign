package com.example.teamprojectapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class thirtyone extends AppCompatActivity {
    private TextView textView2,textView3,textView6;
    private ImageView button1, button2, button3, backbutton;
    private int sum = 0;
    private Random random = new Random();

    private boolean Touched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirtyone);

        ImageView howtoplay = findViewById(R.id.howtoplay);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView6=findViewById(R.id.textView6);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        backbutton = findViewById(R.id.backbutton_1);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howtoplay.setVisibility(View.INVISIBLE);
                backbutton.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);
                button3.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
            }
        });



        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Touched){
                    sum=0;
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    Touched=false;
                    textView3.setVisibility(View.INVISIBLE);
                    textView6.setVisibility(View.INVISIBLE);
                    textView2.setText("0");
                }
            }
        });
        // Button1 클릭 이벤트 처리
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 1;
                textView2.setText(sum+"");

                disableRandomButton();
                checkGameStatus();
            }
        });

        // Button2 클릭 이벤트 처리
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 2;
                textView2.setText(sum+"");

                disableRandomButton();
                checkGameStatus();
            }
        });

        // Button3 클릭 이벤트 처리
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sum += 3;
                textView2.setText(sum+"");

                disableRandomButton();
                checkGameStatus();
            }
        });
    }

    // 게임 종료 여부 확인
    private void checkGameStatus() {
        if (sum >= 31) {
            textView3.setVisibility(View.VISIBLE);
            button1.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.INVISIBLE);
            button3.setVisibility(View.INVISIBLE);
            Touched=true;
            textView6.setVisibility(View.VISIBLE);
        }
    }

    // 무작위 버튼 비활성화
    private void disableRandomButton() {
        ImageView[] buttons = {button1, button2, button3};
        int randomIndex = random.nextInt(3); // 0, 1, 또는 2 중 하나를 선택
        int randomCount = random.nextInt(3);
        if(randomCount==0){
            for (int i = 0; i < 3; i++) {
                if(i!=randomIndex){
                    buttons[i].setVisibility(View.VISIBLE);
                }
                else
                    buttons[i].setVisibility(View.INVISIBLE);
            }
        }
        if(randomCount==1){
            for (int i = 0; i < 3; i++) {
                if(i!=randomIndex){
                    buttons[i].setVisibility(View.INVISIBLE);
                }
                else
                    buttons[i].setVisibility(View.VISIBLE);
            }
        }
        if(randomCount==2){
            for (int i = 0; i < 3; i++) {
                buttons[i].setVisibility(View.VISIBLE); // 무작위 버튼만 비활성화
            }
        }
    }
}


