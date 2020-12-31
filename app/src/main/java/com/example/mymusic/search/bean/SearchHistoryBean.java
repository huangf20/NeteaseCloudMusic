package com.example.mymusic.search.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 用于存放历史搜索的GreenDao Bean
 * Created on 2020/8/3
 */
@Entity
public class SearchHistoryBean {

    @Property
    String keyowrds;

    @Generated(hash = 1649854464)
    public SearchHistoryBean(String keyowrds) {
        this.keyowrds = keyowrds;
    }

    @Generated(hash = 1570282321)
    public SearchHistoryBean() {
    }

    public String getKeyowrds() {
        return keyowrds;
    }

    public void setKeyowrds(String keyowrds) {
        this.keyowrds = keyowrds;
    }
}
