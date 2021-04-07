 package com.example.mymusic.api;

import com.example.mymusic.dj.bean.DjCategoryRecommendBean;
import com.example.mymusic.dj.bean.DjCatelistBean;
import com.example.mymusic.dj.bean.DjDetailBean;
import com.example.mymusic.dj.bean.DjPaygiftBean;
import com.example.mymusic.dj.bean.DjProgramBean;
import com.example.mymusic.dj.bean.DjRecommendBean;
import com.example.mymusic.dj.bean.DjRecommendTypeBean;
import com.example.mymusic.dj.bean.DjSubBean;
import com.example.mymusic.login.bean.LoginBean;
import com.example.mymusic.main.bean.AlbumSublistBean;
import com.example.mymusic.main.bean.ArtistSublistBean;
import com.example.mymusic.main.bean.BannerBean;
import com.example.mymusic.main.bean.CatlistBean;
import com.example.mymusic.main.bean.DailyRecommendBean;
import com.example.mymusic.main.bean.HighQualityPlayListBean;
import com.example.mymusic.main.bean.LikeListBean;
import com.example.mymusic.main.bean.LogoutBean;
import com.example.mymusic.main.bean.MainEventBean;
import com.example.mymusic.main.bean.MainRecommendPlayListBean;
import com.example.mymusic.main.bean.MvSublistBean;
import com.example.mymusic.main.bean.MyFmBean;
import com.example.mymusic.main.bean.PlayModeIntelligenceBean;
import com.example.mymusic.main.bean.PlaylistDetailBean;
import com.example.mymusic.main.bean.RecommendPlayListBean;
import com.example.mymusic.main.bean.TopListBean;
import com.example.mymusic.manager.bean.MusicCanPlayBean;
import com.example.mymusic.personal.bean.UserDetailBean;
import com.example.mymusic.personal.bean.UserEventBean;
import com.example.mymusic.personal.bean.UserPlaylistBean;
import com.example.mymusic.search.bean.AlbumSearchBean;
import com.example.mymusic.search.bean.FeedSearchBean;
import com.example.mymusic.search.bean.HotSearchDetailBean;
import com.example.mymusic.search.bean.PlayListSearchBean;
import com.example.mymusic.search.bean.RadioSearchBean;
import com.example.mymusic.search.bean.SimiSingerBean;
import com.example.mymusic.search.bean.SingerAblumSearchBean;
import com.example.mymusic.search.bean.SingerDescriptionBean;
import com.example.mymusic.search.bean.SingerSearchBean;
import com.example.mymusic.search.bean.SingerSongSearchBean;
import com.example.mymusic.search.bean.SongSearchBean;
import com.example.mymusic.search.bean.SynthesisSearchBean;
import com.example.mymusic.search.bean.UserSearchBean;
import com.example.mymusic.song.bean.CommentLikeBean;
import com.example.mymusic.song.bean.LikeMusicBean;
import com.example.mymusic.song.bean.LyricBean;
import com.example.mymusic.song.bean.MusicCommentBean;
import com.example.mymusic.song.bean.PlayListCommentBean;
import com.example.mymusic.song.bean.SongDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    String BASE_URL = "http://192.168.43.193:3000/";

    @GET("login/cellphone")
    Observable<LoginBean> login(@Query("phone") String phone, @Query("password") String password);

    @GET("logout")
    Observable<LogoutBean> logout();

    @GET("banner")
    Observable<BannerBean> getBanner(@Query("type") String type);

    @GET("recommend/resource")
    Observable<MainRecommendPlayListBean> getRecommendPlayList();

    @GET("recommend/songs")
    Observable<DailyRecommendBean> getDailyRecommend();

    @GET("toplist")
    Observable<TopListBean> getTopList();

    @GET("dj/recommend")
    Observable<DjRecommendBean> getRadioRecommend();

    @GET("dj/recommend/type")
    Observable<DjRecommendTypeBean> getDjRecommend(@Query("type") int type);

    @GET("top/playlist")
    Observable<RecommendPlayListBean> getPlayList(@Query("cat") String type, @Query("limit") int limit);

    @GET("top/playlist/highquality")
    Observable<HighQualityPlayListBean> getHighquality(@Query("limit") int limit, @Query("before") long before);

    @GET("playlist/catlist")
    Observable<CatlistBean> getCatlist();

    @GET("playlist/detail")
    Observable<PlaylistDetailBean> getPlaylistDetail(@Query("id") long id);

    @GET("check/music")
    Observable<MusicCanPlayBean> getMusicCanPlay(@Query("id") long id);

    @GET("user/playlist")
    Observable<UserPlaylistBean> getUserPlaylist(@Query("uid") long uid);

    @GET("user/event")
    Observable<UserEventBean> getUserEvent(@Query("uid") long uid, @Query("limit") int limit, @Query("lasttime") long time);

    @GET("user/detail")
    Observable<UserDetailBean> getUserDetail(@Query("uid") long uid);

    @GET("search/hot/detail")
    Observable<HotSearchDetailBean> getSearchHotDetail();

    @GET("search")
    Observable<SongSearchBean> getSongSearch(@Query("keywords") String keywords, @Query("type") int type);

    @GET("search")
    Observable<FeedSearchBean> getFeedSearch(@Query("keywords") String keywords, @Query("type") int type);

    @GET("search")
    Observable<SingerSearchBean> getSingerSearch(@Query("keywords") String keywords, @Query("type") int type);

    @GET("search")
    Observable<AlbumSearchBean> getAlbumSearch(@Query("keywords") String keywords, @Query("type") int type);

    @GET("search")
    Observable<PlayListSearchBean> getPlayListSearch(@Query("keywords") String keywords, @Query("type") int type);

    @GET("search")
    Observable<RadioSearchBean> getRadioSearch(@Query("keywords") String keywords, @Query("type") int type);

    @GET("search")
    Observable<UserSearchBean> getUserSearch(@Query("keywords") String keywords, @Query("type") int type);

    @GET("search")
    Observable<SynthesisSearchBean> getSynthesisSearch(@Query("keywords") String keywords, @Query("type") int type);

    @GET("artists")
    Observable<SingerSongSearchBean> getSingerHotSong(@Query("id") long id);

    @GET("artist/album")
    Observable<SingerAblumSearchBean> getSingerAlbum(@Query("id") long id);

    @GET("artist/desc")
    Observable<SingerDescriptionBean> getSingerDesc(@Query("id") long id);

    @GET("simi/artist")
    Observable<SimiSingerBean> getSimiSinger(@Query("id") long id);

    @GET("likelist")
    Observable<LikeListBean> getLikeList(@Query("uid") long uid);

    @GET("song/detail")
    Observable<SongDetailBean> getSongDetail(@Query("ids") long ids);

    @GET("like")
    Observable<LikeMusicBean> likeMusice(@Query("id") long id);

    @GET("comment/music")
    Observable<MusicCommentBean> getMusicComment(@Query("id") long id);

    @GET("comment/like")
    Observable<CommentLikeBean> likeComment(@Query("id") long id, @Query("cid") long cid, @Query("t") int t, @Query("type") int type);

    @GET("playmode/intelligence/list")
    Observable<PlayModeIntelligenceBean> getIntelligenceList(@Query("id") long id, @Query("pid") long pid);

    @GET("album/sublist")
    Observable<AlbumSublistBean> getAlbumSublist();

    @GET("artist/sublist")
    Observable<ArtistSublistBean> getArtistSublist();

    @GET("mv/sublist")
    Observable<MvSublistBean> getMvSublist();

    @GET("personal_fm")
    Observable<MyFmBean> getMyFm();

    @GET("event")
    Observable<MainEventBean> getMainEvent();

    @GET("lyric")
    Observable<LyricBean> getLyric(@Query("id") long id);

    @GET("comment/playlist")
    Observable<PlayListCommentBean> getPlaylistComment(@Query("id") long id);

    @GET("dj/paygift")
    Observable<DjPaygiftBean> getDjPaygift(@Query("limit") int limit, @Query("offset") int offset);

    @GET("dj/category/recommend")
    Observable<DjCategoryRecommendBean> getDjCategoryRecommend();

    @GET("dj/catelist")
    Observable<DjCatelistBean> getDjCatelist();

    @GET("dj/sub")
    Observable<DjSubBean> subDj(@Query("rid") long rid, @Query("t") int isSub);

    @GET("dj/program")
    Observable<DjProgramBean> getDjProgram(@Query("rid") long rid);

    @GET("dj/detail")
    Observable<DjDetailBean> getDjDetail(@Query("rid") long rid);
}
