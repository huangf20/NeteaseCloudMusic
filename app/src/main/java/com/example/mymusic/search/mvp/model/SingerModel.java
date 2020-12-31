package com.example.mymusic.search.mvp.model;

import com.example.mymusic.api.ApiEngine;
import com.example.mymusic.search.bean.FeedSearchBean;
import com.example.mymusic.search.bean.SimiSingerBean;
import com.example.mymusic.search.bean.SingerAblumSearchBean;
import com.example.mymusic.search.bean.SingerDescriptionBean;
import com.example.mymusic.search.bean.SingerSongSearchBean;
import com.example.mymusic.search.mvp.contract.SingerContract;

import io.reactivex.Observable;

public class SingerModel implements SingerContract.Model {
    @Override
    public Observable<SingerSongSearchBean> getSingerHotSong(long id) {
        return ApiEngine.getInstance().getApiService().getSingerHotSong(id);
    }

    @Override
    public Observable<SingerAblumSearchBean> getSingerAlbum(long id) {
        return ApiEngine.getInstance().getApiService().getSingerAlbum(id);
    }

    @Override
    public Observable<FeedSearchBean> getFeedSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getFeedSearch(keywords, type);
    }

    @Override
    public Observable<SingerDescriptionBean> getSingerDesc(long id) {
        return ApiEngine.getInstance().getApiService().getSingerDesc(id);
    }

    @Override
    public Observable<SimiSingerBean> getSimiSinger(long id) {
        return ApiEngine.getInstance().getApiService().getSimiSinger(id);
    }
}
