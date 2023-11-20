package com.example.teamprojectapplication;
import androidx.appcompat.app.AppCompatActivity;
//import android.content.intent;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class bottlespiner extends AppCompatActivity {
    ImageView iv_turning;
    ImageView howtoplay;
    float startDegree = 0f;
    float endDegree = 0f;
    ImageView btn_back;

    TextView who;

    private boolean isTouched=true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottlespiner);
        iv_turning = (ImageView)findViewById(R.id.beer);
        btn_back=(ImageView)findViewById(R.id.btn_back);
        howtoplay=findViewById(R.id.howtoplay);
        who = findViewById(R.id.textView);
        ImageView imageView14 = findViewById(R.id.imageView12);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howtoplay.setVisibility(View.INVISIBLE);
                btn_back.setVisibility(View.VISIBLE);
                iv_turning.setVisibility(View.VISIBLE);
                who.setVisibility(View.VISIBLE);
                imageView14.setVisibility(View.INVISIBLE);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    public void rotate(View v) {
        // ---------- 회전각도 설정 ----------
        startDegree = endDegree;    // 이전 정지 각도를 시작 각도로 설정
        Random rand = new Random(); // 랜덤 객체 생성
        int degree_rand = rand.nextInt(360);    // 0~359 사이의 정수 추출
        endDegree = startDegree + 360 * 5 + degree_rand;  // 회전 종료각도 설정(초기 각도에서 세바퀴 돌고 0~359 난수만큼 회전)

        // ---------- 애니메이션 실행 ----------
        // 애니메이션 이미지에 대해 초기 각도에서 회전종료 각도까지 회전하는 애니메이션 객체 생성
        ObjectAnimator object = ObjectAnimator.ofFloat(iv_turning, "rotation", startDegree, endDegree);

        object.setInterpolator(new AccelerateDecelerateInterpolator()); // 애니메이션 속력 설정
        object.setDuration(6000);   // 애니메이션 시간(5초)
        object.start();   // 애니메이션 시작
    }
}

//public class MainActivity extends AppCompatActivity {

// @Override
// protected void onCreate(Bundle savedInstanceState) {
///    super.onCreate(savedInstanceState);
//     setContentView(R.layout.activity_main);

//      Button button = findViewById(R.id.btn_help);

//      button.setOnClickListener(new View.OnClickListener() {
//        @Override
//       public void onClick(View view) {
//화면전환
//       Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//         startActivity(intent);
//         }
//    });
//   }

