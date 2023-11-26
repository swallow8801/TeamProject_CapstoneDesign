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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
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

    private Object[][] images ;
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

        String json = "";
        try {
            InputStream is = getAssets().open("jsons/objectimage.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();

            images = new Object[length][2];

            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String imageName = jsonObject.getString("imageName");
                int imageResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());

                images[i][0]=imageResourceId;
                images[i][1]=jsonObject.getString("imageTitle");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

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

        imageView.setImageResource((int) images[index][0]);
        titleTextView.setText(String.valueOf(images[index][1]));
    }
}