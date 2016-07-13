package com.feicuedi.truesure.user.login;

/**
 * Created by yangdianwen on 16-7-12.
 */
public interface LoginView {
    //显示进度
    void showProgress();
    //隐藏进度
    void hideProgress();
    //显示信息
    void showMessage(String msg);
    //导航至主界面
    void navigateToMain();
}
