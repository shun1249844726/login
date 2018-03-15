package com.example.xushun.mvp_login.presenter;

/**
 * Created by xushun
 * 2018/3/14.
 */

public interface ILoginPresenter {
    void clear();
    void doLogin(String name, String passwd);
    void setProgressBarVisiblity(int visiblity);
}
