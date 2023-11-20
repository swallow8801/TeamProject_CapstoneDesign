package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashSet;

import android.os.Bundle;
import java.util.Random;

public class chosungdrama extends AppCompatActivity {

    private ImageView checkButton;
    private ImageView nextButton;

    private TextView wordTextView;
    private Random random = new Random();

    private String[] possibleWords = {"ㅌㅇㅇㅎㅇ","ㄷㄲㅂ","ㅁㅅㅌㅅㅅㅇ","ㅂㅇㅅㅇㄱㄷ","ㅇㄷㅎㄹㅇㄱㄱㅍ","ㄴㅅㄹㅂㄴㅅㄴ","ㅂㅇㅇㄴㄱㄴ","ㅇㅌㄹㅈ","ㄷㅂㄲㅍㅁㄹ",
            "ㅅㄱㄹㅇㅇㅅㅅㅎ","ㄲㅂㄷㄴㅈ","ㄴㅇㅇㄷㄴㄱㄴㅁㅇ","ㅍㄴㅋㅇ","ㅅㅋㅇㅋㅅ","ㅁㅅ","ㅅㅇㅋㅈㅁㄱㅊㅇ","ㅇㅌㅇㅋㄹㅆ","ㅇㅎㅂㄹㄱㅈㅇㅊㅇ",
            "ㄱㄱㅇㅅ","ㅋㅁㅎㅁ","ㄴㅇㅇㅈㅆ","ㄷㄱㄹㄹ","ㅅㅋㄹㄱㄷ","ㅆㅁㅇㅇㅇ","ㅇㅊ","ㅇㅇㄷㄴㅈ","ㅎㅌㄷㄹㄴ","ㄸㅇㅎㅇ","ㅎㄹㅍㅇㄷ","ㅇㅂㅅ"};


    private HashSet<String> usedWords = new HashSet<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosungdrama);

        checkButton = findViewById(R.id.button8);
        nextButton = findViewById(R.id.button7);
        wordTextView = findViewById(R.id.textView7);
        ImageView backButton = findViewById(R.id.backbutton_1);

        displayRandomWord();


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // 뒤로 가기 동작 수행
            }
        });



        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeWord();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayRandomWord();
            }
        });
    }

    private void changeWord() {
        String currentWord = wordTextView.getText().toString();
        String newWord = "";

        if (currentWord.equals("ㅌㅇㅇㅎㅇ")) {
            newWord = "태양의 후예";
        } else if (currentWord.equals("ㄷㄲㅂ")) {
            newWord = "도깨비";
        } else if (currentWord.equals("ㅁㅅㅌㅅㅅㅇ")) {
            newWord = "미스터 션샤인";
        } else if (currentWord.equals("ㅂㅇㅅㅇㄱㄷ")) {
            newWord = "별에서 온 그대";
        } else if (currentWord.equals("ㅇㄷㅎㄹㅇㄱㄱㅍ")) {
            newWord = "응답하라 1988";
        } else if (currentWord.equals("ㄴㅅㄹㅂㄴㅅㄴ")) {
            newWord = "냄새를 보는 소녀";
        } else if (currentWord.equals("ㅂㅇㅇㄴㄱㄴ")) {
            newWord = "백일의 낭군님";
        } else if (currentWord.equals("ㄷㅂㄲㅍㅁㄹ")) {
            newWord = "동백꽃 필 무렵";
        } else if (currentWord.equals("ㅇㅌㄹㅈ")) {
            newWord = "안투라지";

        } else if (currentWord.equals("ㅅㄱㄹㅇㅇㅅㅅㅎ")) {
            newWord = "슬기로운 의사생활";
        } else if (currentWord.equals("ㄲㅂㄷㄴㅈ")) {
            newWord = "꽃보다 남자";
        } else if (currentWord.equals("ㄴㅇㅇㄷㄴㄱㄴㅁㅇ")) {
            newWord = "내 아이디는 강남미인";
        } else if (currentWord.equals("ㅍㄴㅋㅇ")) {
            newWord = "피노키오";
        } else if (currentWord.equals("ㅅㅋㅇㅋㅅ")) {
            newWord = "스카이 캐슬";
        } else if (currentWord.equals("ㅁㅅ")) {
            newWord = "미생";
        } else if (currentWord.equals("ㅅㅇㅋㅈㅁㄱㅊㅇ")) {
            newWord = "사이코지만 괜찮아";
        } else if (currentWord.equals("ㅇㅎㅂㄹㄱㅈㅇㅊㅇ"))
            newWord = "알함브라 궁전의 추억";

        else if (currentWord.equals("ㄱㄱㅇㅅ"))
            newWord = "구가의 서";
        else if (currentWord.equals("ㅋㅁㅎㅁ"))
            newWord = "킬미, 힐미";
        else if (currentWord.equals("ㄴㅇㅇㅈㅆ"))
            newWord = "나의 아저씨";
        else if (currentWord.equals("ㅇㅂㅅ"))
            newWord = "어비스";
        else if (currentWord.equals("ㄷㄱㄹㄹ"))
            newWord = "더 글로리";
        else if (currentWord.equals("ㅅㅋㄹㄱㄷ"))
            newWord = "시크릿 가든";
        else if (currentWord.equals("ㅆㅁㅇㅇㅇ"))
            newWord = "쌈, 마이웨이";
        else if (currentWord.equals("ㅇㅊ"))
            newWord = "왓쳐";
        else if (currentWord.equals("ㅇㅇㄷㄴㅈ"))
            newWord = "왕이 된 남자";
        else if (currentWord.equals("ㅎㅌㄷㄹㄴ"))
            newWord = "호텔델루나";
        else if (currentWord.equals("ㄸㅇㅎㅇ"))
            newWord = "또 오해영";
        else if (currentWord.equals("ㅎㄹㅍㅇㄷ"))
            newWord = "해를 품은 달";
        else if (currentWord.equals("ㅇㅌㅇㅋㄹㅆ"))
            newWord = "이태원 클라쓰";






        wordTextView.setText(newWord);
    }

    private void displayRandomWord() {
        if (usedWords.size() == possibleWords.length) {
            // 모든 단어를 사용한 경우
            wordTextView.setText("문제가 다 끝났습니다");
            checkButton.setVisibility(View.INVISIBLE); // "checkButton" 비활성화
            nextButton.setVisibility(View.INVISIBLE);  // "nextButton" 비활성화
        } else {

            String randomWord;
            do {
                int randomIndex = random.nextInt(possibleWords.length);
                randomWord = possibleWords[randomIndex];
            } while (usedWords.contains(randomWord));

            usedWords.add(randomWord);
            wordTextView.setText(randomWord);
        }

    }
}

