package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class introsong extends AppCompatActivity {
    private Object[][] song_data_01 = {
            {R.raw.songstart001, R.raw.song001, "내가 제일 잘나가 - 2NE1"},
            {R.raw.songstart002, R.raw.song002, "Fantastic Baby - 빅뱅"},
            {R.raw.songstart003, R.raw.song003, "나혼자 - 씨쓰타"},
            {R.raw.songstart004, R.raw.song004, "Roly Poly - 티아라"},
            {R.raw.songstart005, R.raw.song005, "Offically Missing You - 긱스"},
            {R.raw.songstart006, R.raw.song006, "거북이 - 다비치"},
            {R.raw.songstart007, R.raw.song007, "TV를 껐네 - 리쌍"},
            {R.raw.songstart008, R.raw.song008, "그땐 그땐 그땐 - 슈프림팀"},
            {R.raw.songstart009, R.raw.song009, "너랑나 - 아이유"},
            {R.raw.songstart010, R.raw.song010, "잔소리 - 아이유,임슬옹"},
            {R.raw.songstart011, R.raw.song011, "삐딱하게 - GD"},
            {R.raw.songstart012, R.raw.song012, "으르렁 - EXO"},
            {R.raw.songstart013, R.raw.song013, "뚜두뚜두 - 블랙핑크"},
            {R.raw.songstart014, R.raw.song014, "DNA - BTS"},
            {R.raw.songstart015, R.raw.song015, "바람기억 - 나얼"},
            {R.raw.songstart016, R.raw.song016, "너의 모든 순간 - 성시경"},
            {R.raw.songstart017, R.raw.song017, "선물 - 멜로망스"},
            {R.raw.songstart018, R.raw.song018, "야생화 - 박효신"},
            {R.raw.songstart019, R.raw.song019, "우주를 줄게 - 볼빨간 사춘기"},
            {R.raw.songstart020, R.raw.song020, "좋니 - 윤종신"}
    };

    private Object[][] song_data_02 = {
            {R.raw.songstart101, R.raw.song101, "Adele - Rolling in the Deep"},
            {R.raw.songstart102, R.raw.song102, "Anne Marie - 2002"},
            {R.raw.songstart103, R.raw.song103, "Charlie Puth - Dangerously"},
            {R.raw.songstart104, R.raw.song104, "Ed Sheeran - Shape Of You"},
            {R.raw.songstart105, R.raw.song105, "Justin Bieber - Peaches "},
            {R.raw.songstart106, R.raw.song106, "Maroon 5 - Memories "},
            {R.raw.songstart107, R.raw.song107, "Sam Smith - I'm Not The Only One"},
            {R.raw.songstart108, R.raw.song108, "The Kid LAROI, Justin Bieber - Stay"},
            {R.raw.songstart109, R.raw.song109, "Tones and I - Dance Monkey "},
            {R.raw.songstart110, R.raw.song110, "Olivia Rodrigo - vampire"}
    };

    private Object[][] song_data;
    private int currentIndex = 0;
    private MediaPlayer mediaPlayer;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introsong);

        // TextView 및 Button 초기화
        textView = findViewById(R.id.textView);
        ImageView Backbutton=findViewById(R.id.backbutton_1);

        Backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageView button1 = findViewById(R.id.button1);
        ImageView button2 = findViewById(R.id.button2);
        ImageView button3 = findViewById(R.id.button3);
        ImageView button4 = findViewById(R.id.button4);
        TextView textView2 = findViewById(R.id.textView2);
        TextView textView20 = findViewById(R.id.textView20);

        Intent intent = getIntent();
        String Topic = intent.getStringExtra("Topic");
        switch (Topic){
            case "2010년대":
                song_data=song_data_01;
                textView2.setText(Topic);
                break;
            case "팝송":
                song_data=song_data_02;
                textView2.setText(Topic);
                break;
            default:
                song_data=song_data_01;
                break;
        }

        // 데이터 배열을 무작위로 섞기
        shuffleSongDataArray();



        // MediaPlayer 초기화
        mediaPlayer = new MediaPlayer();

        // 앱 시작 시 초기 데이터 설정
        textView.setText(String.valueOf(song_data[currentIndex][2]));
        textView.setVisibility(View.INVISIBLE);

        // 버튼1 클릭 시
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView20.setText("1초 재생중");
                // 버튼을 클릭했을 때
                textView20.setVisibility(View.VISIBLE);
                button1.setVisibility(View.INVISIBLE);

                // 1초 뒤에 다시 INVISIBLE로 변경
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView20.setVisibility(View.INVISIBLE);
                        button1.setVisibility(View.VISIBLE);
                    }
                }, 1000);
                playMusic(currentIndex, 0, 1000);
            }
        });

        // 버튼2 클릭 시
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView20.setText("3초 재생중");
                // 버튼을 클릭했을 때
                textView20.setVisibility(View.VISIBLE);
                button2.setVisibility(View.INVISIBLE);

                // 1초 뒤에 다시 INVISIBLE로 변경
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView20.setVisibility(View.INVISIBLE);
                        button2.setVisibility(View.VISIBLE);
                    }
                }, 3000);
                playMusic(currentIndex, 0, 3000);
            }
        });

        // 버튼3 클릭 시
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playMusic(currentIndex, 1, 4500);
                textView.setVisibility(View.VISIBLE);
                button3.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        button3.setVisibility(View.VISIBLE);
                    }
                }, 4500);
            }
        });

        // 버튼4 클릭 시
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 인덱스를 1 증가시키고 TextView 업데이트
                currentIndex++;
                if (currentIndex < song_data.length) {
                    textView.setText(String.valueOf(song_data[currentIndex][2]));
                }

                // currentIndex가 song_data의 크기와 같아지면 버튼4를 INVISIBLE로 설정
                if (currentIndex == song_data.length - 1) {
                    button4.setVisibility(View.INVISIBLE);
                }

                textView.setVisibility(View.INVISIBLE);
            }
        });
    }

    // 데이터 배열을 무작위로 섞는 메서드
    private void shuffleSongDataArray() {
        List<Object[]> dataList = Arrays.asList(song_data);
        Collections.shuffle(dataList);
        dataList.toArray(song_data);
    }

    // 음악을 재생하는 메서드
    private void playMusic(int songIndex, int selectIndex, int duration) {
        try {
            // 현재 MediaPlayer 초기화
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getResources().openRawResourceFd((int) song_data[songIndex][selectIndex]));
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
            mediaPlayer.start();

            // 일정 시간 후 재생 중지
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.release();
                }
            });

            // 주어진 시간 후에 재생 중지
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            mediaPlayer.stop();
                        }
                    },
                    duration
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 액티비티가 종료되면 MediaPlayer를 해제합니다.
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
