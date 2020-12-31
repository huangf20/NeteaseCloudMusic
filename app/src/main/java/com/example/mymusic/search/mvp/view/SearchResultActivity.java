package com.example.mymusic.search.mvp.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.example.mymusic.database.SearchHistoryDaoOp;
import com.example.mymusic.search.bean.AlbumSearchBean;
import com.example.mymusic.search.bean.FeedSearchBean;
import com.example.mymusic.search.bean.HotSearchDetailBean;
import com.example.mymusic.search.bean.PlayListSearchBean;
import com.example.mymusic.search.bean.RadioSearchBean;
import com.example.mymusic.search.bean.SearchHistoryBean;
import com.example.mymusic.search.bean.SingerSearchBean;
import com.example.mymusic.search.bean.SongSearchBean;
import com.example.mymusic.search.bean.SynthesisSearchBean;
import com.example.mymusic.search.bean.UserSearchBean;
import com.example.mymusic.search.event.KeywordsEvent;
import com.example.mymusic.search.mvp.contract.SearchContract;
import com.example.mymusic.search.mvp.presenter.SearchPresenter;
import com.example.mymusic.search.mvp.view.fragments.AlbumSearchFragment;
import com.example.mymusic.search.mvp.view.fragments.FeedSearchFragment;
import com.example.mymusic.search.mvp.view.fragments.PlayListSearchFragment;
import com.example.mymusic.search.mvp.view.fragments.RadioSearchFragment;
import com.example.mymusic.search.mvp.view.fragments.SingerSearchFragment;
import com.example.mymusic.search.mvp.view.fragments.SongSearchFragment;
import com.example.mymusic.search.mvp.view.fragments.UserSearchFragment;
import com.example.mymusic.widget.SearchEditText;
import com.flyco.tablayout.SlidingTabLayout;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.base.BaseFragment;
import com.example.mymusic.main.adapter.MultiFragmentPagerAdapter;
import com.example.mymusic.util.ClickUtil;
import com.example.mymusic.util.LogUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.mymusic.search.mvp.view.SearchActivity.KEYWORDS;

/**
 * 搜索结果界面，下面有许多的Fragment
 */
public class SearchResultActivity extends BaseActivity<SearchPresenter> implements SearchContract.View {
    private static final String TAG = "SearchResultActivity";

    @BindView(R.id.tablayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.vp_container)
    ViewPager vpFragment;
    @BindView(R.id.et_search)
    SearchEditText etSearch;

    private List<BaseFragment> fragments = new ArrayList<>();
    private List<SearchHistoryBean> stringList = new ArrayList<>();
    private MultiFragmentPagerAdapter pagerAdapter;
    private String keywords;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search_result);
        ImmersionBar.with(this)
                .transparentBar()
                .statusBarColor(R.color.colorPrimary)
                .statusBarDarkFont(false)
                .init();

        pagerAdapter = new MultiFragmentPagerAdapter(getSupportFragmentManager());
        fragments.add(new SongSearchFragment("单曲"));
        fragments.add(new FeedSearchFragment("视频"));
        fragments.add(new SingerSearchFragment("歌手"));
        fragments.add(new AlbumSearchFragment("专辑"));
        fragments.add(new PlayListSearchFragment("歌单"));
        fragments.add(new RadioSearchFragment("主播电台"));
        fragments.add(new UserSearchFragment("用户"));
        pagerAdapter.init(fragments);
    }

    @Override
    protected SearchPresenter onCreatePresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void initModule() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        setBackBtn(getString(R.string.colorWhite));
        setRightSearchButton();
        setEditText(getString(R.string.colorTransWithe));

        vpFragment.setAdapter(pagerAdapter);
        vpFragment.setOffscreenPageLimit(6);
        tabLayout.setViewPager(vpFragment);
        tabLayout.setCurrentTab(0);

        //从GreenDao里拿搜索历史
        if (SearchHistoryDaoOp.queryAll(this) != null) {
            stringList = SearchHistoryDaoOp.queryAll(this);
        }

        if (getIntent().getStringExtra(KEYWORDS) != null) {
            keywords = getIntent().getStringExtra(KEYWORDS);
            EventBus.getDefault().postSticky(new KeywordsEvent(keywords));
            etSearch.setText(keywords);
        }
    }

    @Override
    @OnClick({R.id.btn_search})
    public void onClick(View v) {
        if (ClickUtil.isFastClick(1000, v)) {
            return;
        }
        switch (v.getId()) {
            case R.id.btn_search:
                String keywords = etSearch.getKeyWords();
                if (!TextUtils.isEmpty(keywords)) {
                    searchSong(keywords);
                } else {
                    ToastUtils.show("请输入关键字！");
                }
                break;
        }
    }

    private void searchSong(String keywords) {
        stringList.add(new SearchHistoryBean(keywords));
        if (stringList.size() > 10) {
            stringList.remove(0);
        }
        for (int i = 0; i < stringList.size() - 1; i++) {
            //去重
            if (stringList.get(i).getKeyowrds().equals(keywords)) {
                stringList.remove(i);
                break;
            }
        }
        SearchHistoryDaoOp.saveData(this, stringList);

        EventBus.getDefault().postSticky(new KeywordsEvent(keywords));
    }

    public int getPosition() {
        LogUtil.d(TAG, "getCurrentTab : " + tabLayout.getCurrentTab());
        return tabLayout.getCurrentTab();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    }

    @Override
    public void onGetRadioSearchFail(String e) {

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
