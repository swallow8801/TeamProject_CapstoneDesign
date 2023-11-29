package com.example.teamprojectapplication;

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

    private static final long LONG_PRESS_DURATION = 5000; // 5초(밀리초)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerchoice);

        ImageView backButton = findViewById(R.id.backbutton_1);
        ImageView howtoplay = findViewById(R.id.howtoplay);
        View touchView1 = findViewById(R.id.touchView);
        FrameLayout touchView2 = findViewById(R.id.touchView2);
        MultiTouchView multiTouchView = new MultiTouchView(this);
        touchView2.addView(multiTouchView);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howtoplay.setVisibility(View.INVISIBLE);
                backButton.setVisibility(View.VISIBLE);
                touchView1.setVisibility(View.VISIBLE);
                touchView2.setVisibility(View.VISIBLE);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); // 뒤로 가기 동작 수행
            }
        });


        // 한 번 터치한 시점에서 꾹(길게) 5초 동안 터치한 경우에만 외부 원 생성
        multiTouchView.setOnLongPressListener(() -> {
            multiTouchView.addOuterCircle();
        });
    }

    public class MultiTouchView extends View {

        private Map<Integer, Point> touchPoints;
        private Map<Integer, Paint> paintMap;
        private Map<Integer, OuterCircle> outerCircles;

        private boolean isLongPress = false;
        private float longPressX, longPressY;
        private long longPressStartTime; // 꾹(길게) 터치를 시작한 시간을 저장하는 변수
        private OnLongPressListener onLongPressListener;
        private int activePointerId = MotionEvent.INVALID_POINTER_ID;
        private boolean isTouchEnabled = true; // 터치 가능 여부를 나타내는 변수 추가

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
            if (isTouchEnabled) {
                int action = event.getActionMasked();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        handleActionDown(event);
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        handleActionPointerDown(event);
                        break;

                    case MotionEvent.ACTION_MOVE:
                        handleActionMove(event);
                        break;

                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                        handleActionUp(event);
                        break;
                }

                return true;
            }

            return false;
        }

        private void handleActionDown(MotionEvent event) {
            int pointerIndex = event.getActionIndex();
            int pointerId = event.getPointerId(pointerIndex);

            activePointerId = pointerId;

            Paint paint = new Paint();
            paint.setColor(generateRandomColor());
            paint.setAntiAlias(true);

            paintMap.put(pointerId, paint);
            touchPoints.put(pointerId, new Point(event.getX(pointerIndex), event.getY(pointerIndex)));

            invalidate();
            startLongPressTimer(event.getX(pointerIndex), event.getY(pointerIndex));
        }

        private void handleActionPointerDown(MotionEvent event) {
            int pointerIndex = event.getActionIndex();
            int pointerId = event.getPointerId(pointerIndex);

            Paint paint = new Paint();
            paint.setColor(generateRandomColor());
            paint.setAntiAlias(true);

            paintMap.put(pointerId, paint);
            touchPoints.put(pointerId, new Point(event.getX(pointerIndex), event.getY(pointerIndex)));

            invalidate();
        }

        private void handleActionMove(MotionEvent event) {
            for (int i = 0; i < event.getPointerCount(); i++) {
                int id = event.getPointerId(i);
                if (id == activePointerId) {
                    float moveX = event.getX(i);
                    float moveY = event.getY(i);
                    touchPoints.get(id).set(moveX, moveY);
                }
            }
            invalidate();
        }

        private void handleActionUp(MotionEvent event) {
            int pointerIndexUp = event.getActionIndex();
            int pointerIdUp = event.getPointerId(pointerIndexUp);
            touchPoints.remove(pointerIdUp);
            paintMap.remove(pointerIdUp);

            if (pointerIdUp == activePointerId) {
                stopLongPressTimer();
                activePointerId = MotionEvent.INVALID_POINTER_ID;
            }

            invalidate();
        }

        private void startLongPressTimer(float x, float y) {
            isLongPress = false;
            longPressX = x;
            longPressY = y;
            longPressStartTime = System.currentTimeMillis(); // 꾹(길게) 터치 시작 시간 저장

            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                if (isLongPress) {
                    // 이미 외부 원이 생성된 경우 무시
                    return;
                }

                // 꾹(길게) 터치한 시점에서 5초 동안 터치한 경우에만
                // 꾹(길게) 터치한 시점에서 5초 동안 터치한 경우에만 콜백 호출
                if (onLongPressListener != null && isLongPressForDuration()) {
                    onLongPressListener.onLongPress();
                }
            }, LONG_PRESS_DURATION);
        }

        private void stopLongPressTimer() {
            isLongPress = false;
        }

        private boolean isLongPressForDuration() {
            long currentTime = System.currentTimeMillis();
            return (currentTime - longPressStartTime) >= LONG_PRESS_DURATION;
        }

        public void setOnLongPressListener(OnLongPressListener listener) {
            this.onLongPressListener = listener;
        }

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

                // 외부 원이 생성
                // 외부 원이 생성되면 터치 비활성화
                isTouchEnabled = false;

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

    public interface OnLongPressListener {
        void onLongPress();
    }
}