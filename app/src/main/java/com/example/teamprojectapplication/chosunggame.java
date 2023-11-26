package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Random;

public class chosunggame extends AppCompatActivity {

    private ImageView checkButton;
    private ImageView nextButton;
    private TextView wordTextView,topicTextView;
    private Random random = new Random();
    private Object[][] word_data;
    private int currentIndex = 0;
    private HashSet<String> usedWords = new HashSet<>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosunggame);

        checkButton = findViewById(R.id.button8);
        nextButton = findViewById(R.id.button7);
        wordTextView = findViewById(R.id.textView7);
        topicTextView = findViewById(R.id.textView22);
        ImageView backButton = findViewById(R.id.backbutton_1);

        Intent intent = getIntent();
        String Topic = intent.getStringExtra("Topic");
        String json = "";
        try {
            InputStream is = getAssets().open("jsons/chosung.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);


            JSONArray filteredArray = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                if (Topic.equals(item.getString("topic"))) {
                    filteredArray.put(item);
                }
            }

            int length = filteredArray.length();

            word_data = new Object[length][2];

            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = filteredArray.getJSONObject(i);
                String Jsontopic = jsonObject.getString("topic");
                if (Jsontopic.equals(Topic)) {
                    word_data[i][0] = jsonObject.getString("quiz");
                    word_data[i][1] = jsonObject.getString("answer");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        shuffleSongDataArray();

        wordTextView.setText(String.valueOf(word_data[currentIndex][0]));
        topicTextView.setText("주제 : "+Topic);



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
                currentIndex++;
                if (currentIndex < word_data.length) {
                    wordTextView.setText(String.valueOf(word_data[currentIndex][0]));
                }
                if (currentIndex == word_data.length - 1) {
                    nextButton.setVisibility(View.INVISIBLE);
                    checkButton.setVisibility(View.INVISIBLE);
                    topicTextView.setVisibility(View.INVISIBLE);
                    wordTextView.setText("문제가 끝났어요!\n다음 업데이트를 \n기다려주세요!");
                }
            }
        });
    }

    private void changeWord() {
        wordTextView.setText(String.valueOf(word_data[currentIndex][1]));
    }

    private void shuffleSongDataArray() {
        List<Object[]> dataList = Arrays.asList(word_data);
        Collections.shuffle(dataList);
        dataList.toArray(word_data);
    }
}