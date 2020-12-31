package com.example.mymusic.main.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mymusic.main.adapter.RankAdapter;
import com.example.mymusic.main.bean.BannerBean;
import com.example.mymusic.main.bean.DailyRecommendBean;
import com.example.mymusic.main.bean.HighQualityPlayListBean;
import com.example.mymusic.main.bean.MainRecommendPlayListBean;
import com.example.mymusic.main.bean.PlaylistDetailBean;
import com.example.mymusic.main.bean.RecommendPlayListBean;
import com.example.mymusic.main.bean.TopListBean;
import com.example.mymusic.main.mvp.contract.WowContract;
import com.example.mymusic.main.mvp.presenter.WowPresenter;
import com.example.mymusic.manager.bean.MusicCanPlayBean;
import com.example.mymusic.util.LogUtil;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;

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
 * 排行榜界面
 * Created on 2020/7/16
 */
public class RankActivity extends BaseActivity<WowPresenter> implements WowContract.View {
    private static final String TAG = "RankActivity";

    @BindView(R.id.rv_toplist)
    RecyclerView rvTopList;

    private List<TopListBean.ListBean> list = new ArrayList<>();
    private RankAdapter adapter;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_rank);

        ImmersionBar.with(this)
                .transparentBar()
                .statusBarColor(R.color.colorPrimary)
                .statusBarDarkFont(false)
                .init();
    }

    @Override
    protected WowPresenter onCreatePresenter() {
        return new WowPresenter(this);
    }

    @Override
    protected void initModule() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        setBackBtn(getString(R.string.colorWhite));
        setLeftTitleText(getString(R.string.rank), getString(R.string.colorWhite));
        list.clear();
        adapter = new RankAdapter(this);
        adapter.setListener(listener);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rvTopList.setLayoutManager(manager);
        rvTopList.setAdapter(adapter);
        showDialog();
        mPresenter.getTopList();
    }

    private RankAdapter.OnTopListClickListener listener = position -> {
        if (list != null || !list.isEmpty()) {
            //进入歌单详情页面
            Intent intent = new Intent(RankActivity.this, PlayListActivity.class);
            TopListBean.ListBean bean = list.get(position);
            String playlistName = bean.getName();
            intent.putExtra(PLAYLIST_NAME, playlistName);
            String playlistPicUrl = bean.getCoverImgUrl();
            intent.putExtra(PLAYLIST_PICURL, playlistPicUrl);
            String playlistCreatorNickname = "";
            intent.putExtra(PLAYLIST_CREATOR_NICKNAME, playlistCreatorNickname);
            String playlistCreatorAvatarUrl = "";
            intent.putExtra(PLAYLIST_CREATOR_AVATARURL, playlistCreatorAvatarUrl);
            long playlistId = bean.getId();
            intent.putExtra(PLAYLIST_ID, playlistId);
            startActivity(intent);
        }
    };

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
        hideDialog();
        LogUtil.d(TAG, "onGetTopListSuccess : " + bean);
        list.addAll(bean.getList());
        adapter.notifyDataSetChanged(list);
    }

    @Override
    public void onGetTopListFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetTopListFail : " + e);
        ToastUtils.show(e);
    }

    @Override
    public void onGetPlayListSuccess(RecommendPlayListBean bean) {

    }

    @Override
    public void onGetPlayListFail(String e) {

    }

    @Override
    public void onGetPlaylistDetailSuccess(PlaylistDetailBean bean) {

    }

    @Override
    public void onGetPlaylistDetailFail(String s) {

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
