package com.example.teamprojectapplication;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class relayspeakingfive extends AppCompatActivity {

    private ImageView nextButton;
    private ImageView backButton;
    private TextView wordTextView;

    private String[] changeWord = {"갈팡□□","갑오□□","경상□□","계란□□","고등□□","고슴□□","국가□□","국어□□","금상□□","기말□□"
            ,"금상□□","기말□□","네트□□","뉴발□□","도깨□□","도라□□","동그□□","물레□□","바이□□","버터□□","버터□□","백설□□","백과□□","붉은□□","비닐□□","비네□□"
            ,"사이□□","소녀□□","신사□□","아이□□","오토□□","장화□□","코카□□","고진□□","일자□□","금의□□","일석□□","다다□□","역지□□","우이□□","대기□□"
            ,"오합□□","권모□□","기승□□","설상□□","삼삼□□","삼삼□□","십중□□","도학□□","속전□□","동문□□","치즈□□","구사□□","일취□□","천재□□","유일□□"
            ,"작심□□","명명□□","신토□□","일심□□","마이□□","동가□□","시시□□"};
private List<String> remainingWords = new ArrayList<>();
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relayspeakingfive);

        nextButton = findViewById(R.id.button7);
        backButton = findViewById(R.id.backbutton_1);
        wordTextView = findViewById(R.id.textView7);
        ImageView imageView23=findViewById(R.id.imageView23);
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