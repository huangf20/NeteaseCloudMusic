package com.example.mymusic.login.mvp.contract;


import com.example.mymusic.login.bean.LoginBean;
import com.example.mymusic.base.BaseModel;
import com.example.mymusic.base.BasePresenter;
import com.example.mymusic.base.BaseView;

import io.reactivex.Observable;

public interface LoginContract {
    interface View extends BaseView {
        void onLoginSuccess(LoginBean bean);

        void onLoginFail(String e);
    }

    interface Model extends BaseModel {
        Observable<LoginBean> login(String phone, String password);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void login(String phone, String password);
    }
}
