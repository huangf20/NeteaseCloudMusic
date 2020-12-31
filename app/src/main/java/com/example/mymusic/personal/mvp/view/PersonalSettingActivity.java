package com.example.mymusic.personal.mvp.view;

import android.os.Bundle;
import android.view.View;

import com.example.mymusic.personal.bean.UserDetailBean;
import com.example.mymusic.personal.bean.UserEventBean;
import com.example.mymusic.personal.bean.UserPlaylistBean;
import com.example.mymusic.personal.mvp.presenter.PersonalPresenter;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.personal.mvp.contract.PersonalContract;

public class PersonalSettingActivity extends BaseActivity<PersonalPresenter> implements PersonalContract.View {

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_setting);
    }

    @Override
    protected PersonalPresenter onCreatePresenter() {
        return null;
    }

    @Override
    protected void initModule() {

    }

    @Override
    protected void initData() {

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
    public void onGetUserEventSuccess(UserEventBean bean) {

    }

    @Override
    public void onGetUserEventFail(String e) {

    }

    @Override
    public void onGetUserDetailSuccess(UserDetailBean bean) {

    }

    @Override
    public void onGetUserDetailFail(String e) {

    }
}
