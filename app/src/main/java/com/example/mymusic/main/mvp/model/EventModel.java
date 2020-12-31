package com.example.mymusic.main.mvp.model;

import com.example.mymusic.api.ApiEngine;
import com.example.mymusic.main.bean.MainEventBean;
import com.example.mymusic.main.mvp.contract.EventContract;

import io.reactivex.Observable;

public class EventModel implements EventContract.Model {

    @Override
    public Observable<MainEventBean> getMainEvent() {
        return ApiEngine.getInstance().getApiService().getMainEvent();
    }

}
