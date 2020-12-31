package com.example.mymusic.search.mvp.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymusic.App;
import com.example.mymusic.search.bean.FeedSearchBean;
import com.example.mymusic.search.bean.SimiSingerBean;
import com.example.mymusic.search.bean.SingerAblumSearchBean;
import com.example.mymusic.search.bean.SingerDescriptionBean;
import com.example.mymusic.search.bean.SingerSongSearchBean;
import com.example.mymusic.search.event.SingIdEvent;
import com.hjq.toast.ToastUtils;
import com.lzx.starrysky.model.SongInfo;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseFragment;
import com.example.mymusic.main.adapter.SongListAdapter;
import com.example.mymusic.search.mvp.contract.SingerContract;
import com.example.mymusic.search.mvp.presenter.SingerPresenter;
import com.example.mymusic.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 歌手热门歌曲搜索
 */
@SuppressLint("ValidFragment")
public class SingerSongSearchFragment extends BaseFragment<SingerPresenter> implements SingerContract.View {
    private static final String TAG = "SingerSongSearchFragment";

    @BindView(R.id.rv_singer_song)
    RecyclerView rvSingerSong;

    private long singerId = -1;
    private String type;
    private SongListAdapter adapter;
    private List<SingerSongSearchBean.HotSongsBean> hotSongList = new ArrayList<>();
    private List<SongInfo> songInfos = new ArrayList<>();


    public SingerSongSearchFragment() {
        setFragmentTitle(App.getContext().getString(R.string.singer_hot_song));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetSingerIdEvent(SingIdEvent event) {
        singerId = event.getSingId();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_singer_hot_song, container, false);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    protected void initData() {
        adapter = new SongListAdapter(getContext());
        rvSingerSong.setLayoutManager(new LinearLayoutManager(getContext()));
        rvSingerSong.setAdapter(adapter);
        adapter.setType(2);

        if (singerId != -1) {
            showDialog();
            mPresenter.getSingerHotSong(singerId);
        }
    }

    @Override
    public SingerPresenter onCreatePresenter() {
        return new SingerPresenter(this);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetSingerHotSongSuccess(SingerSongSearchBean bean) {
        hideDialog();
        hotSongList.clear();
        songInfos.clear();
        hotSongList.addAll(bean.getHotSongs());
        for (int i = 0; i < hotSongList.size(); i++) {
            SongInfo songInfo = new SongInfo();
            songInfo.setSongId(String.valueOf(bean.getHotSongs().get(i).getId()));
            songInfo.setSongUrl(BaseActivity.SONG_URL + bean.getHotSongs().get(i).getId() + ".mp3");
            songInfo.setSongName(bean.getHotSongs().get(i).getName());
            songInfo.setDuration(bean.getHotSongs().get(i).getDt());
            songInfo.setArtist(bean.getArtist().getName());
            songInfo.setSongCover(bean.getHotSongs().get(i).getAl().getPicUrl());
            songInfo.setArtistId(String.valueOf(bean.getArtist().getId()));
            songInfo.setArtistKey(bean.getArtist().getPicUrl());
            songInfos.add(songInfo);
        }
        adapter.notifyDataSetChanged(songInfos);
    }

    @Override
    public void onGetSingerHotSongFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetSingerHotSongFail " + e);
        ToastUtils.show(e);
    }

    @Override
    public void onGetSingerAlbumSuccess(SingerAblumSearchBean bean) {

    }

    @Override
    public void onGetSingerAlbumFail(String e) {

    }

    @Override
    public void onGetFeedSearchSuccess(FeedSearchBean bean) {

    }

    @Override
    public void onGetFeedSearchFail(String e) {

    }

    @Override
    public void onGetSingerDescSuccess(SingerDescriptionBean bean) {

    }

    @Override
    public void onGetSingerDescFail(String e) {

    }

    @Override
    public void onGetSimiSingerSuccess(SimiSingerBean bean) {

    }

    @Override
    public void onGetSimiSingerFail(String e) {

    }
}
