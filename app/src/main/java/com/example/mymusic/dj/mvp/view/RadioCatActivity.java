package com.example.mymusic.dj.mvp.view;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.dj.adapter.DjCateAdapter;
import com.example.mymusic.dj.bean.DjCategoryRecommendBean;
import com.example.mymusic.dj.bean.DjCatelistBean;
import com.example.mymusic.dj.bean.DjDetailBean;
import com.example.mymusic.dj.bean.DjPaygiftBean;
import com.example.mymusic.dj.bean.DjProgramBean;
import com.example.mymusic.dj.bean.DjRecommendBean;
import com.example.mymusic.dj.bean.DjRecommendTypeBean;
import com.example.mymusic.dj.bean.DjSubBean;
import com.example.mymusic.dj.mvp.presenter.DjPresenter;
import com.example.mymusic.util.LogUtil;
import com.gyf.immersionbar.ImmersionBar;
import com.hjq.toast.ToastUtils;
import com.example.mymusic.R;
import com.example.mymusic.dj.mvp.contract.DjContract;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioCatActivity extends BaseActivity<DjPresenter> implements DjContract.View {
    private static final String TAG = "RadioCatActivity";

    @BindView(R.id.rv_cate)
    RecyclerView rvCate;

    private DjCateAdapter adapter;


    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_radio_cat);

        ImmersionBar.with(this)
                .transparentBar()
                .statusBarColor(R.color.colorPrimary)
                .statusBarDarkFont(false)
                .init();
    }

    @Override
    protected DjPresenter onCreatePresenter() {
        return new DjPresenter(this);
    }

    @Override
    protected void initModule() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        setBackBtn(getString(R.string.colorWhite));
        setLeftTitleText(getString(R.string.cate_dj), getString(R.string.colorWhite));

        adapter = new DjCateAdapter(this);
        rvCate.setLayoutManager(new GridLayoutManager(this, 2));
        rvCate.setAdapter(adapter);

        showDialog();
        mPresenter.getDjCatelist();
    }

    @Override
    public void onClick(View v) {

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
        hideDialog();
        LogUtil.d(TAG, "onGetDjCatelistSuccess :" + bean);
        if (bean != null) {
            adapter.notifyDataSetChanged(bean.getCategories());
        }
    }

    @Override
    public void onGetDjCatelistFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetDjCatelistFail :" + e);
        ToastUtils.show(e);
    }

    @Override
    public void onSubDjSuccess(DjSubBean bean) {

    }

    @Override
    public void onSubDjFail(String e) {

    }

    @Override
    public void onGetDjProgramSuccess(DjProgramBean bean) {

    }

    @Override
    public void onGetDjProgramFail(String e) {

    }

    @Override
    public void onGetDjDetailSuccess(DjDetailBean bean) {

    }

    @Override
    public void onGetDjDetailFail(String e) {

    }
}
