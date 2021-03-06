package com.example.mymusic.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.mymusic.App;
import com.example.mymusic.Constants;
import com.example.mymusic.R;
import com.example.mymusic.manager.SongPlayManager;
import com.example.mymusic.manager.event.MusicPauseEvent;
import com.example.mymusic.manager.event.MusicStartEvent;
import com.example.mymusic.manager.event.StopMusicEvent;
import com.example.mymusic.util.LogUtil;
import com.lzx.starrysky.model.SongInfo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

/**
 * Create by huangf20
 * on 2021/1/11
 */
public class NotificationUtil {
    private static final String TAG = "NotificationUtil";
    private NotificationManager mNotificationManager;
    private Context mContext;
    private static NotificationUtil sNotificationUtil;
    private Notification mNotification;
    public static final int NOTIFICATION_ID = 10004;
    private RemoteViews mNormalView;
    private RemoteViews mBigview;
    public  static NotificationUtil getInstance(Context mContext){
        if(sNotificationUtil !=null){
            return sNotificationUtil;
        }
        sNotificationUtil =new NotificationUtil(mContext);
        return sNotificationUtil;
    }
    public NotificationUtil(Context mContext) {
        this.mContext = mContext;
        mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView(){
        mNormalView = new RemoteViews(mContext.getPackageName(), R.layout.notification_layout);
        //显示bigView的notification用到的视图
        mBigview = new RemoteViews(mContext.getPackageName(), R.layout.notification_layout);

        mBigview.setImageViewResource(R.id.iv_sing_cover,R.mipmap.ic_launcher);
        //设置下一首按键监听
        mBigview.setOnClickPendingIntent(R.id.notification_next,getPendingIntent(Constants.MUSIC_CONTROL_NEXT));
        //设置上一首按键监听
        mBigview.setOnClickPendingIntent(R.id.notification_pre,getPendingIntent(Constants.MUSIC_CONTROL_PREVIONS));
        //设置播放/暂停按键监听
        mBigview.setOnClickPendingIntent(R.id.notification_play_pause,getPendingIntent(Constants.MUSIC_CONTROL_PLAY_OR_PAUSE));
        File file=new File("/sdcard/mymusiconline/jay.png");
    }

    private PendingIntent getPendingIntent(int flag){
        Intent intent=new Intent("com.example.mymusic.controlbroadcast");
        intent.putExtra("action", flag);
        PendingIntent playControlIntent=PendingIntent.getBroadcast(mContext,flag,intent,flag);
        return  playControlIntent;
    }
    /**
     * 展示通知栏
     */
    public void showNotification(){
        String id = "com.example.mymusic";
        mNotification = new NotificationCompat.Builder(mContext)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("开始播放啦~~")
                .setOngoing(true)
                .setContent(mNormalView)//设置普通notification视图
                .setCustomBigContentView(mBigview)//设置显示bigView的notification视图
                .setPriority(NotificationCompat.PRIORITY_MAX)//设置最大优先级
                .build();
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);

    }

    public void setPlayButton(){
        mBigview.setImageViewResource(R.id.notification_play_pause,R.drawable.shape_pause_notification);
    }
    public void setPauseButton(){
        mBigview.setImageViewResource(R.id.notification_play_pause,R.drawable.shape_play_notification);
    }
    public void setLrcYes(){
        mBigview.setImageViewResource(R.id.notification_lrc,R.drawable.shape_lrc_yes_notification);
    }
    public void setLrcNot(){
        mBigview.setImageViewResource(R.id.notification_lrc,R.drawable.shape_lrc_not_notification);
    }

    public void setLikeOrDislike(Boolean isLike){
        if(isLike){
            mBigview.setImageViewResource(R.id.notification_like,R.drawable.shape_like_white);
        }
        else{
            mBigview.setImageViewResource(R.id.notification_like,R.drawable.shape_not_like_black);
        }

    }

    public void setCover(String uri){
        Glide.with(App.getContext())
                .asBitmap().load(uri)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(new RequestOptions().override(140,140))
                .into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                mBigview.setImageViewBitmap(R.id.iv_sing_cover,resource);
                mNotificationManager.notify(NOTIFICATION_ID, mNotification);
            }
        });
    }
    public void setSinger(String s){
        mBigview.setTextViewText(R.id.tv_song_singer,s);
    }
    public void setSong(String s){
        mBigview.setTextViewText(R.id.tv_song_name,s);
    }
    public void sendNext(){
    }
    public void sendPre(){

    }
    public void sendPlayOrPause(){

    }
    public void updataView(SongInfo songInfo){
        setLikeOrDislike(SongPlayManager.isLike(songInfo.getSongId()));
        setSinger(songInfo.getArtist());
        setSong(songInfo.getSongName());

    }
    public void updataViewWithNoti(SongInfo songInfo){
        updataView(songInfo);
        updataNotification();
    }
    public void updataNotification(){
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayMusicEvent(MusicStartEvent event) {
        LogUtil.d(TAG, "MusicStartEvent :" + event);
        updataView(event.getSongInfo());
        setPlayButton();
        updataNotification();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onStopMusicEvent(StopMusicEvent event) {
        LogUtil.d(TAG, "onStopMusicEvent");
        setPauseButton();
        updataNotification();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPauseMusicEvent(MusicPauseEvent event) {
        LogUtil.d(TAG, "onPauseMusicEvent");
        setPauseButton();
        updataNotification();
    }
}
