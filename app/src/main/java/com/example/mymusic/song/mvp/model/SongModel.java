package com.example.mymusic.song.mvp.model;


import com.example.mymusic.api.ApiEngine;
import com.example.mymusic.main.bean.LikeListBean;
import com.example.mymusic.song.bean.CommentLikeBean;
import com.example.mymusic.song.bean.LikeMusicBean;
import com.example.mymusic.song.bean.LyricBean;
import com.example.mymusic.song.bean.MusicCommentBean;
import com.example.mymusic.song.bean.PlayListCommentBean;
import com.example.mymusic.song.bean.SongDetailBean;
import com.example.mymusic.song.mvp.contract.SongContract;

import io.reactivex.Observable;

public class SongModel implements SongContract.Model {
    @Override
    public Observable<SongDetailBean> getSongDetail(long ids) {
        return ApiEngine.getInstance().getApiService().getSongDetail(ids);
    }

    @Override
    public Observable<LikeMusicBean> likeMusic(long id) {
        return ApiEngine.getInstance().getApiService().likeMusice(id);
    }

    @Override
    public Observable<LikeListBean> getLikeList(long uid) {
        return ApiEngine.getInstance().getApiService().getLikeList(uid);
    }

    @Override
    public Observable<MusicCommentBean> getMusicComment(long id) {
        return ApiEngine.getInstance().getApiService().getMusicComment(id);
    }

    @Override
    public Observable<CommentLikeBean> likeComment(long id, long cid, int t, int type) {
        return ApiEngine.getInstance().getApiService().likeComment(id, cid, t, type);
    }

    @Override
    public Observable<LyricBean> getLyric(long id) {
        return ApiEngine.getInstance().getApiService().getLyric(id);
    }

    @Override
    public Observable<PlayListCommentBean> getPlaylistComment(long id) {
        return ApiEngine.getInstance().getApiService().getPlaylistComment(id);
    }
}
