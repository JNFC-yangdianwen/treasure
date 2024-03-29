package com.feicuedi.truesure.user.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.feicuedi.truesure.home.HomeActivity;
import com.feicuedi.truesure.R;
import com.feicuedi.truesure.commons.ActivityUtils;
import com.feicuedi.truesure.commons.RegexUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/** 登陆视图*/
public class LoginActivity extends AppCompatActivity implements LoginView{

    @Bind(R.id.et_Password) EditText etPassword;
    @Bind(R.id.et_Username) EditText etUsername;
    @Bind(R.id.btn_Login) Button btnLogin;

    private String userName; // 用来存储用户名
    private String passWord; // 用来存储密码

    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        etPassword.addTextChangedListener(mTextWatcher);
        etUsername.addTextChangedListener(mTextWatcher);
    }

    private final TextWatcher mTextWatcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override public void afterTextChanged(Editable s) {
            userName = etUsername.getText().toString();
            passWord = etPassword.getText().toString();
            boolean canLogin = !(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord));
            // 默认情况下Login按钮是未激活，不可点的
            btnLogin.setEnabled(canLogin);
        }
    };

    @OnClick(R.id.btn_Login)
    public void login() {
        // 用户名是否有效
        if (RegexUtils.verifyUsername(userName) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(R.string.username_rules);
            return;
        }
        // 密码是否有效
        if (RegexUtils.verifyPassword(passWord) != RegexUtils.VERIFY_SUCCESS) {
            activityUtils.showToast(R.string.password_rules);
            return;
        }
        // 执行业务
        //
        new LoginPresenter(this).login();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    private ProgressDialog progressDialog;
    @Override
    public void showProgress() {
        progressDialog=ProgressDialog.show(this,"","正在登陆中，请稍候。。。。");
    }

    @Override
    public void hideProgress() {
            progressDialog.dismiss();
    }

    @Override
    public void showMessage(String msg) {
          activityUtils.showToast(msg);
    }

    @Override
    public void navigateToMain() {
      activityUtils.startActivity(HomeActivity.class);
    }
}