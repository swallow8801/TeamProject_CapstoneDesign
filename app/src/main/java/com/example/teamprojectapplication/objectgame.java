package com.example.teamprojectapplication;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.teamprojectapplication.R;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class objectgame extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView;
    private ImageView NextButton;
    private ImageView CheckButton;

    private ImageView HowToPlay;

    private ImageView backbutton;

    private int[][] images = {
            {R.drawable.objectsimage001, R.string.object_title001},
            {R.drawable.objectsimage002, R.string.object_title002},
            {R.drawable.objectsimage003, R.string.object_title003},
            {R.drawable.objectsimage004, R.string.object_title004},
            {R.drawable.objectsimage005, R.string.object_title005},
            {R.drawable.objectsimage006, R.string.object_title006},
            {R.drawable.objectsimage007, R.string.object_title007},
            {R.drawable.objectsimage008, R.string.object_title008},
            {R.drawable.objectsimage009, R.string.object_title009},
            {R.drawable.objectsimage010, R.string.object_title010},
            {R.drawable.objectsimage011, R.string.object_title011},
            {R.drawable.objectsimage012, R.string.object_title012},
            {R.drawable.objectsimage013, R.string.object_title013},
            {R.drawable.objectsimage014, R.string.object_title014},
            {R.drawable.objectsimage015, R.string.object_title015},
            {R.drawable.objectsimage016, R.string.object_title016},
            {R.drawable.objectsimage017, R.string.object_title017},
            {R.drawable.objectsimage018, R.string.object_title018},
            {R.drawable.objectsimage019, R.string.object_title019},
            {R.drawable.objectsimage020, R.string.object_title020},
            {R.drawable.objectsimage021, R.string.object_title021},
            {R.drawable.objectsimage022, R.string.object_title022},
            {R.drawable.objectsimage023, R.string.object_title023},
            {R.drawable.objectsimage024, R.string.object_title024},
            {R.drawable.objectsimage025, R.string.object_title025},
            {R.drawable.objectsimage026, R.string.object_title026},
            {R.drawable.objectsimage027, R.string.object_title027},
            {R.drawable.objectsimage028, R.string.object_title028},
            {R.drawable.objectsimage029, R.string.object_title029},
            {R.drawable.objectsimage030, R.string.object_title030},
            {R.drawable.objectsimage031, R.string.object_title031},
            {R.drawable.objectsimage032, R.string.object_title032},
            {R.drawable.objectsimage033, R.string.object_title033},
            {R.drawable.objectsimage034, R.string.object_title034},
            {R.drawable.objectsimage035, R.string.object_title035},
            {R.drawable.objectsimage036, R.string.object_title036},
            {R.drawable.objectsimage037, R.string.object_title037},
            {R.drawable.objectsimage038, R.string.object_title038},
            {R.drawable.objectsimage039, R.string.object_title039},
            {R.drawable.objectsimage040, R.string.object_title040},
            {R.drawable.objectsimage041, R.string.object_title041},
            {R.drawable.objectsimage042, R.string.object_title042},
            {R.drawable.objectsimage043, R.string.object_title043},
            {R.drawable.objectsimage044, R.string.object_title044},
            {R.drawable.objectsimage045, R.string.object_title045},
            {R.drawable.objectsimage046, R.string.object_title046},
            {R.drawable.objectsimage047, R.string.object_title047},
            {R.drawable.objectsimage048, R.string.object_title048},
            {R.drawable.objectsimage049, R.string.object_title049},
            {R.drawable.objectsimage050, R.string.object_title050},
            {R.drawable.objectsimage051, R.string.object_title051},
            {R.drawable.objectsimage052, R.string.object_title052},
            {R.drawable.objectsimage053, R.string.object_title053},
            {R.drawable.objectsimage054, R.string.object_title054},
            {R.drawable.objectsimage055, R.string.object_title055},
            {R.drawable.objectsimage056, R.string.object_title056},
            {R.drawable.objectsimage057, R.string.object_title057},
            {R.drawable.objectsimage058, R.string.object_title058},
            {R.drawable.objectsimage059, R.string.object_title059},
            {R.drawable.objectsimage060, R.string.object_title060},
            {R.drawable.objectsimage061, R.string.object_title061},
            {R.drawable.objectsimage062, R.string.object_title062},
            {R.drawable.objectsimage063, R.string.object_title063},
            {R.drawable.objectsimage064, R.string.object_title064},
            {R.drawable.objectsimage065, R.string.object_title065},
            {R.drawable.objectsimage066, R.string.object_title066},
            {R.drawable.objectsimage067, R.string.object_title067},
            {R.drawable.objectsimage068, R.string.object_title068},
            {R.drawable.objectsimage069, R.string.object_title069},
            {R.drawable.objectsimage070, R.string.object_title070},
    };

    private Set<Integer> usedImageIndices = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectgame);

        imageView = findViewById(R.id.imageView);
        titleTextView = findViewById(R.id.textView);
        NextButton = findViewById(R.id.nextquiz);
        CheckButton = findViewById(R.id.answercheck);
        backbutton=findViewById(R.id.backbutton_1);
        HowToPlay=findViewById(R.id.howtoplay);

        HowToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HowToPlay.setVisibility(View.INVISIBLE);
                backbutton.setVisibility(View.VISIBLE);
                CheckButton.setVisibility(View.VISIBLE);
                NextButton.setVisibility(View.VISIBLE);
                showRandomImageAndTitle();
            }
        });

        NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleTextView.setVisibility(View.INVISIBLE);
                showRandomImageAndTitle();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        CheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                titleTextView.setVisibility(View.VISIBLE);
            }
        });

    }



    private void showRandomImageAndTitle() {
        Random random = new Random();
        int index;
        if (usedImageIndices.size() < images.length) {
            do {
                index = random.nextInt(images.length);
            } while (usedImageIndices.contains(index));
            usedImageIndices.add(index);
        } else {
            // 이미 모든 이미지가 나왔을 경우, 초기화
            usedImageIndices.clear();
            index = random.nextInt(images.length);
            usedImageIndices.add(index);
        }

        imageView.setImageResource(images[index][0]);
        titleTextView.setText(getString(images[index][1]));
    }
}
