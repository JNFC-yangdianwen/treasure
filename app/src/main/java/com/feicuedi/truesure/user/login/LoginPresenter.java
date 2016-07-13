package com.feicuedi.truesure.user.login;

import android.os.AsyncTask;

/**
 * Created by yangdianwen on 16-7-12.
 */
public class LoginPresenter {
    private LoginView loginView;
    //constructor
    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(){
        new LoginTask().execute();

    }
    private final class LoginTask extends AsyncTask<Void,Void,Integer>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loginView.showProgress();
        }

        @Override
        protected Integer doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                loginView.hideProgress();
                loginView.showMessage("未知错误");
               return 0;
            }
            return 1;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (integer == null) {
                loginView.showMessage("未知错误。。。");
            }else {
                loginView.navigateToMain();
            }
        }
    }
}
