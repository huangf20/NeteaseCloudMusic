package com.example.mymusic.song.mvp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mymusic.manager.SongPlayManager;
import com.example.mymusic.song.adapter.SongListAdapter;
import com.example.mymusic.song.bean.CommentLikeBean;
import com.example.mymusic.song.bean.LikeMusicBean;
import com.example.mymusic.song.bean.LyricBean;
import com.example.mymusic.song.bean.MusicCommentBean;
import com.example.mymusic.song.bean.PlayListCommentBean;
import com.example.mymusic.song.bean.SongDetailBean;
import com.example.mymusic.song.mvp.contract.SongContract;
import com.example.mymusic.song.mvp.presenter.SongPresenter;
import com.example.mymusic.widget.MaxHeightRecyclerView;
import com.hjq.toast.ToastUtils;
import com.lzx.starrysky.model.SongInfo;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.main.bean.LikeListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 播放列表，直接从Manager里面拿出来展示就行了
 */
public class SongListActivity extends BaseActivity<SongPresenter> implements SongContract.View {
    private static final String TAG = "SongListActivity";

    @BindView(R.id.view)
    View view;
    @BindView(R.id.rv_playlist)
    MaxHeightRecyclerView rvPlayList;
    @BindView(R.id.iv_play_mode)
    ImageView ivPlayMode;
    @BindView(R.id.tv_play_mode)
    TextView tvPlayMode;
    @BindView(R.id.iv_trash_can)
    ImageView ivDeleteAll;

    SongListAdapter adapter;
    private Animation toTranslateIn;
    private Animation toTranslateOut;
    private List<SongInfo> songList = new ArrayList<>();

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_song_list);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (toTranslateIn == null) {
            toTranslateIn = AnimationUtils.loadAnimation(this, R.anim.view_to_translate_in);
            toTranslateIn.setFillAfter(true);
            toTranslateIn.setStartOffset(200);
        }
        if (toTranslateOut == null) {
            toTranslateOut = AnimationUtils.loadAnimation(this, R.anim.view_to_translate_out);
            toTranslateOut.setFillAfter(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        view.startAnimation(toTranslateIn);
        setPlayMode(SongPlayManager.getInstance().getMode());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setPlayMode(SongPlayManager.getInstance().getMode());
    }

    @Override
    protected SongPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initModule() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        rvPlayList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SongListAdapter(this);
        adapter.setListener(listener);
        rvPlayList.setAdapter(adapter);

        songList = SongPlayManager.getInstance().getSongList();
        adapter.notifyDataSetChanged(songList);

        if (SongPlayManager.getInstance().isPlaying() || SongPlayManager.getInstance().isPaused()) {
            rvPlayList.scrollToPosition(SongPlayManager.getInstance().getCurrentSongIndex());
        }

        setPlayMode(SongPlayManager.getInstance().getMode());
    }

    private void setPlayMode(int playMode) {
        switch (playMode) {
            case SongPlayManager.MODE_LIST_LOOP_PLAY:
                tvPlayMode.setText("列表循环");
                setPlayModeImageColor(R.drawable.shape_list_cycle_grey);
                break;
            case SongPlayManager.MODE_SINGLE_LOOP_PLAY:
                tvPlayMode.setText("单曲循环");
                setPlayModeImageColor(R.drawable.shape_single_cycle_grey);
                break;
            case SongPlayManager.MODE_RANDOM:
                tvPlayMode.setText("随机播放");
                setPlayModeImageColor(R.drawable.shape_list_random_grey);
                break;
        }
    }

    private void setPlayModeImageColor(int resId) {
        VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), resId, getTheme());
        vectorDrawableCompat.setTint(Color.parseColor("#999999"));
        ivPlayMode.setImageDrawable(vectorDrawableCompat);
    }

    SongListAdapter.OnSongClickListener listener = new SongListAdapter.OnSongClickListener() {
        @Override
        public void onMusicClick(int position) {
            SongPlayManager.getInstance().switchMusic(position);
            songList = SongPlayManager.getInstance().getSongList();
            adapter.notifyDataSetChanged(songList);
        }

        @Override
        public void onDelClick(int position) {
            SongPlayManager.getInstance().deleteSong(position);
            songList = SongPlayManager.getInstance().getSongList();
            adapter.notifyDataSetChanged(songList);
        }
    };

    @Override
    @OnClick({R.id.view, R.id.rl_play_mode, R.id.iv_trash_can})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view:
                finish();
                break;
            case R.id.rl_play_mode:
                if (SongPlayManager.getInstance().getMode() == SongPlayManager.MODE_LIST_LOOP_PLAY) {
                    ToastUtils.show("已切换到单曲循环");
                    SongPlayManager.getInstance().setMode(SongPlayManager.MODE_SINGLE_LOOP_PLAY);
                } else if (SongPlayManager.getInstance().getMode() == SongPlayManager.MODE_SINGLE_LOOP_PLAY) {
                    ToastUtils.show("已切换到列表随机");
                    SongPlayManager.getInstance().setMode(SongPlayManager.MODE_RANDOM);
                } else if (SongPlayManager.getInstance().getMode() == SongPlayManager.MODE_RANDOM) {
                    ToastUtils.show("已切换到列表循环");
                    SongPlayManager.getInstance().setMode(SongPlayManager.MODE_LIST_LOOP_PLAY);
                }
                setPlayMode(SongPlayManager.getInstance().getMode());
                break;
            case R.id.iv_trash_can:
                SongPlayManager.getInstance().clearSongList();
                songList = SongPlayManager.getInstance().getSongList();
                adapter.notifyDataSetChanged(songList);
                break;
        }
    }

    @Override
    public void finish() {
        super.finish();
        view.startAnimation(toTranslateOut);
        overridePendingTransition(R.anim.bottom_silent, R.anim.bottom_out);
    }

    @Override
    public void onGetSongDetailSuccess(SongDetailBean bean) {

    }

    @Override
    public void onGetSongDetailFail(String e) {

    }

    @Override
    public void onLikeMusicSuccess(LikeMusicBean bean) {

    }

    @Override
    public void onLikeMusicFail(String e) {

    }

    @Override
    public void onGetLikeListSuccess(LikeListBean bean) {

    }

    @Override
    public void onGetLikeListFail(String e) {

    }

    @Override
    public void onGetMusicCommentSuccess(MusicCommentBean bean) {

    }

    @Override
    public void onGetMusicCommentFail(String e) {

    }

    @Override
    public void onLikeCommentSuccess(CommentLikeBean bean) {

    }

    @Override
    public void onLikeCommentFail(String e) {

    }

    @Override
    public void onGetLyricSuccess(LyricBean bean) {

    }

    @Override
    public void onGetLyricFail(String e) {

    }

    @Override
    public void onGetPlaylistCommentSuccess(PlayListCommentBean bean) {

    }

    @Override
    public void onGetPlaylistCommentFail(String e) {

    }
}
