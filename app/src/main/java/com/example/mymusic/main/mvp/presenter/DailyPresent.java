package com.example.mymusic.main.mvp.presenter;

import com.example.mymusic.main.mvp.contract.DailyContract;
import com.example.mymusic.main.mvp.model.DailyModel;

/**
 * Create by huangf20
 * on 2021/4/26
 */
public class DailyPresent extends DailyContract.Presenter {
    public DailyPresent(DailyContract.View v) {
        this.mView=v;
        mModel=new DailyModel();
    }
}
