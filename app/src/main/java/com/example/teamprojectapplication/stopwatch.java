package com.example.teamprojectapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.app.AppCompatActivity;

import kotlin.collections.SlidingWindowKt;

public class stopwatch extends AppCompatActivity {

    Animation translateLeft, translateRight;

    boolean isPageOpen = false;

    boolean test = true;
    private TextView timeTextView;
    private TextView totalTextView;
    private ImageView minusButton;
    private ImageView addButton;
    private ImageView startButton;
    private ImageView howtoplay;

    private ImageView backbutton;
    private TextView view1, view2;

    private boolean timerRunning = false;
    private long initialTimeInMillis = 0;
    private long startTime = 0;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);



        // UI 요소 초기화
        initializeUI();
        // 이벤트 핸들러 설정
        setupEventHandlers();

        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidinAnimationListener listener = new SlidinAnimationListener();
        translateRight.setAnimationListener(listener);
        translateLeft.setAnimationListener(listener);


    }

    private class SlidinAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                totalTextView.setVisibility(View.INVISIBLE);
                isPageOpen = false;
            }else{
                isPageOpen =true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


    private void initializeUI() {
        // 각 UI 요소를 레이아웃에서 찾아와 초기화
        timeTextView = findViewById(R.id.time);
        totalTextView = findViewById(R.id.total);
        minusButton = findViewById(R.id.btn_minus);
        addButton = findViewById(R.id.btn_add);
        view1 = findViewById(R.id.view_1);
        view2 = findViewById(R.id.view_2);
        startButton = findViewById(R.id.btn_strt);
        howtoplay=findViewById(R.id.howtoplay);
        ToggleButton toggleButton = findViewById(R.id.toggle);

        backbutton=findViewById(R.id.backbutton_1);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                howtoplay.setVisibility(View.INVISIBLE);
                backbutton.setVisibility(View.VISIBLE);
                toggleButton.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);


            }
        });




        // ToggleButton 찾아와서 상태 변경을 감지하는 리스너 설정

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // isChecked가 true이면 view_1을 보이게, false이면 숨기게 설정
                if (isChecked) {
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.INVISIBLE);
                    test=true;

                } else {
                    view1.setVisibility(View.INVISIBLE);
                    view2.setVisibility(View.VISIBLE);
                    test=false;
                }
            }
        });
    }

    private void setupEventHandlers() {
        // 시작 버튼 클릭 시 이벤트 처리
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning) {
                    // 타이머가 실행 중이면 멈추고 초기화
                    stopTimer();
                    view1.setVisibility(View.INVISIBLE);
                    view2.setVisibility(View.INVISIBLE);
                    initialTimeInMillis = 0; // 다시 시작할 때 초기화
                    updateCountDownText();
                } else {
                    // 타이머가 멈춰있을 때 시작
                    timerRunning = true;
                    startTimer();
                    startButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        // 화면 터치 이벤트 처리
        view1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (timerRunning) {
                    // 타이머가 실행 중이면 멈춤
                    stopTimer();
                    totalTextView.setVisibility(View.VISIBLE);
                    totalTextView.startAnimation(translateLeft);

                }
                return false;
            }
        });

        view2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (timerRunning) {
                    // 타이머가 실행 중이면 멈춤
                    stopTimer();
                    view2.setVisibility(View.INVISIBLE);
                    view1.setVisibility(View.VISIBLE);
                    totalTextView.setVisibility(View.VISIBLE);
                    totalTextView.startAnimation(translateLeft);
                }
                return false;
            }
        });

        // 마이너스 버튼 클릭 시 이벤트 처리
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerRunning) {
                    // 타이머가 실행 중이 아니면 설정 시간을 감소
                    if (initialTimeInMillis > 0) {
                        initialTimeInMillis -= 1000; // 1초(1000밀리초) 감소
                        updateCountDownText();
                    }
                }
            }
        });
        // 더하기 버튼 클릭 시 이벤트 처리
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!timerRunning) {
                    // 타이머가 실행 중이 아니면 설정 시간을 증가
                    initialTimeInMillis += 1000; // 1초(1000밀리초) 증가
                    updateCountDownText();
                }
            }
        });

        totalTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                totalTextView.startAnimation(translateRight);
                startButton.setVisibility(View.VISIBLE);
                if (test) {
                    view1.setVisibility(View.VISIBLE);
                    view2.setVisibility(View.INVISIBLE);

                } else {
                    view1.setVisibility(View.INVISIBLE);
                    view2.setVisibility(View.VISIBLE);
                }


                return false;
            }
        });
    }

    private void startTimer() {
        // 타이머 시작 시 현재 시간을 기준으로 경과 시간 계산
        startTime = System.nanoTime();

        // view1을 초기에 업데이트
        updateView1();

        // 주기적으로 실행되는 핸들러 설정
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timerRunning) {
                    // 경과된 시간을 업데이트하지 않고 설정된 시간만 표시
                    updateCountDownText();
                    updateView1(); // view1 업데이트
                    handler.postDelayed(this, 10); // 10밀리초마다 업데이트
                }
            }
        }, 10);
    }

    private void stopTimer() {
        // 타이머를 멈추고 결과를 표시
        timerRunning = false;
        showResult();
    }

    private void updateCountDownText() {
        // 남은 시간을 텍스트뷰에 업데이트
        double seconds = (double) initialTimeInMillis / 1000.0;
        timeTextView.setText(String.format("%.0f", seconds));
    }

    private void showResult() {
        // 현재까지 경과한 시간을 계산
        long currentTime = System.nanoTime();
        long elapsedTimeSinceStart = currentTime - startTime;

        // 나노초를 초로 변환하고 소수점 둘째 자리까지 표시
        double elapsedSeconds = (double) elapsedTimeSinceStart / 1_000_000_000.0;
        view1.setText(String.format("%.2f", elapsedSeconds));

        // 결과를 텍스트뷰에 표시
        double setSeconds = (double) initialTimeInMillis / 1000.0;
        double remainingTimeInSeconds = setSeconds - elapsedSeconds;

        String formattedSetSeconds = String.format("%.2f", setSeconds);
        String formattedElapsedSeconds = String.format("%.2f", elapsedSeconds);

        // 음수 여부에 따라 남은 시간 표시
        String formattedRemainingTime;
        if (remainingTimeInSeconds < 0) {
            formattedRemainingTime = "-" + String.format("%.2f", Math.abs(remainingTimeInSeconds));
        } else {
            formattedRemainingTime = String.format("%.2f", remainingTimeInSeconds);
        }

        // 결과 텍스트뷰에 표시
        totalTextView.setText(formattedRemainingTime + "초");
    }

    private void updateView1() {
        // startTime부터 현재까지 경과한 시간을 계산
        long elapsedTimeSinceStart = System.nanoTime() - startTime;

        // 나노초를 초로 변환하고 소수점 둘째 자리까지 표시
        double secondsElapsed = (double) elapsedTimeSinceStart / 1_000_000_000.0;
        view1.setText(String.format("%.2f", secondsElapsed));
    }


}
