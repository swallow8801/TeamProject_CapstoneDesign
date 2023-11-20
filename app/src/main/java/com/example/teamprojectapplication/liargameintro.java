package com.example.teamprojectapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class liargameintro extends AppCompatActivity {

    private int player = 3;
    private int liar = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liargameintro);

        EditText editTheme = findViewById(R.id.editTheme);
        EditText editWord = findViewById(R.id.editWord);

        ImageView playerPlus = findViewById(R.id.playerplus);
        ImageView playerMinus = findViewById(R.id.playerminus);
        ImageView LiarPlus = findViewById(R.id.liarplus);
        ImageView LiarMinus = findViewById(R.id.liarminus);
        ImageView howtoplay= findViewById(R.id.howtoplay);

        TextView playertext = findViewById(R.id.playertext);
        TextView liarText = findViewById(R.id.liartext);
        TextView textView16 = findViewById(R.id.textView16);

        ImageView backbutton = findViewById(R.id.backbutton_1);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backbutton.setVisibility(View.VISIBLE);
                howtoplay.setVisibility(View.INVISIBLE);

            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        playerPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player<10){
                    player++;
                    playertext.setText(String.valueOf(player));
                }
            }
        });

        playerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player>3){
                    player--;
                    playertext.setText(String.valueOf(player));
                }
            }
        });

        LiarPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(liar<3){
                    liar++;
                    liarText.setText(String.valueOf(liar));
                }
            }
        });

        LiarMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(liar>1){
                    liar--;
                    liarText.setText(String.valueOf(liar));
                }
            }
        });



        textView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = editTheme.getText().toString();
                String text2 = editWord.getText().toString();
                int number1 = player;
                int number2 = liar;



                Intent intent = new Intent(liargameintro.this, liargame.class);
                intent.putExtra("주제", text1);
                intent.putExtra("주제어", text2);
                intent.putExtra("인원수", number1);
                intent.putExtra("라이어", number2);

                startActivity(intent);
            }
        });

    }
}
