package com.example.mymusic.main.mvp.model;

import com.example.mymusic.api.ApiEngine;
import com.example.mymusic.main.bean.LikeListBean;
import com.example.mymusic.main.bean.LogoutBean;
import com.example.mymusic.main.mvp.contract.MainContract;

import io.reactivex.Observable;


public class MainModel implements MainContract.Model {

    @Override
    public Observable<LogoutBean> logout() {
        return ApiEngine.getInstance().getApiService().logout();
    }

    @Override
    public Observable<LikeListBean> getLikeList(long uid) {
        return ApiEngine.getInstance().getApiService().getLikeList(uid);
    }
}
