package com.example.mymusic.main.mvp.contract;

import com.example.mymusic.main.bean.LikeListBean;
import com.example.mymusic.main.bean.LogoutBean;
import com.example.mymusic.base.BaseModel;
import com.example.mymusic.base.BasePresenter;
import com.example.mymusic.base.BaseView;

import io.reactivex.Observable;


public interface MainContract {
    interface View extends BaseView {
        void onLogoutSuccess();

        void onLogoutFail(String e);

        void onGetLikeListSuccess(LikeListBean bean);

        void onGetLikeListFail(String e);
    }

    interface Model extends BaseModel {
        Observable<LogoutBean> logout();

        Observable<LikeListBean> getLikeList(long uid);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void logout();

        public abstract void getLikeList(long uid);
    }
}
