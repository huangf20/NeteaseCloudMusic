package com.example.mymusic.main.mvp.presenter;

import com.example.mymusic.main.bean.LikeListBean;
import com.example.mymusic.util.LogUtil;
import com.example.mymusic.main.bean.LogoutBean;
import com.example.mymusic.main.mvp.contract.MainContract;
import com.example.mymusic.main.mvp.model.MainModel;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends MainContract.Presenter {
    private static final String TAG = "MainPresenter";

    public MainPresenter(MainContract.View view) {
        this.mView = view;
        this.mModel = new MainModel();
    }

    @Override
    public void logout() {
        mModel.logout().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LogoutBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(LogoutBean logoutBean) {
                        mView.onLogoutSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "error : " + e.toString());
                        mView.onLogoutFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d(TAG, "onComplete!");
                    }
                });
    }

    @Override
    public void getLikeList(long uid) {
        mModel.getLikeList(uid).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LikeListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.d(TAG, "onSubscribe");
                    }

                    @Override
                    public void onNext(LikeListBean bean) {
                        LogUtil.d(TAG, "onNext :" + bean);
                        mView.onGetLikeListSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(TAG, "onError : " + e);
                        mView.onGetLikeListFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.d(TAG, "onComplete");
                    }
                });
    }
}
