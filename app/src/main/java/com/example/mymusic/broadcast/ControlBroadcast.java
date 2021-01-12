package com.example.mymusic.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mymusic.App;
import com.example.mymusic.Constants;
import com.example.mymusic.manager.SongPlayManager;
import com.example.mymusic.util.LogUtil;
import com.example.mymusic.util.SharePreferenceUtil;
import com.lzx.starrysky.model.SongInfo;


/**
 * Create by huangf20
 * on 2021/1/11
 */
public class ControlBroadcast extends BroadcastReceiver {
    private static final String TAG = "ControlBroadcast";
    @Override
    public void onReceive(Context context, Intent intent) {
        int action = intent.getIntExtra("action",0);
        if (action!=0){
            switch (action){
                case Constants.MUSIC_CONTROL_LIKE_OR_UNLIKE:
                case Constants.MUSIC_CONTROL_LYRIC_OR_NOT:
                case Constants.MUSIC_CONTROL_NEXT:
                    SongPlayManager.getInstance().playNextMusic();
                    LogUtil.d(TAG,"onReceive:   "+"playNextMusic");
                    break;
                case Constants.MUSIC_CONTROL_PREVIONS:
                    SongPlayManager.getInstance().playPreMusic();
                    LogUtil.d(TAG,"onReceive:   "+"playPreMusic");
                    break;
                case Constants.MUSIC_CONTROL_PLAY_OR_PAUSE:
                    if (SongPlayManager.getInstance().isPlaying()) {
                        SongPlayManager.getInstance().pauseMusic();
                    } else if (SongPlayManager.getInstance().isPaused()) {
                        SongPlayManager.getInstance().playMusic();
                    } else if (SongPlayManager.getInstance().isIdle()) {
                        SongInfo songInfo = SharePreferenceUtil.getInstance(App.getContext()).getLatestSong();
                        SongPlayManager.getInstance().clickASong(songInfo);
                    }
                    LogUtil.d(TAG,"onReceive:   "+"playOrPauseMusic");
                    break;
                default:
                    break;
            }

        }
    }
}
