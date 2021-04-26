package com.example.mymusic.main.mvp.contract;

import com.example.mymusic.base.BaseModel;
import com.example.mymusic.base.BasePresenter;
import com.example.mymusic.base.BaseView;

/**
 * Create by huangf20
 * on 2021/4/26
 */
public interface DailyContract {
    interface View extends BaseView{}
    interface Model extends BaseModel {}
    abstract class Presenter extends BasePresenter<DailyContract.View, DailyContract.Model> {}
}
