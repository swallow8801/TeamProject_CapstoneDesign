# 캡스톤디자인 팀 프로젝트
2023.09.06 - 2023.12.13 까지 진행된 "캡스톤디자인" 팀 프로젝트로 12.13에 진행한 컴퓨터공학과 학술제에서 "캡스톤디자인 우수작"으로 선정되어 발표를 하게되었습니다.

## 프로젝트 소개

### 0. 팀원 소개 
###### 전재엽 : 프로젝트 총괄 및 팀장. 게임 및 어플 개선 및 피드백. 머신러닝 이용한 게임 개발
###### 심재헌 : 제시용 게임 개발. 문제 데이터 관리 및 처리
###### 이성훈 : 피지컬 게임 개발. UI 및 디자인 개선 및 음악 데이터 편집 및 관리
###### 설유일 : 복불복 게임 개발. 외부 라이브러리 참조한 게임 개발 및 디자인 전반

### 1. 어플 이름 : 놀고먹고(Nm)

### 2. 어플 소개 : 술자리나 MT 등 하나의 폰으로 즐길 수 있는 여러 가지 술 게임 문화를 어플로 구현

### 3. 제작 방법 
##### 제작 Tools : Android Studio , Firebase (ML kit)
##### 디자인 및 음악 : 수제작 , GoldWave , FLstudio
##### 회의 : Discord(화면공유 및 피드백) , GitHub(파일공유)

### 4. 게임 소개
###### [1. 폭탄게임](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/master/app/src/main/java/com/example/teamprojectapplication/bombgame.java)
###### [2. 라이어게임](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/liargame.java)
###### [3. 인물 퀴즈](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/personimagegame.java)
###### [4. 사물 퀴즈](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/objectgame.java)
###### [5. 초성 게임](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/chosunggame.java)
###### [6. 이어말하기](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/relayspeakingfour.java)
###### [7. 병돌리기](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/bottlespiner.java)
###### [8. 터치 게임](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/touchgame.java)
###### [9. 벌칙 룰렛](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/Roulette.java)
###### [10. 베스킨라빈스31+](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/thrityone.java)
###### [11. 왕게임](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/KingGameReal.java)
###### [12. 스탑 워치](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/stopwatch.java)
###### [13. 이모지 게임](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/emojigame.java)
###### [14. 전주 듣고 노래 맞추기](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/introsong.java)
###### [15. 핑거초이스](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/java/com/example/teamprojectapplication/fingerchoice.java)
###### 16. 얼굴 인식 게임


### 5. 데이터 관리

#### [Introsong.Json 예시](https://github.com/swallow8801/TeamProject_CapstoneDesign/blob/main/app/src/main/assets/jsons/introsong.json)
```json
[
  {"topic": "2010년대", "start": "songstart001", "song": "song001", "title": "내가 제일 잘나가 - 2NE1"},
  {"topic": "2010년대", "start": "songstart002", "song": "song002", "title": "Fantastic Baby - 빅뱅"},
  {"topic": "2010년대", "start": "songstart003", "song": "song003", "title": "나혼자 - 씨쓰타"},
  {"topic": "2010년대", "start": "songstart004", "song": "song004", "title": "Roly Poly - 티아라"},
  {"topic": "2010년대", "start": "songstart005", "song": "song005", "title": "Offically Missing You - 긱스"},
  {"topic": "2010년대", "start": "songstart006", "song": "song006", "title": "거북이 - 다비치"},
  {"topic": "2010년대", "start": "songstart007", "song": "song007", "title": "TV를 껐네 - 리쌍"},
  {"topic": "2010년대", "start": "songstart008", "song": "song008", "title": "그땐 그땐 그땐 - 슈프림팀"},
  {"topic": "2010년대", "start": "songstart009", "song": "song009", "title": "너랑나 - 아이유"},
```

#### JSON Parsing & Filtering
```java
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
```


