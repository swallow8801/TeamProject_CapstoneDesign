package com.example.teamprojectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class introsong extends AppCompatActivity {
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
        String json = "";
        try {
            InputStream is = getAssets().open("jsons/introsong.json");
            int fileSize = is.available();

            byte[] buffer = new byte[fileSize];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);


            JSONArray filteredArray = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                if (Topic.equals(item.getString("topic"))) {
                    filteredArray.put(item);
                }
            }

            int length = filteredArray.length();

            song_data = new Object[length][3];

            for (int i = 0; i < length; i++) {
                JSONObject jsonObject = filteredArray.getJSONObject(i);
                String Jsontopic = jsonObject.getString("topic");
                if (Jsontopic.equals(Topic)) {
                    String startName = jsonObject.getString("start");
                    int startResourceId = getResources().getIdentifier(startName, "raw", getPackageName());
                    String songName = jsonObject.getString("song");
                    int songResourceId = getResources().getIdentifier(songName, "raw", getPackageName());

                    song_data[i][0] = startResourceId;
                    song_data[i][1] = songResourceId;
                    song_data[i][2] = jsonObject.getString("title");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // 데이터 배열을 무작위로 섞기
        shuffleSongDataArray();



        // MediaPlayer 초기화
        mediaPlayer = new MediaPlayer();

        // 앱 시작 시 초기 데이터 설정
        textView.setText(String.valueOf(song_data[currentIndex][2]));
        textView.setVisibility(View.INVISIBLE);
        textView2.setText(Topic);

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
