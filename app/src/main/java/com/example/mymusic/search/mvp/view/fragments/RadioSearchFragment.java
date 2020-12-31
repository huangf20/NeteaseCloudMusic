package com.example.mymusic.search.mvp.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mymusic.search.adapter.RadioSearchAdapter;
import com.example.mymusic.search.bean.AlbumSearchBean;
import com.example.mymusic.search.bean.FeedSearchBean;
import com.example.mymusic.search.bean.HotSearchDetailBean;
import com.example.mymusic.search.bean.PlayListSearchBean;
import com.example.mymusic.search.bean.RadioSearchBean;
import com.example.mymusic.search.bean.SingerSearchBean;
import com.example.mymusic.search.bean.SongSearchBean;
import com.example.mymusic.search.bean.SynthesisSearchBean;
import com.example.mymusic.search.bean.UserSearchBean;
import com.example.mymusic.search.event.KeywordsEvent;
import com.example.mymusic.search.mvp.contract.SearchContract;
import com.example.mymusic.search.mvp.presenter.SearchPresenter;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseFragment;
import com.example.mymusic.search.mvp.view.SearchResultActivity;
import com.example.mymusic.util.LogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 电台搜索Fragment 1009
 */
@SuppressLint("ValidFragment")
public class RadioSearchFragment extends BaseFragment<SearchPresenter> implements SearchContract.View {
    private static final String TAG = "RadioSearchFragment";

    @BindView(R.id.rv)
    RecyclerView rvRadio;

    private String type;
    private int searchType = 1009;
    private String keywords;
    private boolean needRefresh = false;
    private List<RadioSearchBean.ResultBean.DjRadiosBean> djList = new ArrayList<>();
    private RadioSearchAdapter adapter;

    public RadioSearchFragment() {
    }

    public RadioSearchFragment(String type) {
        this.type = type;
        setFragmentTitle(type);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetKeywordsEvent(KeywordsEvent event) {
        LogUtil.d(TAG, "onGetKeywordsEvent : " + event.toString());
        if (event != null) {
            if (keywords != null && !event.getKeyword().equals(keywords)) {
                needRefresh = true;
                if (((SearchResultActivity) getActivity()).getPosition() == 2) {
                    needRefresh = false;
                    keywords = event.getKeyword();
                    showDialog();
                    mPresenter.getRadioSearch(keywords, searchType);
                }
            }
            keywords = event.getKeyword();
        }
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
        adapter = new RadioSearchAdapter(getContext());
        adapter.setKeywords(keywords);
        adapter.setListener(listener);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvRadio.setLayoutManager(manager);
        rvRadio.setAdapter(adapter);

        if (keywords != null) {
            showDialog();
            mPresenter.getRadioSearch(keywords, searchType);
        }
    }

    RadioSearchAdapter.OnRadioClickListener listener = position -> {
        ToastUtils.showShort("点击了 : " + position);
    };

    @Override
    public SearchPresenter onCreatePresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    protected void onVisible() {
        super.onVisible();
        if (needRefresh) {
            needRefresh = false;
            showDialog();
            mPresenter.getRadioSearch(keywords, searchType);
        }
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
    public void onGetHotSearchDetailSuccess(HotSearchDetailBean bean) {

    }

    @Override
    public void onGetHotSearchDetailFail(String e) {

    }

    @Override
    public void onGetSongSearchSuccess(SongSearchBean bean) {

    }

    @Override
    public void onGetSongSearchFail(String e) {

    }

    @Override
    public void onGetFeedSearchSuccess(FeedSearchBean bean) {

    }

    @Override
    public void onGetFeedSearchFail(String e) {

    }

    @Override
    public void onGetSingerSearchSuccess(SingerSearchBean bean) {

    }

    @Override
    public void onGetSingerSearchFail(String e) {

    }

    @Override
    public void onGetAlbumSearchSuccess(AlbumSearchBean bean) {

    }

    @Override
    public void onGetAlbumSearchFail(String e) {

    }

    @Override
    public void onGetPlayListSearchSuccess(PlayListSearchBean bean) {

    }

    @Override
    public void onGetPlayListSearchFail(String e) {

    }

    @Override
    public void onGetRadioSearchSuccess(RadioSearchBean bean) {
        hideDialog();
        LogUtil.d(TAG, "onGetRadioSearchSuccess : " + bean);
        djList.clear();
        if(bean.getResult().getDjRadios() != null) {
            djList.addAll(bean.getResult().getDjRadios());
        }
        adapter.notifyDataSetChanged(djList);
    }

    @Override
    public void onGetRadioSearchFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetRadioSearchFail : " + e);
        ToastUtils.showShort(e);
    }

    @Override
    public void onGetUserSearchSuccess(UserSearchBean bean) {

    }

    @Override
    public void onGetUserSearchFail(String e) {

    }

    @Override
    public void onGetSynthesisSearchSuccess(SynthesisSearchBean bean) {

    }

    @Override
    public void onGetSynthesisSearchFail(String e) {

    }
}
