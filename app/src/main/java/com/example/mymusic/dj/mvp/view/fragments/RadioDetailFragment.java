package com.example.mymusic.dj.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mymusic.App;
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
import com.example.mymusic.personal.mvp.view.PersonalInfoActivity;
import com.example.mymusic.search.bean.UserSearchBean;
import com.example.mymusic.util.ClickUtil;
import com.example.mymusic.util.GsonUtil;
import com.example.mymusic.util.LogUtil;
import com.hjq.toast.ToastUtils;
import com.example.mymusic.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class RadioDetailFragment extends BaseFragment<DjPresenter> implements DjContract.View {
    private static final String TAG = "RadioDetailFragment";

    @BindView(R.id.iv_dj_avatar)
    CircleImageView ivDjAvatar;
    @BindView(R.id.tv_dj_name)
    TextView tvDjName;
    @BindView(R.id.tv_dj_des)
    TextView tvDjDes;
    @BindView(R.id.tv_dj_detail)
    TextView tvRadioDetail;
    @BindView(R.id.tv_cat)
    TextView tvCat;

    private long rid = 0;
    private DjDetailBean.DjRadioBean.DjBean userBean;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onGetRidEvent(RidEvent event) {
        LogUtil.d(TAG, "onGetRidEvent :" + event);
        rid = event.getRid();
    }

    public RadioDetailFragment() {
        setFragmentTitle(App.getContext().getString(R.string.dj_detail));
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_radio_detail, container, false);
        ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    protected void initData() {
        if (rid != 0) {
            showDialog();
            mPresenter.getDjDetail(rid);
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
    @OnClick({R.id.rl_dj_info})
    public void onClick(View v) {
        if (ClickUtil.isFastClick(1000, v)) {
            return;
        }
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.rl_dj_info:
                if (userBean != null) {
                    intent.setClass(getContext(), PersonalInfoActivity.class);
                    UserSearchBean.ResultBean.UserprofilesBean userprofilesBean = new UserSearchBean.ResultBean.UserprofilesBean();
                    userprofilesBean.setUserId(userBean.getUserId());
                    userprofilesBean.setBackgroundUrl(userBean.getBackgroundUrl());
                    userprofilesBean.setAvatarUrl(userBean.getAvatarUrl());
                    userprofilesBean.setNickname(userBean.getNickname());
                    intent.putExtra(PersonalInfoActivity.USER_BEAN, GsonUtil.toJson(userprofilesBean));
                    startActivity(intent);
                }
                break;
        }
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

    }

    @Override
    public void onGetDjProgramFail(String e) {

    }

    @Override
    public void onGetDjDetailSuccess(DjDetailBean bean) {
        hideDialog();
        LogUtil.d(TAG, "onGetDjDetailSuccess : " + bean);
        userBean = bean.getDjRadio().getDj();
        Glide.with(this).load(bean.getDjRadio().getDj().getAvatarUrl()).into(ivDjAvatar);
        tvDjName.setText(bean.getDjRadio().getDj().getNickname());
        tvDjDes.setText(bean.getDjRadio().getDj().getSignature());
        tvRadioDetail.setText(bean.getDjRadio().getRcmdText());
        tvCat.setText(bean.getDjRadio().getCategory());
        tvCat.setVisibility(View.VISIBLE);
    }

    @Override
    public void onGetDjDetailFail(String e) {
        hideDialog();
        LogUtil.e(TAG, "onGetDjDetailFail :" + e);
        ToastUtils.show(e);
    }
}
