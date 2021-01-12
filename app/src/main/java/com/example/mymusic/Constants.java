package com.example.mymusic;

public interface Constants {
    String SHARED_PREFERENCE_FILE_NAME = "MyMusic_sp";


    interface SpKey {
        String TAG_LANGUAGE = "languageSelect";

        String AUTH_TOKEN = "authToken";

        String USER_INFO = "userInfo";

        String PHONE_NUMBER = "phoneNumber";

        String LATEST_SONG = "latestSong";

        String DAILY_UPDATE_TIME = "dailyUpdateTime";

        String DAILY_RECOMMEND = "dailyRecommend";

        String LIKE_LIST = "likeList";
    }
    int MUSIC_CONTROL_NEXT=1;
    int MUSIC_CONTROL_PREVIONS=2;
    int MUSIC_CONTROL_LIKE_OR_UNLIKE=3;
    int MUSIC_CONTROL_LYRIC_OR_NOT=4;
    int MUSIC_CONTROL_PLAY_OR_PAUSE=5;
}
