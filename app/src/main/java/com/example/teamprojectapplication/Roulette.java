package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.TextView;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roulette extends AppCompatActivity {

    Animation translateLeft, translateRight;//애니메이션 효과


    private ConstraintLayout slideview, mainview, howtoplay9;
    boolean isPageOpen = false;

    private ImageView btnAdd, btnMinus, changebtn1, backbtn,mainbackbutton;

    private TextView tvCount, resultView;

    private ScrollView rootView;
    private int count = 0;

    List<WheelItem> wheelItems;
    LuckyWheel luckyWheel;
    String point;

    EditText ets [] = new EditText[6];
    int etss [] = {R.id.et1, R.id.et2, R.id.et3, R.id.et4, R.id.et5, R.id.et6};

    private class SlidinAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                slideview.setVisibility(View.INVISIBLE);
                mainview.setVisibility(View.VISIBLE);

                isPageOpen = false;
            }else{
                isPageOpen =true;
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);



        mainview = (ConstraintLayout) findViewById(R.id.mainview);
        slideview = (ConstraintLayout) findViewById(R.id.slide_view);
        //버튼 증가 감소
        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(count+"");
        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);
        changebtn1 = findViewById(R.id.btn_change1);
        backbtn = findViewById(R.id.backbtn);
        //변수 담기
        luckyWheel = findViewById(R.id.luck_wheel);

        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidinAnimationListener listener = new SlidinAnimationListener();
        translateRight.setAnimationListener(listener);
        translateLeft.setAnimationListener(listener);

        count = 2;
        tvCount.setText(String.valueOf(count));

        // onCreate() 메서드 내에 다음 코드 추가
        final View activityRootView = findViewById(R.id.rootview);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            int previousHeightDiff = -1;

            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = activityRootView.getRootView().getHeight();

                int heightDiff = screenHeight - r.bottom;

                if (previousHeightDiff != heightDiff) {
                    previousHeightDiff = heightDiff;

                    if (heightDiff < screenHeight * 0.15) {
                        // 키보드가 숨겨진 경우
                        activityRootView.scrollTo(0, 0); // 스크롤 뷰를 최상단으로 스크롤하는 예시 코드
                    } else {
                        // 키보드가 나타난 경우
                        // 필요한 경우 추가 작업 수행
                    }
                }
            }
        });




        for(int i = 0; i<6; i++){
            final int idx = i;
            ets[idx] = findViewById(etss[idx]);
        }

        howtoplay9 = (ConstraintLayout) findViewById(R.id.howtoplay9);
        mainbackbutton = (ImageView) findViewById(R.id.mainbackbtn);


        mainbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        howtoplay9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                howtoplay9.setVisibility(View.INVISIBLE);
            }
        });

        //점수판 데이터 생성
        generateWheelItems();

        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                int wheelIndex = Integer.parseInt(point) - 1; // 휠 렛 번호

                if (wheelIndex >= 0 && wheelIndex < 6) {
                    String textToShow = ets[wheelIndex].getText().toString(); // 휠 렛 번호에 해당하는 EditText의 텍스트 가져오기
                    resultView = findViewById(R.id.resultview);
                    resultView.setText(textToShow); // 휠 렛 번호에 해당하는 EditText의 텍스트를 결과뷰에 설정
                }
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count < 6)
                    count++;
                tvCount.setText(count+"");
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count > 2)
                    count--;
                tvCount.setText(count+"");
            }
        });

        backbtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                slideview.startAnimation(translateRight);
                return false;
            }
        });


        //시작 버튼
        ImageView start = findViewById(R.id.spin_btn);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                wheelItems = new ArrayList<>();

                Drawable d = getResources().getDrawable(R.drawable.ic_money, null);

                String color[] = {"#F44336", "#E91E63", "#9C27B0", "#3F51B5", "#1E88E5", "#009688"};

                Bitmap bitmap = drawableToBitmap(d);
                //tvCount 점수 정수로 파싱
                int repeatCount = Integer.parseInt(tvCount.getText().toString());

                for(int i = 0; i<repeatCount; i++){
//                    String editTextValue = ets[i].getText().toString();
                    String editTextValue = String.valueOf(i+1);
                    wheelItems.add(new WheelItem(Color.parseColor(color[i]), bitmap, editTextValue));
                }


                //점수판에 데이터 넣기
                luckyWheel.addWheelItems(wheelItems);

                //랜덤숫자 생성
                Random random = new Random();
                point = String.valueOf(random.nextInt(6)+1); //1 ~ 6
                luckyWheel.rotateWheelTo(Integer.parseInt(point));



            }
        });

        changebtn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                slideview.setVisibility(View.VISIBLE);
                slideview.startAnimation(translateLeft);
                return false;
            }
        });

    }//onCreate();

    //데이터 생성
    private void generateWheelItems(){

        wheelItems = new ArrayList<>();

        Drawable d = getResources().getDrawable(R.drawable.ic_money, null);

        String color[] = {"#F44336", "#E91E63", "#9C27B0", "#3F51B5", "#1E88E5", "#009688"};

        Bitmap bitmap = drawableToBitmap(d);
        wheelItems.add(new WheelItem(Color.parseColor("#F44336"), bitmap, getResources().getString(R.string.peace1)));
        wheelItems.add(new WheelItem(Color.parseColor("#E91E63"), bitmap, getResources().getString(R.string.peace2)));
        wheelItems.add(new WheelItem(Color.parseColor("#9C27B0"), bitmap, getResources().getString(R.string.peace3)));
        wheelItems.add(new WheelItem(Color.parseColor("#3F51B5"), bitmap, getResources().getString(R.string.peace4)));
        wheelItems.add(new WheelItem(Color.parseColor("#1E88E5"), bitmap, getResources().getString(R.string.peace5)));
        wheelItems.add(new WheelItem(Color.parseColor("#009688"), bitmap, getResources().getString(R.string.peace6)));


        //점수판에 데이터 넣기
        luckyWheel.addWheelItems(wheelItems);
    }//generateWheelItems

    public static  Bitmap drawableToBitmap(Drawable drawable){

        if(drawable instanceof BitmapDrawable){

            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap =  Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);

        drawable.setBounds(0, 0,canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}//MainActivity