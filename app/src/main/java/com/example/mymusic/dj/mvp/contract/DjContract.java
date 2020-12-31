package com.example.mymusic.dj.mvp.contract;

import com.example.mymusic.base.BaseModel;
import com.example.mymusic.base.BasePresenter;
import com.example.mymusic.base.BaseView;
import com.example.mymusic.dj.bean.DjCategoryRecommendBean;
import com.example.mymusic.dj.bean.DjCatelistBean;
import com.example.mymusic.dj.bean.DjDetailBean;
import com.example.mymusic.dj.bean.DjPaygiftBean;
import com.example.mymusic.dj.bean.DjProgramBean;
import com.example.mymusic.dj.bean.DjRecommendBean;
import com.example.mymusic.dj.bean.DjRecommendTypeBean;
import com.example.mymusic.dj.bean.DjSubBean;

import io.reactivex.Observable;

public interface DjContract {
    interface View extends BaseView {
        void onGetDjRecommendSuccess(DjRecommendBean bean);

        void onGetDjRecommendFail(String e);

        void onGetDjPaygiftSuccess(DjPaygiftBean bean);

        void onGetDjPaygiftFail(String e);

        void onGetDjRecommendTypeSuccess(DjRecommendTypeBean bean);

        void onGetDjRecommendTypeFail(String e);

        void onGetDjCategoryRecommendSuccess(DjCategoryRecommendBean bean);

        void onGetDjCategoryRecommendFail(String e);

        void onGetDjCatelistSuccess(DjCatelistBean bean);

        void onGetDjCatelistFail(String e);

        void onSubDjSuccess(DjSubBean bean);

        void onSubDjFail(String e);

        void onGetDjProgramSuccess(DjProgramBean bean);

        void onGetDjProgramFail(String e);

        void onGetDjDetailSuccess(DjDetailBean bean);

        void onGetDjDetailFail(String e);
    }

    interface Model extends BaseModel {
        Observable<DjRecommendBean> getDjRecommend();

        Observable<DjPaygiftBean> getDjPaygift(int limit, int offset);

        Observable<DjRecommendTypeBean> getDjRecommendType(int type);

        Observable<DjCategoryRecommendBean> getDjCategoryRecommend();

        Observable<DjCatelistBean> getDjCatelist();

        Observable<DjSubBean> subDj(long rid, int isSub);

        Observable<DjProgramBean> getDjProgram(long rid);

        Observable<DjDetailBean> getDjDetail(long rid);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getDjRecommend();

        public abstract void getDjPaygift(int limit, int offset);

        public abstract void getDjRecommendType(int type);

        public abstract void getDjCategoryRecommend();

        public abstract void getDjCatelist();

        public abstract void subDj(long rid, int isSub);

        public abstract void getDjProgram(long rid);

        public abstract void getDjDetail(long rid);
    }
}
