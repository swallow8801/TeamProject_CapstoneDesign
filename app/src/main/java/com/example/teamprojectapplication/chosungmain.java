package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;



public class chosungmain extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosungmain);



        TextView textview4 = findViewById(R.id.textView4);
        TextView textview5 = findViewById(R.id.textView5);
        TextView textview12 = findViewById(R.id.textView12);
        TextView textview13 = findViewById(R.id.textView13);
        TextView textview14 = findViewById(R.id.textView14);



        ImageView backButton = findViewById(R.id.backbutton_1);
        ImageView movie = findViewById(R.id.imageView11);
        ImageView songButton = findViewById(R.id.imageView15);
        ImageView jobButton = findViewById(R.id.imageView16);
        ImageView drama = findViewById(R.id.imageView17);
        ImageView howtoplay = findViewById(R.id.howtoplay);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howtoplay.setVisibility(View.INVISIBLE);
                textview4.setVisibility(View.VISIBLE);
                textview5.setVisibility(View.VISIBLE);
                textview12.setVisibility(View.VISIBLE);
                textview13.setVisibility(View.VISIBLE);
                textview14.setVisibility(View.VISIBLE);
                backButton.setVisibility(View.VISIBLE);

                movie.setVisibility(View.VISIBLE);
                songButton.setVisibility(View.VISIBLE);
                jobButton.setVisibility(View.VISIBLE);
                drama.setVisibility(View.VISIBLE);

            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chosungmain.this, chosungmovie.class);
                startActivity(intent);
            }
        });

        songButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chosungmain.this, chosungsong.class);
                startActivity(intent);
            }
        });

        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chosungmain.this, chosungdrama.class);
                startActivity(intent);
            }
        });

        jobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chosungmain.this, chosungjob.class);
                startActivity(intent);
            }
        });







    }
}















