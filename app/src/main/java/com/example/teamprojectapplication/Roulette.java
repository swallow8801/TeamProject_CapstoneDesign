package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Roulette extends AppCompatActivity {

    private Button btnAdd, btnMinus , btnBack;
    private TextView tvCount;
    private int count = 0;

    List<WheelItem> wheelItems;
    LuckyWheel luckyWheel;
    String point;

    EditText ets [] = new EditText[6];
    int etss [] = {R.id.et1, R.id.et2, R.id.et3, R.id.et4, R.id.et5, R.id.et6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roulette);
        //버튼 증가 감소
        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(count+"");
        btnAdd = findViewById(R.id.btn_add);
        btnMinus = findViewById(R.id.btn_minus);
        btnBack = findViewById(R.id.button356);

        //변수 담기
        luckyWheel = findViewById(R.id.luck_wheel);

        for(int i = 0; i<6; i++){
            final int idx = i;
            ets[idx] = findViewById(etss[idx]);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //점수판 데이터 생성
        generateWheelItems();

        luckyWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
            @Override
            public void onReachTarget() {
                //아이템 변수에 담기
                WheelItem wheelItem = wheelItems.get(Integer.parseInt(point)-1);
                //아이템 텍스트 변수에 담기
                String money = wheelItem.text;
                //메시지
                Toast.makeText(Roulette.this,"당첨", Toast.LENGTH_SHORT).show();
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

        //시작 버튼
        Button start = findViewById(R.id.spin_btn);
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
                    String editTextValue = ets[i].getText().toString();
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
    }//onCreate();
    //데이터 생성
    private void generateWheelItems(){

        wheelItems = new ArrayList<>();

        Drawable d = getResources().getDrawable(R.drawable.ic_money, null);

        String color[] = {"#F44336", "#E91E63", "#9C27B0", "#3F51B5", "#1E88E5", "#009688"};

        Bitmap bitmap = drawableToBitmap(d);
//        wheelItems.add(new WheelItem(Color.parseColor("#F44336"), bitmap, getResources().getString(R.string.peace1)));
//        wheelItems.add(new WheelItem(Color.parseColor("#E91E63"), bitmap, getResources().getString(R.string.peace2)));
//        wheelItems.add(new WheelItem(Color.parseColor("#9C27B0"), bitmap, getResources().getString(R.string.peace3)));
//        wheelItems.add(new WheelItem(Color.parseColor("#3F51B5"), bitmap, getResources().getString(R.string.peace4)));
//        wheelItems.add(new WheelItem(Color.parseColor("#1E88E5"), bitmap, getResources().getString(R.string.peace5)));
//        wheelItems.add(new WheelItem(Color.parseColor("#009688"), bitmap, getResources().getString(R.string.peace6)));
//        wheelItems.add(new WheelItem(Color.parseColor("#009688"), bitmap, "7"));



        for(int i = 0; i<6; i++){
            wheelItems.add(new WheelItem(Color.parseColor(color[i]), bitmap, getResources().getString(R.string.peace1)));
        }


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