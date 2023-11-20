package com.example.teamprojectapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class emojigame extends AppCompatActivity {

    private ImageView imageView;
    private int[] imageArray = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,R.drawable.image5, R.drawable.image6,
            R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,R.drawable.image11,R.drawable.image12,R.drawable.image13,R.drawable.image14,
            R.drawable.image15,R.drawable.image16,R.drawable.image17,
            R.drawable.image18,R.drawable.image19,R.drawable.image20,R.drawable.image21,R.drawable.image22,R.drawable.image23,R.drawable.image24,R.drawable.image25,
            R.drawable.image26,R.drawable.image27,R.drawable.image28,R.drawable.image29,R.drawable.image30,R.drawable.image31,R.drawable.image32,R.drawable.image33,};
    private String[] imageDescriptions = {"EXID-위아래", "Kill This Love -블랙핑크", "거북이-빙고","눈코입-태양", "Lovesick Girls-블랙핑크","MIC Drop-BTS"
            ,"백지영-사랑안해","빅뱅-봄여름가을겨울","빅뱅-뱅뱅뱅","빅뱅-판타스틱베이비","다비치-사랑과전쟁","세븐틴-손오공","여자친구-시간을달려서","싹스리-다시여기바닷가",
            "씨엔블루-외톨이야","아이유-strawberry moon","아이유-좋은날","악동뮤지션-후라이의 꿈","먼데이 키즈-발자국","에스파-Savage","에이핑크-No No No","에이핑크-Mr chu","엑소-으르렁","2PM-우리집",
            "에스파-도깨비 불","BTS-피땀눈물", "하이키-건물 사이에 피어난 장미","리쌍-헤어지지 못하는 여자\n떠나가지 못하는 남자","에일리-첫눈 처럼 \n너에게 가겠다","아이브-러브 다이브","적재-별보러갈래",
            "아이유-잔소리","볼빨간사춘기-우주를 줄게"};

    private String[] hints = {"힌트:가사,춤", "힌트:검정분홍", "힌트:한 줄 OO ", "힌트:sun","힌트:검정분홍","힌트:BTS"
            ,"힌트:아재개그","힌트:4계절","힌트:총총총","힌트-빅뱅","힌트:다비치","힌트:마치 된 것 같아 000","힌트:여자친구","힌트:유재석","힌트:정용화","힌트:아이유",
            "힌트:아이유","힌트:이찬혁","힌트:월요일","힌트:에스파","힌트:에이핑크","힌트:에이핑크","힌트:엑소","힌트:2PM","힌트:에스파","힌트:BTS","힌트:하이키","힌트:리쌍","힌트:에일리","힌트:아이브","힌트:적재"
            ,"힌트:임슬옹","힌트:안지영"};
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