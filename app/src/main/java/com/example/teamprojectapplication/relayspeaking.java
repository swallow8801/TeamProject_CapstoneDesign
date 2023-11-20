package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Bundle;

public class relayspeaking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relayspeaking);

        ImageView one = findViewById(R.id.imageView14);
        ImageView two = findViewById(R.id.imageView13);
        ImageView backbutton = findViewById(R.id.backbutton_1);
        ImageView howtoplay = findViewById(R.id.howtoplay);

        TextView textView15 = findViewById(R.id.textView15);
        TextView textView17 = findViewById(R.id.textView17);
        TextView textView18 = findViewById(R.id.textView18);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView15.setVisibility(View.VISIBLE);
                textView17.setVisibility(View.VISIBLE);
                textView18.setVisibility(View.VISIBLE);
                one.setVisibility(View.VISIBLE);
                two.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);
                howtoplay.setVisibility(View.INVISIBLE);
            }
        });



        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(relayspeaking.this, relayspeakingfour.class);
                startActivity(intent);

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(relayspeaking.this, relayspeakingfive.class);
                startActivity(intent);

            }
        });

    }
}
