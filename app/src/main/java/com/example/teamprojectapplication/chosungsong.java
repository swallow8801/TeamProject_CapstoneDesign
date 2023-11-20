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

public class chosungsong extends AppCompatActivity {

    private ImageView checkButton;
    private ImageView nextButton;

    private TextView wordTextView;
    private Random random = new Random();

    private String[] possibleWords = {"ㅇㅇㅇ","ㅎㅇㅈㅈㅁㅎㅇ","ㅇㅇㅈ","ㅈㅇㅂㅈㅇㄲ","ㅇㅂ,ㅍㅅㅋㄱㄹㄱㅍㄹㅅㅇㅇㅇㄴ","ㅈㅈㅎㄴㅇㅇㄷㅇㅇㅎ",
            "ㅋㅋ","ㄱㄹㅇㅅ","ㅈㅇㄹㄱㅎㅈ","ㄱㄹㄷㅇㅅ","ㅍㅅㅌ","ㄷㄲㅂㅂ","ㄴㅎ","ㅅㄱㅇㅈㅍㅅ","ㅇㄴㅎㅇㅈㅇㅇ","ㅅㅊㅇㅁㄱ"
            ,"ㅎㅍㅇㅈㄱㄷㅅㅇㄱ","ㅇㄹㄴㅇㄹㅂ","ㄱㄴㅅㅌㅇ","ㅇㄹㄹ","ㄷㅅㅁㄴㅅㄱ","ㅇㅂㄹㅋㄷㅂㄹ","ㄴㄱㅈㅇㅈㄴㄱ",
            "ㅆㄹㅆㄹ","ㄸㄷㄸㄷ","ㅈㅇㄴ","ㄴㅋㅇ","ㅍㅌㅅㅌㅂㅇㅂ","ㅌㄹㅂㅁㅇㅋ","ㅅㄹㅇㅎㄷ"};
    private HashSet<String> usedWords = new HashSet<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosungsong);

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

        if (currentWord.equals("ㅇㅇㅇ")) {
            newWord = "아이엠(아이브)";
        } else if (currentWord.equals("ㅎㅇㅈㅈㅁㅎㅇ")) {
            newWord = "헤어지자 말해요(박재정)";
        } else if (currentWord.equals("ㅇㅇㅈ")) {
            newWord = "OMG(뉴진스)";
        } else if (currentWord.equals("ㅈㅇㅂㅈㅇㄲ")) {
            newWord = "좋은밤 좋은꿈(Nerd Connection)";
        } else if (currentWord.equals("ㅇㅂ,ㅍㅅㅋㄱㄹㄱㅍㄹㅅㅇㅇㅇㄴ")) {
            newWord = "이브 프시케 그리고 푸른 수염의 아내(르세라핌)";
        } else if (currentWord.equals("ㅈㅈㅎㄴㅇㅇㄷㅇㅇㅎ")) {
            newWord = "주저하는 연인들을 위해(잔나비)";
        } else if (currentWord.equals("ㅋㅋ")) {
            newWord = "퀸카(여자아이들)";
        } else if (currentWord.equals("ㄱㄹㅇㅅ")) {
            newWord = "거리에서(성시경)";
        } else if (currentWord.equals("ㅈㅇㄹㄱㅎㅈ")) {
            newWord = "정이라고하자(BIG Naughty)";
        } else if (currentWord.equals("ㄱㄹㄷㅇㅅ")) {
            newWord = "그라데이션(10cm)";
        } else if (currentWord.equals("ㅍㅅㅌ")) {
            newWord = "폰서트(10cm)";
        } else if (currentWord.equals("ㄷㄲㅂㅂ")) {
            newWord = "도깨비불(에스파)";
        } else if (currentWord.equals("ㄴㅎ")) {
            newWord = "낙하(악동뮤지션)";
        } else if (currentWord.equals("ㅅㄱㅇㅈㅍㅅ")) {
            newWord = "사건의지평선(윤하)";
        } else if (currentWord.equals("ㅇㄴㅎㅇㅈㅇㅇ")) {
            newWord = "오늘헤어졌어요(윤하)";
        } else if (currentWord.equals("ㅅㅊㅇㅁㄱ")) {
            newWord = "신촌을못가(포스트맨)";
        } else if (currentWord.equals("ㅎㅍㅇㅈㄱㄷㅅㅇㄱ")) {
            newWord = "한 페이지가 될 수 있게(데이식스)";
        } else if (currentWord.equals("ㅇㄹㄴㅇㄹㅂ")) {
            newWord = "오랜날오랜밤(악동뮤지션)";
        } else if (currentWord.equals("ㄱㄴㅅㅌㅇ")) {
            newWord = "강남스타일(싸이)";
        } else if (currentWord.equals("ㅇㄹㄹ")) {
            newWord = "으르렁(EXO)";
        } else if (currentWord.equals("ㄷㅅㅁㄴㅅㄱ")) {
            newWord = "다시만나세계(소녀시대)";
        } else if (currentWord.equals("ㅇㅂㄹㅋㄷㅂㄹ")) {
            newWord = "아브라카다브라(브라운아이드걸스)";
        } else if (currentWord.equals("ㄴㄱㅈㅇㅈㄴㄱ")) {
            newWord = "내가제일잘나가(2NE1)";
        } else if (currentWord.equals("ㅆㄹㅆㄹ"))
            newWord = "Sorry,Sorry(슈퍼주니어)";
        else if (currentWord.equals("ㄸㄷㄸㄷ"))
            newWord = "뚜두뚜두(블랙핑크)";
        else if (currentWord.equals("ㅈㅇㄴ"))
            newWord = "좋은날(아이유)";
        else if (currentWord.equals("ㄴㅋㅇ"))
            newWord = "눈코입(태양)";
        else if (currentWord.equals("ㅍㅌㅅㅌㅂㅇㅂ"))
            newWord = "FANTASTIC BABY(빅뱅)";
        else if (currentWord.equals("ㅌㄹㅂㅁㅇㅋ"))
            newWord = "트러블메이커";
        else if (currentWord.equals("ㅅㄹㅇㅎㄷ"))
            newWord = "사랑을했다(아이콘)";




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

