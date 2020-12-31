package com.example.mymusic.main.mvp.view.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymusic.main.adapter.EndlessRecyclerOnScrollListener;
import com.example.mymusic.main.adapter.PlayListAdapter;
import com.example.mymusic.main.bean.BannerBean;
import com.example.mymusic.main.bean.DailyRecommendBean;
import com.example.mymusic.main.bean.HighQualityPlayListBean;
import com.example.mymusic.main.bean.MainRecommendPlayListBean;
import com.example.mymusic.main.bean.PlaylistBean;
import com.example.mymusic.main.bean.PlaylistDetailBean;
import com.example.mymusic.main.bean.RecommendPlayListBean;
import com.example.mymusic.main.bean.TopListBean;
import com.example.mymusic.main.mvp.contract.WowContract;
import com.example.mymusic.main.mvp.presenter.WowPresenter;
import com.example.mymusic.manager.bean.MusicCanPlayBean;
import com.example.mymusic.util.LogUtil;
import com.hjq.toast.ToastUtils;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseFragment;
import com.example.mymusic.main.mvp.view.PlayListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.mymusic.main.mvp.view.fragments.WowFragment.PLAYLIST_CREATOR_AVATARURL;
import static com.example.mymusic.main.mvp.view.fragments.WowFragment.PLAYLIST_CREATOR_NICKNAME;
import static com.example.mymusic.main.mvp.view.fragments.WowFragment.PLAYLIST_ID;
import static com.example.mymusic.main.mvp.view.fragments.WowFragment.PLAYLIST_NAME;
import static com.example.mymusic.main.mvp.view.fragments.WowFragment.PLAYLIST_PICURL;

/**
 * 歌单界面嵌套的Fragment来着...
 * 之所以把一个RecyclerView单独写成一个Fragment是为了解耦
 * <p>
 * Created By Rikka on 2019/7/17
 */
@SuppressLint("ValidFragment")
public class PlayListFragment extends BaseFragment<WowPresenter> implements WowContract.View {
    private static final String TAG = "PlayListFragment";


    //每行加载3个
    private static final int INIT_LOAD_LINE = 3;
    //总共加载30行
    private static final int TOTAL_LOAD_LINE = 30;
    @BindView(R.id.rv)
    RecyclerView rvPlaylist;

    private String type;
    private PlayListAdapter adapter;
    private List<RecommendPlayListBean.PlaylistsBean> playlist = new ArrayList<>();
    private List<PlaylistBean> list = new ArrayList<>();
    private GridLayoutManager manager;
    private int totalPage = 0;


    public PlayListFragment(String type) {
        this.type = type;
        setFragmentTitle(type);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        list.clear();
        adapter = new PlayListAdapter(getContext());
        adapter.setType(2);
        adapter.setListener(listener);
        manager = new GridLayoutManager(getContext(), 3);
        rvPlaylist.setLayoutManager(manager);
        rvPlaylist.addOnScrollListener(new EndlessRecyclerOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                //每次只加载三行
                lazyLoadPlayList(3);
            }
        });
        rvPlaylist.setHasFixedSize(true);
        rvPlaylist.setAdapter(adapter);
        playlist.clear();
        showDialog();
        mPresenter.getPlayList(type, TOTAL_LOAD_LINE * INIT_LOAD_LINE);
    }

    private PlayListAdapter.OnPlayListClickListener listener = position -> {
        if (playlist != null || !playlist.isEmpty()) {
            Intent intent = new Intent(getActivity(), PlayListActivity.class);
            RecommendPlayListBean.PlaylistsBean bean = playlist.get(position);
            String playlistName = bean.getName();
            intent.putExtra(PLAYLIST_NAME, playlistName);
            String playlistPicUrl = bean.getCoverImgUrl();
            intent.putExtra(PLAYLIST_PICURL, playlistPicUrl);
            String playlistCreatorNickName = bean.getCreator().getNickname();
            intent.putExtra(PLAYLIST_CREATOR_NICKNAME, playlistCreatorNickName);
            String playlistCreatorAvatarUrl = bean.getCreator().getAvatarUrl();
            intent.putExtra(PLAYLIST_CREATOR_AVATARURL, playlistCreatorAvatarUrl);
            long playlistId = bean.getId();
            intent.putExtra(PLAYLIST_ID, playlistId);
            startActivity(intent);
        }
    };

    @Override
    public WowPresenter onCreatePresenter() {
        return new WowPresenter(this);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onGetBannerSuccess(BannerBean bean) {

    }

    @Override
    public void onGetBannerFail(String e) {

    }

    @Override
    public void onGetRecommendPlayListSuccess(MainRecommendPlayListBean bean) {

    }

    @Override
    public void onGetRecommendPlayListFail(String e) {

    }

    @Override
    public void onGetDailyRecommendSuccess(DailyRecommendBean bean) {

    }

    @Override
    public void onGetDailyRecommendFail(String e) {

    }

    @Override
    public void onGetTopListSuccess(TopListBean bean) {

    }

    @Override
    public void onGetTopListFail(String e) {

    }

    /**
     * 这个接口没有分批，只能一次请求完所有，然后分批加载
     */
    @Override
    public void onGetPlayListSuccess(RecommendPlayListBean bean) {
        hideDialog();
        playlist.addAll(bean.getPlaylists());
        lazyLoadPlayList(3);
    }

    private void lazyLoadPlayList(int page) {
        LogUtil.d(TAG, "totalPage :" + totalPage + " " + 90);
        if (playlist.size() == 0 || totalPage >= 90) {
            return;
        }
        for (int i = totalPage; i < (totalPage + page * INIT_LOAD_LINE); i++) {
            PlaylistBean beanInfo = new PlaylistBean();
            beanInfo.setPlaylistName(playlist.get(i).getName());
            beanInfo.setPlaylistCoverUrl(playlist.get(i).getCoverImgUrl());
            list.add(beanInfo);
        }
        totalPage += page * INIT_LOAD_LINE;
        adapter.notifyDataSetChanged(list);
    }

    @Override
    public void onGetPlayListFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetPlayListFail : " + e);
        ToastUtils.show(e);
    }

    @Override
    public void onGetPlaylistDetailSuccess(PlaylistDetailBean bean) {

    }

    @Override
    public void onGetPlaylistDetailFail(String e) {

    }

    @Override
    public void onGetMusicCanPlaySuccess(MusicCanPlayBean bean) {

    }

    @Override
    public void onGetMusicCanPlayFail(String e) {

    }

    @Override
    public void onGetHighQualitySuccess(HighQualityPlayListBean bean) {

    }

    @Override
    public void onGetHighQualityFail(String e) {

    }
}
