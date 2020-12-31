package com.example.mymusic.login.mvp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.mymusic.login.bean.LoginBean;
import com.example.mymusic.login.mvp.contract.LoginContract;
import com.example.mymusic.login.mvp.presenter.LoginPresenter;
import com.hjq.toast.ToastUtils;
import com.example.mymusic.R;
import com.example.mymusic.base.BaseActivity;
import com.example.mymusic.util.ActivityStarter;
import com.example.mymusic.util.ClickUtil;
import com.example.mymusic.util.InputUtil;
import com.example.mymusic.util.LogUtil;
import com.example.mymusic.util.ScreenUtils;
import com.example.mymusic.util.SharePreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {
    private static final String TAG = "LoginActivity";

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    String phoneNumber;
    String password;

    @Override
    protected void onCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected LoginPresenter onCreatePresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initData() {
        setBackBtn(getString(R.string.colorBlack));
        setLeftTitleText(R.string.login_phone_number);
        if (!TextUtils.isEmpty(SharePreferenceUtil.getInstance(this).getAccountNum())) {
            phoneNumber = SharePreferenceUtil.getInstance(this).getAccountNum();
            etPhone.setText(phoneNumber);
        }
    }

    @Override
    protected void initModule() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        LogUtil.d(TAG, "onResume");
        super.onResume();
        //设置状态栏为白底黑字
        ScreenUtils.setStatusBarColor(this, Color.parseColor("#ffffff"));
        ScreenUtils.setStatusBarDarkFont(this, true);
    }

    @Override
    @OnClick({R.id.btn_login, R.id.register, R.id.forget_pwd})
    public void onClick(View v) {
        if (ClickUtil.isFastClick(1000, v)) {
            return;
        }
//        InputUtil.dismissInputMethod(this);
        switch (v.getId()) {
            case R.id.btn_login:
                phoneNumber = etPhone.getText().toString().trim();
                password = etPwd.getText().toString().trim();
                if (InputUtil.checkMobileLegal(phoneNumber) && InputUtil.checkPasswordLegal(password)) {
                    showDialog();
                    LogUtil.d(TAG, "login : " + phoneNumber + " ," + password);
                    mPresenter.login(phoneNumber, password);
                }
                break;
            case R.id.register:
                ToastUtils.show(R.string.in_developing);
                break;
            case R.id.forget_pwd:
                ToastUtils.show(R.string.in_developing);
                break;
        }
    }

    @Override
    public void onLoginSuccess(LoginBean bean) {
        hideDialog();
        LogUtil.d(TAG, "onLoginSuccess : " + bean);
        SharePreferenceUtil.getInstance(this).saveUserInfo(bean, phoneNumber);
        ActivityStarter.getInstance().startMainActivity(this);
        this.finish();
    }

    @Override
    public void onLoginFail(String error) {
        hideDialog();
        LogUtil.w(TAG, "bean : " + error);
        if (error.equals("HTTP 502 Bad Gateway")) {
            ToastUtils.show(R.string.enter_correct_password);
        } else {
            ToastUtils.show(error);
        }
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }
}
