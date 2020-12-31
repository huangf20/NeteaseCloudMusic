package com.example.mymusic.personal.mvp.model;

import com.example.mymusic.api.ApiEngine;
import com.example.mymusic.personal.bean.UserDetailBean;
import com.example.mymusic.personal.bean.UserEventBean;
import com.example.mymusic.personal.bean.UserPlaylistBean;
import com.example.mymusic.personal.mvp.contract.PersonalContract;

import io.reactivex.Observable;

public class PersonalModel implements PersonalContract.Model {

    @Override
    public Observable<UserPlaylistBean> getUserPlaylist(long uid) {
        return ApiEngine.getInstance().getApiService().getUserPlaylist(uid);
    }

    @Override
    public Observable<UserEventBean> getUserEvent(long uid, int limit, long lasttime) {
        return ApiEngine.getInstance().getApiService().getUserEvent(uid, limit, lasttime);
    }

    @Override
    public Observable<UserDetailBean> getUserDetail(long uid) {
        return ApiEngine.getInstance().getApiService().getUserDetail(uid);
    }
}
