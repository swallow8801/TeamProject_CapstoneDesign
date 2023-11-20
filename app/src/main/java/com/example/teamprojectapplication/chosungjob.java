package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashSet;

import android.os.Bundle;
import java.util.Random;

public class chosungjob extends AppCompatActivity {

    private ImageView checkButton;
    private ImageView nextButton;

    private TextView wordTextView;
    private Random random = new Random();

    private String[] possibleWords = {"ㅇㅅ","ㄱㅎㅅ","ㅅㅂㄱ","ㅂㅎㅅ","ㄱㅊㄱ","ㄷㅈㅇㄴ","ㅇㅎㅇ","ㄱㅅㅇㄱㅈㅅㅈ","ㄱㅈ","ㅇㄱㅇ","ㅇㅌㅌㅇㄴ","ㅇㄹㄱㄱㄱㅅㅈ","ㄱㅎㄱㅅ","ㅇㅎㄱㅇㄷ","ㅅㅇㅅ","ㅈㅊㅇ",
            "ㅂㄷㅅㅈㄱㅇ","ㅂㅌㄷ","ㅍㅌㄴㅅㅌㄹㅇㄴ","ㅁㄹㅊㄹㅅ","ㅁㅇㅅ","ㅅㄹㅅㄷㅅ","ㅅㅎㅂㅈㅅ","ㅅㅁㄱㅈ","ㅊㄱㅅㅅ","ㅇㅇㄷㅁㅁㅇㅅ","ㅇㄴㅇㅅ","ㅂㄹㅅㅌ",
            "ㅇㅇㅍㄹㄷㅅ","ㅂㅇㅇㄹㄴㅅㅌ","ㅅㅇㄱㅎㅈ","ㅎㄱㅇㅈㄴㅇ","ㅇㅈㄹㄱㅎㅈ"};



    private HashSet<String> usedWords = new HashSet<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosungjob);

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

        if (currentWord.equals("ㅇㅅ")) {
            newWord = "의사";
        } else if (currentWord.equals("ㄱㅎㅅ")) {
            newWord = "간호사";
        } else if (currentWord.equals("ㅅㅂㄱ")) {
            newWord ="소방관";
        } else if (currentWord.equals("ㄱㅊㄱ")) {
            newWord = "경찰관";
        } else if (currentWord.equals("ㅂㅎㅅ")) {
            newWord = "변호사";
        } else if (currentWord.equals("ㄷㅈㅇㄴ")) {
            newWord = "디자이너";
        } else if (currentWord.equals("ㅇㅎㅇ")) {
            newWord = "은행원";
        } else if (currentWord.equals("ㄱㅅㅇㄱㅈㅅㅈ")) {
            newWord = "건설 업계 종사자";
        } else if (currentWord.equals("ㄱㅈ")) {
            newWord = "기자";
        } else if (currentWord.equals("ㅊㄱㅅㅅ")) {
            newWord = "축구선수";
        } else if (currentWord.equals("ㅇㄱㅇ")) {
            newWord = "연구원";
        } else if (currentWord.equals("ㅇㅌㅌㅇㄴ")) {
            newWord = "엔터테이너";
        } else if (currentWord.equals("ㅇㄹㄱㄱㄱㅅㅈ")) {
            newWord = "의료기기 기술자";
        } else if (currentWord.equals("ㄱㅎㄱㅅ")) {
            newWord = "공학 기사";
        } else if (currentWord.equals("ㅇㅎㄱㅇㄷ")) {
            newWord = "여행 가이드";
        } else if (currentWord.equals("ㅅㅇㅅ")) {
            newWord = "수의사";
        } else if (currentWord.equals("ㅈㅊㅇ")) {
            newWord = "정치인";
        } else if (currentWord.equals("ㅂㄷㅅㅈㄱㅇ")) {
            newWord = "부동산 중개인";
        } else if (currentWord.equals("ㅇㅈㄹㄱㅎㅈ")) {
            newWord = "원자력 공학자";
        } else if (currentWord.equals("ㅂㅌㄷ")) {
            newWord = "바텐더";
        } else if (currentWord.equals("ㅍㅌㄴㅅㅌㄹㅇㄴ")) {
            newWord = "피트니스 트레이너";
        } else if (currentWord.equals("ㅁㄹㅊㄹㅅ")) {
            newWord = "물리치료사";
        } else if (currentWord.equals("ㅁㅇㅅ"))
            newWord = "미용사";
        else if (currentWord.equals("ㅅㄹㅅㄷㅅ"))
            newWord = "심리상담사";
        else if (currentWord.equals("ㅅㅎㅂㅈㅅ"))
            newWord = "사회복지사";
        else if (currentWord.equals("ㅅㅁㄱㅈ"))
            newWord = "신문 기자";
        else if (currentWord.equals("ㅇㅇㄷㅁㅁㅇㅅ"))
            newWord = "애완동물 미용사";
        else if (currentWord.equals("ㅇㄴㅇㅅ"))
            newWord = "아나운서 ";

        else if (currentWord.equals("ㅇㅇㅍㄹㄷㅅ"))
            newWord = "음악 프로듀서";
        else if (currentWord.equals("ㅂㅇㅇㄹㄴㅅㅌ"))
            newWord = "바이올리니스트";
        else if (currentWord.equals("ㅎㄱㅇㅈㄴㅇ"))
            newWord = "환경 엔지니어 ";




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

