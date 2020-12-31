package com.example.mymusic.personal.mvp.contract;

import com.example.mymusic.personal.bean.UserDetailBean;
import com.example.mymusic.personal.bean.UserEventBean;
import com.example.mymusic.personal.bean.UserPlaylistBean;
import com.example.mymusic.base.BaseModel;
import com.example.mymusic.base.BasePresenter;
import com.example.mymusic.base.BaseView;

import io.reactivex.Observable;


public interface PersonalContract {
    interface View extends BaseView {
        void onGetUserPlaylistSuccess(UserPlaylistBean bean);

        void onGetUserPlaylistFail(String e);

        void onGetUserEventSuccess(UserEventBean bean);

        void onGetUserEventFail(String e);

        void onGetUserDetailSuccess(UserDetailBean bean);

        void onGetUserDetailFail(String e);
    }

    interface Model extends BaseModel {
        Observable<UserPlaylistBean> getUserPlaylist(long uid);

        Observable<UserEventBean> getUserEvent(long uid, int limit, long lasttime);

        Observable<UserDetailBean> getUserDetail(long uid);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getUserPlaylist(long uid);

        public abstract void getUserEvent(long uid, int limit, long lasttime);

        public abstract void getUserDetail(long uid);
    }
}
