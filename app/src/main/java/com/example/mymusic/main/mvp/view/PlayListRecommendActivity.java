package com.example.mymusic.main.mvp.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mymusic.main.adapter.MultiFragmentPagerAdapter;
import com.example.mymusic.main.bean.BannerBean;
import com.example.mymusic.main.bean.DailyRecommendBean;
import com.example.mymusic.main.bean.HighQualityPlayListBean;
import com.example.mymusic.main.bean.MainRecommendPlayListBean;
import com.example.mymusic.main.bean.PlaylistDetailBean;
import com.example.mymusic.main.bean.RecommendPlayListBean;
import com.example.mymusic.main.bean.TopListBean;
import com.example.mymusic.main.mvp.contract.WowContract;
import com.example.mymusic.main.mvp.presenter.WowPresenter;
import com.example.mymusic.main.mvp.view.fragments.HighQualityPlayListFragment;
import com.example.mymusic.main.mvp.view.fragments.PlayListFragment;
import com.example.mymusic.manager.bean.MusicCanPlayBean;
import com.example.mymusic.widget.PlayListRecommendViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.gyf.immersionbar.ImmersionBar;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 歌单界面
 */
public class PlayListRecommendActivity extends BaseActivity<WowPresenter> implements WowContract.View {
    private static final String TAG = "PlayListRecommendActivity";

    @BindView(R.id.tab_type)
    SlidingTabLayout tabTitle;
    @BindView(R.id.vp_container)
    PlayListRecommendViewPager vpFragment;

    private List<BaseFragment> fragments = new ArrayList<>();
    private MultiFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_play_list_recommend);
        ImmersionBar.with(this)
                .transparentBar()
                .statusBarColor(R.color.colorPrimary)
                .statusBarDarkFont(false)
                .init();

        pagerAdapter = new MultiFragmentPagerAdapter(getSupportFragmentManager());
        fragments.add(new HighQualityPlayListFragment("精品"));
        fragments.add(new PlayListFragment("ACG"));
        fragments.add(new PlayListFragment("影视原声"));
        fragments.add(new PlayListFragment("摇滚"));
        fragments.add(new PlayListFragment("华语"));
        fragments.add(new PlayListFragment("经典"));
        fragments.add(new PlayListFragment("电子"));
        pagerAdapter.init(fragments);
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
        setLeftTitleText(getString(R.string.playlist_playground), getString(R.string.colorWhite));
        vpFragment.setAdapter(pagerAdapter);
        vpFragment.setOffscreenPageLimit(6);
        vpFragment.setCurrentItem(0);
        pagerAdapter.getItem(0).setUserVisibleHint(true);
        tabTitle.setViewPager(vpFragment);
        initListener();


//        showDialog();
//        mPresenter.getPlayList("ACG", 3);
    }

    private void initListener() {
        tabTitle.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
//                    playListPager.setVisibility(View.VISIBLE);
                } else {
//                    playListPager.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vpFragment.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
//                    playListPager.setVisibility(View.VISIBLE);
                } else {
//                    playListPager.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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
