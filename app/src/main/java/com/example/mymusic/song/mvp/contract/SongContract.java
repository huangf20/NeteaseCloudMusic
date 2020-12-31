package com.example.mymusic.song.mvp.contract;

import com.example.mymusic.base.BaseModel;
import com.example.mymusic.base.BasePresenter;
import com.example.mymusic.base.BaseView;
import com.example.mymusic.main.bean.LikeListBean;
import com.example.mymusic.song.bean.CommentLikeBean;
import com.example.mymusic.song.bean.LikeMusicBean;
import com.example.mymusic.song.bean.LyricBean;
import com.example.mymusic.song.bean.MusicCommentBean;
import com.example.mymusic.song.bean.PlayListCommentBean;
import com.example.mymusic.song.bean.SongDetailBean;

import io.reactivex.Observable;

public interface SongContract {
    interface View extends BaseView {
        void onGetSongDetailSuccess(SongDetailBean bean);

        void onGetSongDetailFail(String e);

        void onLikeMusicSuccess(LikeMusicBean bean);

        void onLikeMusicFail(String e);

        void onGetLikeListSuccess(LikeListBean bean);

        void onGetLikeListFail(String e);

        void onGetMusicCommentSuccess(MusicCommentBean bean);

        void onGetMusicCommentFail(String e);

        void onLikeCommentSuccess(CommentLikeBean bean);

        void onLikeCommentFail(String e);

        void onGetLyricSuccess(LyricBean bean);

        void onGetLyricFail(String e);

        void onGetPlaylistCommentSuccess(PlayListCommentBean bean);

        void onGetPlaylistCommentFail(String e);
    }

    interface Model extends BaseModel {
        Observable<SongDetailBean> getSongDetail(long ids);

        Observable<LikeMusicBean> likeMusic(long id);

        Observable<LikeListBean> getLikeList(long uid);

        Observable<MusicCommentBean> getMusicComment(long id);

        Observable<CommentLikeBean> likeComment(long id, long cid, int t, int type);

        Observable<LyricBean> getLyric(long id);

        Observable<PlayListCommentBean> getPlaylistComment(long id);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getSongDetail(long ids);

        public abstract void likeMusic(long id);

        public abstract void getLikeList(long uid);

        public abstract void getMusicComment(long id);

        public abstract void likeComment(long id, long cid, int t, int type);

        public abstract void getLyric(long id);

        public abstract void getPlaylistComment(long id);
    }
}
