package com.example.teamprojectapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;;

import java.util.Random;

public class emojigame extends AppCompatActivity {

    private ImageView imageView;
    private int[] imageArray;
    private String[] imageDescriptions;
    private String[] hints;
    private ImageView checkButton;
    private ImageView nextButton;
    private ImageView hintButton;
    private TextView textView;
    private TextView hintTextView;
    private int previousImageIndex = -1; // 이전 이미지의 인덱스를 저장
    private int currentIndex = 0;
    private int[] shuffledIndices;
    private int shuffledIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emojigame);

        imageView = findViewById(R.id.imageView8);
        checkButton = findViewById(R.id.button8);
        nextButton = findViewById(R.id.button7);
        hintButton = findViewById(R.id.imageView18);  // 힌트 버튼 추가
        textView = findViewById(R.id.textView);
        hintTextView = findViewById(R.id.hintTextView);

        ImageView backbutton = findViewById(R.id.backbutton_1);
        ImageView howtoplay= findViewById(R.id.howtoplay);

        String json = "";
        try {
            InputStream is = getAssets().open("jsons/emoji.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            int length = jsonArray.length();

            imageArray = new int[length];
            imageDescriptions = new String[length];
            hints = new String[length];

            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String imageName = jsonObject.getString("imageName");
                int imageResourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());

                imageArray[i] = imageResourceId;
                imageDescriptions[i] = jsonObject.getString("description");
                hints[i] = jsonObject.getString("hint");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextButton.setVisibility(View.VISIBLE);
                checkButton.setVisibility(View.VISIBLE);
                hintButton.setVisibility(View.VISIBLE);
                backbutton.setVisibility(View.VISIBLE);
                howtoplay.setVisibility(View.INVISIBLE);
                setRandomImage();
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        // "다음문제" 버튼을 클릭할 때마다 랜덤 이미지 설정
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setRandomImage();
            }
        });

        // "정답확인" 버튼을 클릭할 때 이미지 설명 표시
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showImageDescription();
            }
        });

        // "힌트" 버튼을 클릭할 때 힌트 표시
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHint();
            }
        });
    }

    private void setRandomImage() {
        if (currentIndex < imageArray.length) {
            if (shuffledIndices == null || shuffledIndex >= shuffledIndices.length) {
                // shuffledIndices가 null인 경우 또는 끝에 도달한 경우 인덱스를 다시 섞습니다.
                shuffledIndices = shuffleArray(imageArray.length);
                shuffledIndex = 0;
            }

            // 섞인 배열에서 다음 랜덤 인덱스를 가져옵니다.
            int randomIndex = shuffledIndices[shuffledIndex];

            imageView.setImageResource(imageArray[randomIndex]);

            // 현재 이미지의 인덱스를 저장
            previousImageIndex = randomIndex;

            // 이미지에 대한 설명 초기화
            textView.setText("");

            // 힌트 텍스트뷰 초기화
            hintTextView.setText("");

            // 현재 이미지 인덱스 증가
            currentIndex++;

            // 섞인 인덱스를 증가시킵니다.
            shuffledIndex++;
        } else {
            // 이미지가 모두 사용되었을 때
            imageView.setVisibility(View.INVISIBLE); // 이미지뷰 숨기기
            hintButton.setVisibility(View.INVISIBLE);
            hintTextView.setText("문제가 끝났어요");
            nextButton.setVisibility(View.INVISIBLE);
            checkButton.setVisibility(View.INVISIBLE);
            textView.setText("다음 업데이트를\n기다려주세요!");
        }
    }

    private int[] shuffleArray(int length) {
        int[] indices = new int[length];
        for (int i = 0; i < length; i++) {
            indices[i] = i;
        }

        // Fisher-Yates 섞기 알고리즘
        Random rand = new Random();
        for (int i = length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        return indices;
    }



    private void showImageDescription() {
        // 현재 이미지의 설명을 가져와서 텍스트뷰에 설정
        int selectedImageIndex = getCurrentImageIndex();
        if (selectedImageIndex != -1) {
            textView.setText(imageDescriptions[selectedImageIndex]);
        }
    }

    private void showHint() {
        // 현재 이미지의 힌트를 가져와서 힌트 텍스트뷰에 설정
        int selectedImageIndex = getCurrentImageIndex();
        if (selectedImageIndex != -1) {
            hintTextView.setText(hints[selectedImageIndex]);
        }
    }

    private int getCurrentImageIndex() {
        // 현재 ImageView에 설정된 이미지의 인덱스를 찾기
        for (int i = 0; i < imageArray.length; i++) {
            if (imageView.getDrawable() != null && imageView.getDrawable().getConstantState().equals(getResources().getDrawable(imageArray[i]).getConstantState())) {
                return i;
            }
        }
        return -1;
    }
}