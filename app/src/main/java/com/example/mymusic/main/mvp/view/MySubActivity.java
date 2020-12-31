package com.example.mymusic.main.mvp.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mymusic.main.adapter.MultiFragmentPagerAdapter;
import com.example.mymusic.main.bean.AlbumSublistBean;
import com.example.mymusic.main.bean.ArtistSublistBean;
import com.example.mymusic.main.bean.MvSublistBean;
import com.example.mymusic.main.bean.MyFmBean;
import com.example.mymusic.main.bean.PlayModeIntelligenceBean;
import com.example.mymusic.main.mvp.contract.MineContract;
import com.example.mymusic.main.mvp.presenter.MinePresenter;
import com.example.mymusic.main.mvp.view.fragments.MyAlbumSubFragment;
import com.example.mymusic.main.mvp.view.fragments.MyMvSubFragment;
import com.example.mymusic.personal.bean.UserPlaylistBean;
import com.example.mymusic.widget.BottomSongPlayBar;
import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseFragment;
import com.example.mymusic.main.mvp.view.fragments.MyArtistSubFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的收藏界面
 */
public class MySubActivity extends BaseActivity<MinePresenter> implements MineContract.View {
    private static final String TAG = "MySubActivity";

    @BindView(R.id.tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.vp_container)
    ViewPager vpFragment;
    @BindView(R.id.bottom_controller)
    BottomSongPlayBar bottomSongPlayBar;

    private List<BaseFragment> fragments = new ArrayList<>();
    private MultiFragmentPagerAdapter pagerAdapter;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_sub);
        ImmersionBar.with(this)
                .transparentBar()
                .statusBarColor(R.color.colorPrimary)
                .statusBarDarkFont(false)
                .init();

        pagerAdapter = new MultiFragmentPagerAdapter(getSupportFragmentManager());
        fragments.add(new MyAlbumSubFragment());
        fragments.add(new MyArtistSubFragment());
        fragments.add(new MyMvSubFragment());
        pagerAdapter.init(fragments);
    }

    @Override
    protected MinePresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initModule() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        setBackBtn(getString(R.string.colorWhite));
        setLeftTitleText(getString(R.string.my_collection), getString(R.string.colorWhite));

        vpFragment.setAdapter(pagerAdapter);
        vpFragment.setOffscreenPageLimit(3);
        tabLayout.setViewPager(vpFragment);
        tabLayout.setCurrentTab(1);


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
