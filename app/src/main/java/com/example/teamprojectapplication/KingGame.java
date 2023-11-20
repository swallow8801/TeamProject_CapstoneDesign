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



public class KingGame extends AppCompatActivity {

    private int player = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_king_game);


        ImageView playerPlus = findViewById(R.id.playerplus);
        ImageView playerMinus = findViewById(R.id.playerminus);

        ImageView howtoplay= findViewById(R.id.howtoplay);

        TextView playertext = findViewById(R.id.playertext);
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
                if(player<9){
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




        textView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number1 = player;


                Intent intent = new Intent(KingGame.this, KingGameReal.class);

                intent.putExtra("인원수", number1);

                startActivity(intent);
            }
        });

    }
}
