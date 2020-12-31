package com.example.mymusic.search.mvp.model;

import com.example.mymusic.api.ApiEngine;
import com.example.mymusic.search.bean.AlbumSearchBean;
import com.example.mymusic.search.bean.FeedSearchBean;
import com.example.mymusic.search.bean.HotSearchDetailBean;
import com.example.mymusic.search.bean.PlayListSearchBean;
import com.example.mymusic.search.bean.RadioSearchBean;
import com.example.mymusic.search.bean.SingerSearchBean;
import com.example.mymusic.search.bean.SongSearchBean;
import com.example.mymusic.search.bean.SynthesisSearchBean;
import com.example.mymusic.search.bean.UserSearchBean;
import com.example.mymusic.search.mvp.contract.SearchContract;

import io.reactivex.Observable;

public class SearchModel implements SearchContract.Model {
    @Override
    public Observable<HotSearchDetailBean> getHotSearchDetail() {
        return ApiEngine.getInstance().getApiService().getSearchHotDetail();
    }

    @Override
    public Observable<SongSearchBean> getSongSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getSongSearch(keywords, type);
    }

    @Override
    public Observable<FeedSearchBean> getFeedSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getFeedSearch(keywords, type);
    }

    @Override
    public Observable<SingerSearchBean> getSingerSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getSingerSearch(keywords, type);
    }

    @Override
    public Observable<AlbumSearchBean> getAlbumSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getAlbumSearch(keywords, type);
    }

    @Override
    public Observable<PlayListSearchBean> getPlayListSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getPlayListSearch(keywords, type);
    }

    @Override
    public Observable<RadioSearchBean> getRadioSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getRadioSearch(keywords, type);
    }

    @Override
    public Observable<UserSearchBean> getUserSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getUserSearch(keywords, type);
    }

    @Override
    public Observable<SynthesisSearchBean> getSynthesisSearch(String keywords, int type) {
        return ApiEngine.getInstance().getApiService().getSynthesisSearch(keywords, type);
    }

}
