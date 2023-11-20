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

public class chosungmovie extends AppCompatActivity {

    private ImageView checkButton;
    private ImageView nextButton;

    private TextView wordTextView;
    private Random random = new Random();

    private String[] possibleWords = {"ㅊㅂㅂㅇㅅㅁ", "ㄷㄷㄷ", "ㅇㅇㄴㅈ", "ㄱㅅㅊ", "ㄱㄱㄷㅍ", "ㅁㄹ", "ㄱㅎㅈㅇ", "ㅇㅂㅌ", "ㄱㅈㅅㅈ", "ㅇㅂㅈㅅ:ㅇㄷㄱㅇ",
            "ㄱㅎ,ㅇㅇㄷㄴㅈ", "ㅌㅅㅇㅈㅅ", "ㄱㅇㅇㄱ", "ㄴㅇㅈㅊㄱㄹㅅㄱㅎㄴㄷ", "ㅂㅌㅇㅅㅇㄷ", "ㅇㅌㅅㅌㄹ", "ㅅㅅㅎㄱㄴ", "ㅅㅍㅇㄷㅁ", "ㅊㅈㅂㄱㅎ",
            "ㅌㄹㅅㅍㅁ", "ㅇㅁㅎㄱㅇㄷㅎㄱ", "ㅈㅇㄴㄴㅃㄴㅇㅅㅎㄴ", "ㅂㅈㄷㅅ", "ㅌㅅㅂㅇㅊ", "ㄹㅁㅈㄹㅂ",
            "ㅈㄹㄱㅇㄷ", "ㅊㄴㄱㅊ", "ㅅㅂㄲㅈ", "ㅇㅊㅅㄹㅈㅈ", "ㅈㅇㅊ"}; // 가능한 단어 목록

    private HashSet<String> usedWords = new HashSet<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosungmovie);

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

        if (currentWord.equals("ㅊㅂㅂㅇㅅㅁ")) {
            newWord = "칠번방의선물";
        } else if (currentWord.equals("ㄷㄷㄷ")) {
            newWord = "도둑들";
        } else if (currentWord.equals("ㅇㅇㄴㅈ")) {
            newWord = "왕의남자";
        } else if (currentWord.equals("ㄱㅅㅊ")) {
            newWord = "기생충";
        } else if (currentWord.equals("ㄱㄱㄷㅍ")) {
            newWord = "국가대표";
        } else if (currentWord.equals("ㅁㄹ")) {
            newWord = "명량";
        } else if (currentWord.equals("ㄱㅎㅈㅇ")) {
            newWord = "극한직업";
        } else if (currentWord.equals("ㅇㅂㅌ")) {
            newWord = "아바타";
        } else if (currentWord.equals("ㄱㅈㅅㅈ")) {
            newWord = "국제시장";
        } else if (currentWord.equals("ㅇㅂㅈㅅ:ㅇㄷㄱㅇ")) {
            newWord = "어벤져스:엔드게임";
        } else if (currentWord.equals("ㄱㅎ,ㅇㅇㄷㄴㅈ")) {
            newWord = "광해,왕이 된 남자";
        } else if (currentWord.equals("ㅌㅅㅇㅈㅅ")) {
            newWord = "택시운전사";
        } else if (currentWord.equals("ㅂㅌㅇㅅㅇㄷ")) {
            newWord = "뷰티인사이드";
        } else if (currentWord.equals("ㅅㅅㅎㄱㄴ")) {
            newWord = "수상한그녀";
        } else if (currentWord.equals("ㅅㅍㅇㄷㅁ")) {
            newWord = "스파이더맨";
        } else if (currentWord.equals("ㅇㅁㅎㄱㅇㄷㅎㄱ")) {
            newWord = "은밀하게 위대하게";
        } else if (currentWord.equals("ㅊㅈㅂㄱㅎ")) {
            newWord = "최종병기 활";
        } else if (currentWord.equals("ㅈㅇㄴㄴㅃㄴㅇㅅㅎㄴ")) {
            newWord = "좋은놈 나쁜놈 이상한놈";
        } else if (currentWord.equals("ㅂㅈㄷㅅ")) {
            newWord = "범죄도시";
        } else if (currentWord.equals("ㅌㅅㅂㅇㅊ")) {
            newWord = "투사부일체";
        } else if (currentWord.equals("ㄹㅁㅈㄹㅂ")) {
            newWord = "레미제라블";
        } else if (currentWord.equals("ㅈㄹㄱㅇㄷ")) {
            newWord = "쥬라기월드";
        } else if (currentWord.equals("ㅊㄴㄱㅊ")) {
            newWord = "청년경찰";
        } else if (currentWord.equals("ㅇㅌㅅㅌㄹ"))
            newWord = "인터스텔라";
        else if (currentWord.equals("ㅈㅇㅊ"))
            newWord = "전우치";
        else if (currentWord.equals("ㅌㄹㅅㅍㅁ"))
            newWord = "트렌스포머";
        else if (currentWord.equals("ㄱㅇㅇㄱ"))
            newWord = "겨울왕국";
        else if (currentWord.equals("ㄴㅇㅈㅊㄱㄹㅅㄱㅎㄴㄷ"))
            newWord = "내여자친구를소개합니다";
        else if (currentWord.equals("ㅅㅂㄲㅈ"))
            newWord = "숨바꼭질";
        else if (currentWord.equals("ㅇㅊㅅㄹㅈㅈ"))
            newWord = "인천상륙작전";


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