package com.example.mymusic.main.mvp.view.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymusic.main.bean.AlbumSublistBean;
import com.example.mymusic.main.bean.ArtistSublistBean;
import com.example.mymusic.main.bean.MvSublistBean;
import com.example.mymusic.main.bean.MyFmBean;
import com.example.mymusic.main.bean.PlayModeIntelligenceBean;
import com.example.mymusic.main.mvp.contract.MineContract;
import com.example.mymusic.main.mvp.presenter.MinePresenter;
import com.example.mymusic.personal.bean.UserPlaylistBean;
import com.example.mymusic.search.adapter.FeedAdapter;
import com.example.mymusic.search.bean.MvBean;
import com.example.mymusic.util.LogUtil;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMvSubFragment extends BaseFragment<MinePresenter> implements MineContract.View {
    private static final String TAG = "MyMvSubFragment";

    @BindView(R.id.rv)
    RecyclerView rvMv;

    private FeedAdapter adapter;
    private List<MvBean> mvList = new ArrayList<>();
    private List<MvSublistBean.DataBean> videoList;

    public MyMvSubFragment() {
        setFragmentTitle("视频");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        adapter = new FeedAdapter(getContext());
        adapter.setType(2);
        rvMv.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMv.setAdapter(adapter);

        showDialog();
        mPresenter.getMvSublist();
    }


    @Override
    public MinePresenter onCreatePresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetUserPlaylistSuccess(UserPlaylistBean bean) {

    }

    @Override
    public void onGetUserPlaylistFail(String e) {

    }

    @Override
    public void onGetIntelligenceListSuccess(PlayModeIntelligenceBean bean) {

    }

    @Override
    public void onGetIntelligenceListFail(String e) {

    }

    @Override
    public void onGetMvSublistBeanSuccess(MvSublistBean bean) {
        hideDialog();
        LogUtil.d(TAG, "onGetMvSublistBeanSuccess :" + bean);
        videoList = bean.getData();
        mvList.clear();
        for (int i = 0; i < videoList.size(); i++) {
            MvBean mvBean = new MvBean();
            mvBean.setCoverUrl(videoList.get(i).getCoverUrl());
            mvBean.setCreator(videoList.get(i).getCreator());
            mvBean.setDuration(videoList.get(i).getDurationms());
            mvBean.setPlayTime(videoList.get(i).getPlayTime());
            mvBean.setTitle(videoList.get(i).getTitle());
            mvBean.setType(videoList.get(i).getType());
            mvBean.setVid(videoList.get(i).getVid());
            mvList.add(mvBean);
        }
        adapter.notifyDataSetChanged(mvList);
    }

    @Override
    public void onGetMvSublistBeanFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetMvSublistBeanFail : " + e);
    }

    @Override
    public void onGetArtistSublistBeanSuccess(ArtistSublistBean bean) {

    }

    @Override
    public void onGetArtistSublistBeanFail(String e) {

    }

    @Override
    public void onGetAlbumSublistBeanSuccess(AlbumSublistBean bean) {

    }

    @Override
    public void onGetAlbumSublistBeanFail(String e) {

    }

    @Override
    public void onGetMyFMSuccess(MyFmBean bean) {

    }

    @Override
    public void onGetMyFMFail(String e) {

    }
}
