package com.example.mymusic.main.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymusic.main.adapter.ArtistSubAdapter;
import com.example.mymusic.main.bean.AlbumSublistBean;
import com.example.mymusic.main.bean.ArtistSublistBean;
import com.example.mymusic.main.bean.MvSublistBean;
import com.example.mymusic.main.bean.MyFmBean;
import com.example.mymusic.main.bean.PlayModeIntelligenceBean;
import com.example.mymusic.main.mvp.contract.MineContract;
import com.example.mymusic.main.mvp.presenter.MinePresenter;
import com.example.mymusic.personal.bean.UserPlaylistBean;
import com.example.mymusic.search.mvp.view.SingerActivity;
import com.example.mymusic.util.LogUtil;
import com.hjq.toast.ToastUtils;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyArtistSubFragment extends BaseFragment<MinePresenter> implements MineContract.View {
    private static final String TAG = "MyArtistSubFragment";

    @BindView(R.id.rv)
    RecyclerView rv;

    private List<ArtistSublistBean.DataBean> artistSubList = new ArrayList<>();
    private ArtistSubAdapter adapter;

    public MyArtistSubFragment() {
        setFragmentTitle("歌手");
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        adapter = new ArtistSubAdapter(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        adapter.setListener(listener);

        showDialog();
        mPresenter.getArtistSublist();
    }

    ArtistSubAdapter.OnArtistClickListener listener = position -> {
        if (position >= artistSubList.size()) {
            return;
        }
        Intent intent = new Intent(getContext(), SingerActivity.class);
        intent.putExtra(SingerActivity.SINGER_PICURL, artistSubList.get(position).getPicUrl());
        intent.putExtra(SingerActivity.SINGER_ID, artistSubList.get(position).getId());
        intent.putExtra(SingerActivity.SINGER_NAME, artistSubList.get(position).getName());
        startActivity(intent);
    };

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

    }

    @Override
    public void onGetMvSublistBeanFail(String e) {

    }

    @Override
    public void onGetArtistSublistBeanSuccess(ArtistSublistBean bean) {
        hideDialog();
        LogUtil.d(TAG, "onGetArtistSublistBeanSuccess :" + bean);
        artistSubList = bean.getData();
        adapter.notifyDataSetChanged(artistSubList);
    }

    @Override
    public void onGetArtistSublistBeanFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetArtistSublistBeanFail : " + e);
        ToastUtils.show(e);
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
