package com.example.mymusic.personal.event;

import com.example.mymusic.personal.bean.UserDetailBean;

public class UserDetailEvent {

    public UserDetailEvent(UserDetailBean userDetailBean) {
        this.userDetailBean = userDetailBean;
    }

    UserDetailBean userDetailBean;

    public UserDetailBean getUserDetailBean() {
        return userDetailBean;
    }

    public void setUserDetailBean(UserDetailBean userDetailBean) {
        this.userDetailBean = userDetailBean;
    }
}
