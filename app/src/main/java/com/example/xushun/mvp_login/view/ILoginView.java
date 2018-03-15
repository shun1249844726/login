package com.example.xushun.mvp_login.view;

/**
 * Created by xushun
 * 2018/3/14.
 */

public interface ILoginView {
     void onClearText();
     void onLoginResult(Boolean result, int code);
     void onSetProgressBarVisibility(int visibility);

}
