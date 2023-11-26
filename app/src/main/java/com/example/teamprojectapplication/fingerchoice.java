package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class fingerchoice extends AppCompatActivity {

    private static final int DELAY_MILLIS = 5000; // 5초(밀리초)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerchoice);


        ImageView backButton = findViewById(R.id.backbutton_1);
        FrameLayout touchView2 = findViewById(R.id.touchView2);
        MultiTouchView multiTouchView = new MultiTouchView(this);
        touchView2.addView(multiTouchView);


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); // 뒤로 가기 동작 수행
            }
        });

        // 5초 뒤에 터치 중인 포인트 중 하나를 랜덤하게 선택하여 외부 원 생성
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            multiTouchView.addOuterCircle();
        }, DELAY_MILLIS);
    }

    public class MultiTouchView extends View {

        private Map<Integer, Point> touchPoints;
        private Map<Integer, Paint> paintMap;
        private Map<Integer, OuterCircle> outerCircles;

        public MultiTouchView(Context context) {
            super(context);
            init();
        }

        public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public MultiTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            touchPoints = new HashMap<>();
            paintMap = new HashMap<>();
            outerCircles = new HashMap<>();
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // 내부 원 그리기
            for (Map.Entry<Integer, Point> entry : touchPoints.entrySet()) {
                int id = entry.getKey();
                Point point = entry.getValue();
                Paint paint = paintMap.get(id);

                if (paint != null) {
                    canvas.drawCircle(point.x, point.y, 120, paint);
                }
            }

            // 외부 원 그리기
            for (Map.Entry<Integer, OuterCircle> entry : outerCircles.entrySet()) {
                int id = entry.getKey();
                OuterCircle outerCircle = entry.getValue();

                if (outerCircle != null) {
                    canvas.drawCircle(outerCircle.x, outerCircle.y, outerCircle.radius, outerCircle.paint);
                }
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (outerCircles.isEmpty()) { // 외부 원이 없을 때만 터치 이벤트 처리
                int action = event.getActionMasked();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        int pointerIndex = event.getActionIndex();
                        int pointerId = event.getPointerId(pointerIndex);
                        float x = event.getX(pointerIndex);
                        float y = event.getY(pointerIndex);

                        Paint paint = new Paint();
                        paint.setColor(generateRandomColor());
                        paint.setAntiAlias(true);

                        paintMap.put(pointerId, paint);
                        touchPoints.put(pointerId, new Point(x, y));
                        invalidate();  // 화면 갱신 요청
                        break;

                    case MotionEvent.ACTION_MOVE:
                        for (int i = 0; i < event.getPointerCount(); i++) {
                            int id = event.getPointerId(i);
                            float moveX = event.getX(i);
                            float moveY = event.getY(i);
                            touchPoints.get(id).set(moveX, moveY);
                        }
                        invalidate();  // 화면 갱신 요청
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        int pointerIndexUp = event.getActionIndex();
                        int pointerIdUp = event.getPointerId(pointerIndexUp);
                        touchPoints.remove(pointerIdUp);
                        paintMap.remove(pointerIdUp);
                        invalidate();  // 화면 갱신 요청
                        break;
                }

                return true;
            }

            return false; // 외부 원이 있을 경우 터치 이벤트 무시
        }
        // 랜덤으로 하나의 터치를 제거하는 메서드
        public void addOuterCircle() {
            if (!touchPoints.isEmpty()) {
                Object[] keys = touchPoints.keySet().toArray();
                Object randomKey = keys[new Random().nextInt(keys.length)];
                int pointerId = (int) randomKey;

                Point point = touchPoints.get(pointerId);
                Paint paint = paintMap.get(pointerId);

                // 외부 원을 추가
                Paint outerPaint = new Paint(paint);
                outerPaint.setStyle(Paint.Style.STROKE);
                outerPaint.setStrokeWidth(20); // 외부 원의 두께 설정
                outerCircles.put(pointerId, new OuterCircle(point.x, point.y, outerPaint));

                invalidate(); // 화면 갱신 요청
            }
        }

        private int generateRandomColor() {
            Random random = new Random();
            return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }
    }

    private static class Point {
        float x, y;

        Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        void set(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    private static class OuterCircle {
        float x, y, radius;
        Paint paint;

        OuterCircle(float x, float y, Paint paint) {
            this.x = x;
            this.y = y;
            this.radius = 150; // 외부 원의 반지름
            this.paint = paint;
        }
    }
}