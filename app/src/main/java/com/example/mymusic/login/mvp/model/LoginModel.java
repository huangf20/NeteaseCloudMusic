package com.example.mymusic.login.mvp.model;


import com.example.mymusic.api.ApiEngine;
import com.example.mymusic.login.bean.LoginBean;
import com.example.mymusic.login.mvp.contract.LoginContract;

import io.reactivex.Observable;

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<LoginBean> login(String phone, String password) {
        return ApiEngine.getInstance().getApiService().login(phone, password);
    }
}
