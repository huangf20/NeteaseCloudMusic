package com.example.mymusic.search.mvp.contract;

import com.example.mymusic.search.bean.AlbumSearchBean;
import com.example.mymusic.search.bean.FeedSearchBean;
import com.example.mymusic.search.bean.HotSearchDetailBean;
import com.example.mymusic.search.bean.PlayListSearchBean;
import com.example.mymusic.search.bean.RadioSearchBean;
import com.example.mymusic.search.bean.SingerSearchBean;
import com.example.mymusic.search.bean.SongSearchBean;
import com.example.mymusic.search.bean.SynthesisSearchBean;
import com.example.mymusic.search.bean.UserSearchBean;
import com.example.mymusic.base.BaseModel;
import com.example.mymusic.base.BasePresenter;
import com.example.mymusic.base.BaseView;

import io.reactivex.Observable;


public interface SearchContract {
    interface View extends BaseView {
        void onGetHotSearchDetailSuccess(HotSearchDetailBean bean);

        void onGetHotSearchDetailFail(String e);

        void onGetSongSearchSuccess(SongSearchBean bean);

        void onGetSongSearchFail(String e);

        void onGetFeedSearchSuccess(FeedSearchBean bean);

        void onGetFeedSearchFail(String e);

        void onGetSingerSearchSuccess(SingerSearchBean bean);

        void onGetSingerSearchFail(String e);

        void onGetAlbumSearchSuccess(AlbumSearchBean bean);

        void onGetAlbumSearchFail(String e);

        void onGetPlayListSearchSuccess(PlayListSearchBean bean);

        void onGetPlayListSearchFail(String e);

        void onGetRadioSearchSuccess(RadioSearchBean bean);

        void onGetRadioSearchFail(String e);

        void onGetUserSearchSuccess(UserSearchBean bean);

        void onGetUserSearchFail(String e);

        void onGetSynthesisSearchSuccess(SynthesisSearchBean bean);

        void onGetSynthesisSearchFail(String e);
    }

    interface Model extends BaseModel {
        Observable<HotSearchDetailBean> getHotSearchDetail();

        Observable<SongSearchBean> getSongSearch(String keywords, int type);

        Observable<FeedSearchBean> getFeedSearch(String keywords, int type);

        Observable<SingerSearchBean> getSingerSearch(String keywords, int type);

        Observable<AlbumSearchBean> getAlbumSearch(String keywords, int type);

        Observable<PlayListSearchBean> getPlayListSearch(String keywords, int type);

        Observable<RadioSearchBean> getRadioSearch(String keywords, int type);

        Observable<UserSearchBean> getUserSearch(String keywords, int type);

        Observable<SynthesisSearchBean> getSynthesisSearch(String keywords, int type);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getHotSearchDetail();

        public abstract void getSongSearch(String keywords, int type);

        public abstract void getFeedSearch(String keywords, int type);

        public abstract void getSingerSearch(String keywords, int type);

        public abstract void getAlbumSearch(String keywords, int type);

        public abstract void getPlayListSearch(String keywords, int type);

        public abstract void getRadioSearch(String keywords, int type);

        public abstract void getUserSearch(String keywords, int type);

        public abstract void getSynthesisSearch(String keywords, int type);
    }
}
