package com.example.teamprojectapplication;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class relayspeakingfour extends AppCompatActivity {

    private ImageView nextButton;
    private ImageView backButton;
    private TextView wordTextView;

    private String[] changeWord = {"슈퍼□□□", "진공□□□", "김치□□□", "가족□□□","자동□□□","롤러□□□",
            "안드□□□","이비□□□","콜레□□□","오므□□□","빠리□□□","필라□□□","히비□□□","엘리□□□","가스□□□","이산□□□"
            ,"이산□□□","스터□□□","포켓□□□","아이□□□","레몬□□□","자몽□□□","마리□□□","버스□□□","오세□□□"
            ,"이니□□□","빨래□□□","무선□□□","오케□□□","중앙□□□","트렌□□□","아인□□□","염화□□□","과산□□□","강남□□□"
            ,"이노□□□","최애□□□","개그□□□","악동□□□","인터□□□","어셈□□□","전자□□□","하룻□□□","주책□□□"};
    private List<String> remainingWords = new ArrayList<>();
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relayspeakingfour);

        nextButton = findViewById(R.id.button7);
        backButton = findViewById(R.id.backbutton_1);
        wordTextView = findViewById(R.id.textView7);
        ImageView imageView23=findViewById(R.id.imageView23);
        ImageView imageView24=findViewById(R.id.imageView24);
        ImageView imageView25=findViewById(R.id.imageView25);


        remainingWords.addAll(Arrays.asList(changeWord));

        int randomIndex2 = random.nextInt(remainingWords.size());
        String FirstWord=remainingWords.remove(randomIndex2);
        wordTextView.setText(FirstWord);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (remainingWords.isEmpty()) {
                    wordTextView.setTextSize(Dimension.DP,140);
                    wordTextView.setText("문제가 끝났어요.\n다음 업데이트를\n기다려주세요!");
                    imageView23.setVisibility(View.INVISIBLE);
                    imageView24.setVisibility(View.INVISIBLE);
                    imageView25.setVisibility(View.INVISIBLE);
                    nextButton.setVisibility(View.INVISIBLE);
                } else {
                    int randomIndex = random.nextInt(remainingWords.size());
                    String selectedWord = remainingWords.remove(randomIndex);
                    wordTextView.setText(selectedWord);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
