package com.example.mymusic.main.mvp.contract;

import com.example.mymusic.main.bean.MainEventBean;
import com.example.mymusic.base.BaseModel;
import com.example.mymusic.base.BasePresenter;
import com.example.mymusic.base.BaseView;

import io.reactivex.Observable;

public interface EventContract {
    interface View extends BaseView {
        void onGetMainEventSuccess(MainEventBean bean);

        void onGetMainEventFail(String e);

    }

    interface Model extends BaseModel {
        Observable<MainEventBean> getMainEvent();

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getMainEvent();

    }
}
