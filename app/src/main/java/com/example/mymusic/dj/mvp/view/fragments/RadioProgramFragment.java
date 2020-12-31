package com.example.mymusic.dj.mvp.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymusic.App;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseFragment;
import com.example.mymusic.dj.bean.DjCategoryRecommendBean;
import com.example.mymusic.dj.bean.DjCatelistBean;
import com.example.mymusic.dj.bean.DjDetailBean;
import com.example.mymusic.dj.bean.DjPaygiftBean;
import com.example.mymusic.dj.bean.DjProgramBean;
import com.example.mymusic.dj.bean.DjRecommendBean;
import com.example.mymusic.dj.bean.DjRecommendTypeBean;
import com.example.mymusic.dj.bean.DjSubBean;
import com.example.mymusic.dj.event.RidEvent;
import com.example.mymusic.dj.mvp.contract.DjContract;
import com.example.mymusic.dj.mvp.presenter.DjPresenter;
import com.example.mymusic.main.adapter.SongListAdapter;
import com.example.mymusic.util.LogUtil;
import com.hjq.toast.ToastUtils;
import com.lzx.starrysky.model.SongInfo;
import com.example.mymusic.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioProgramFragment extends BaseFragment<DjPresenter> implements DjContract.View {
    private static final String TAG = "RadioProgramFragment";

    @BindView(R.id.rv)
    RecyclerView rvProgram;

    private long rid = 0;
    private SongListAdapter adapter;

    public RadioProgramFragment() {
        setFragmentTitle(App.getContext().getString(R.string.program));
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetRidEvent(RidEvent event) {
        LogUtil.d(TAG, "onGetRidEvent :" + event);
        rid = event.getRid();
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    protected void initData() {
        rvProgram.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SongListAdapter(getContext());
        adapter.setType(2);
        rvProgram.setAdapter(adapter);

        if (rid != 0) {
            showDialog();
            mPresenter.getDjProgram(rid);
        }
    }

    @Override
    public DjPresenter onCreatePresenter() {
        return new DjPresenter(this);
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
    public void onGetDjRecommendSuccess(DjRecommendBean bean) {

    }

    @Override
    public void onGetDjRecommendFail(String e) {

    }

    @Override
    public void onGetDjPaygiftSuccess(DjPaygiftBean bean) {

    }

    @Override
    public void onGetDjPaygiftFail(String e) {

    }

    @Override
    public void onGetDjRecommendTypeSuccess(DjRecommendTypeBean bean) {

    }

    @Override
    public void onGetDjRecommendTypeFail(String e) {

    }

    @Override
    public void onGetDjCategoryRecommendSuccess(DjCategoryRecommendBean bean) {

    }

    @Override
    public void onGetDjCategoryRecommendFail(String e) {

    }

    @Override
    public void onGetDjCatelistSuccess(DjCatelistBean bean) {

    }

    @Override
    public void onGetDjCatelistFail(String e) {

    }

    @Override
    public void onSubDjSuccess(DjSubBean bean) {

    }

    @Override
    public void onSubDjFail(String e) {

    }

    @Override
    public void onGetDjProgramSuccess(DjProgramBean bean) {
        hideDialog();
        LogUtil.d(TAG, "onGetDjProgramSuccess :" + bean);
        List<DjProgramBean.ProgramsBean> programs = bean.getPrograms();
        notifyProgram(programs);
    }

    private void notifyProgram(List<DjProgramBean.ProgramsBean> programs) {
        List<SongInfo> songInfos = new ArrayList<>();
        for (int i = 0; i < programs.size(); i++) {
            SongInfo songInfo = new SongInfo();
            DjProgramBean.ProgramsBean programsBean = programs.get(i);
            songInfo.setSongCover(programsBean.getCoverUrl());
            songInfo.setDuration(programsBean.getDuration());
            songInfo.setArtist(programsBean.getDj().getNickname());
            songInfo.setArtistId(String.valueOf(programsBean.getDj().getUserId()));
            songInfo.setSongId(String.valueOf(programsBean.getMainSong().getId()));
            songInfo.setArtistKey(programsBean.getDj().getAvatarUrl());
            songInfo.setSongUrl(BaseActivity.SONG_URL + programsBean.getMainSong().getId() + ".mp3");
            songInfo.setSongName(programsBean.getName());
            songInfos.add(songInfo);
        }
        adapter.notifyDataSetChanged(songInfos);
    }

    @Override
    public void onGetDjProgramFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetDjProgramFail :" + e);
        ToastUtils.show(e);
    }

    @Override
    public void onGetDjDetailSuccess(DjDetailBean bean) {

    }

    @Override
    public void onGetDjDetailFail(String e) {

    }
}
