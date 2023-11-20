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

public class personimagegame extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView;
    private ImageView NextButton;
    private ImageView CheckButton;

    private ImageView HowToPlay;

    private ImageView backbutton;

    private int[][] images = {
            {R.drawable.personimage001, R.string.image_title001},
            {R.drawable.personimage002, R.string.image_title002},
            {R.drawable.personimage007, R.string.image_title007},
            {R.drawable.personimage008, R.string.image_title008},
            {R.drawable.personimage009, R.string.image_title009},
            {R.drawable.personimage010, R.string.image_title010},
            {R.drawable.personimage012, R.string.image_title012},
            {R.drawable.personimage013, R.string.image_title013},
            {R.drawable.personimage014, R.string.image_title014},
            {R.drawable.personimage015, R.string.image_title015},
            {R.drawable.personimage016, R.string.image_title016},
            {R.drawable.personimage017, R.string.image_title017},
            {R.drawable.personimage018, R.string.image_title018},
            {R.drawable.personimage019, R.string.image_title019},
            {R.drawable.personimage020, R.string.image_title020},
            {R.drawable.personimage021, R.string.image_title021},
            {R.drawable.personimage022, R.string.image_title022},
            {R.drawable.personimage023, R.string.image_title023},
            {R.drawable.personimage024, R.string.image_title024},
            {R.drawable.personimage025, R.string.image_title025},
            {R.drawable.personimage026, R.string.image_title026},
            {R.drawable.personimage027, R.string.image_title027},
            {R.drawable.personimage028, R.string.image_title028},
            {R.drawable.personimage029, R.string.image_title029},
            {R.drawable.personimage030, R.string.image_title030},
            {R.drawable.personimage031, R.string.image_title031},
            {R.drawable.personimage032, R.string.image_title032},
            {R.drawable.personimage033, R.string.image_title033},
            {R.drawable.personimage034, R.string.image_title034},
            {R.drawable.personimage035, R.string.image_title035},
            {R.drawable.personimage036, R.string.image_title036},
            {R.drawable.personimage037, R.string.image_title037},
            {R.drawable.personimage038, R.string.image_title038},
            {R.drawable.personimage039, R.string.image_title039},
            {R.drawable.personimage040, R.string.image_title040},
            {R.drawable.personimage041, R.string.image_title041},
            {R.drawable.personimage042, R.string.image_title042},
            {R.drawable.personimage043, R.string.image_title043},
            {R.drawable.personimage044, R.string.image_title044},
            {R.drawable.personimage045, R.string.image_title045},
            {R.drawable.personimage046, R.string.image_title046},
            {R.drawable.personimage047, R.string.image_title047},
            {R.drawable.personimage048, R.string.image_title048},
            {R.drawable.personimage049, R.string.image_title049},
            {R.drawable.personimage050, R.string.image_title050},
            {R.drawable.personimage051, R.string.image_title051},
            {R.drawable.personimage052, R.string.image_title052},
            {R.drawable.personimage053, R.string.image_title053},
            {R.drawable.personimage054, R.string.image_title054},
            {R.drawable.personimage055, R.string.image_title055},
            {R.drawable.personimage056, R.string.image_title056},
            {R.drawable.personimage057, R.string.image_title057},
            {R.drawable.personimage058, R.string.image_title058},
            {R.drawable.personimage059, R.string.image_title059},
            {R.drawable.personimage060, R.string.image_title060},
            {R.drawable.personimage061, R.string.image_title061},
            {R.drawable.personimage062, R.string.image_title062},
            {R.drawable.personimage063, R.string.image_title063},
            {R.drawable.personimage064, R.string.image_title064},
            {R.drawable.personimage065, R.string.image_title065},
            {R.drawable.personimage066, R.string.image_title066},
            {R.drawable.personimage067, R.string.image_title067},
            {R.drawable.personimage068, R.string.image_title068},
            {R.drawable.personimage069, R.string.image_title069},
            {R.drawable.personimage070, R.string.image_title070},
            {R.drawable.personimage072, R.string.image_title072},
            {R.drawable.personimage073, R.string.image_title073},
            {R.drawable.personimage074, R.string.image_title074},
            {R.drawable.personimage075, R.string.image_title075},
            {R.drawable.personimage077, R.string.image_title077},
            {R.drawable.personimage078, R.string.image_title078},
            {R.drawable.personimage079, R.string.image_title079},
            {R.drawable.personimage082, R.string.image_title082},
            {R.drawable.personimage084, R.string.image_title084},
            {R.drawable.personimage086, R.string.image_title086},
            {R.drawable.personimage090, R.string.image_title090},
            {R.drawable.personimage093, R.string.image_title093},
            {R.drawable.personimage094, R.string.image_title094},
            {R.drawable.personimage095, R.string.image_title095},
            {R.drawable.personimage096, R.string.image_title096},
            {R.drawable.personimage097, R.string.image_title097},
            {R.drawable.personimage098, R.string.image_title098},
    };

    private Set<Integer> usedImageIndices = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personimagegame);

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
