package com.example.mymusic.util;

import android.text.TextUtils;

import com.hjq.toast.ToastUtils;
import com.example.mymusic.R;


/**
 * 输入合法性
 * Created on 2020/7/12
 */
public class InputUtil {
    private static final String TAG = "InputUtil";

    /**
     * 检查手机号码的合法性
     */
    public static boolean checkMobileLegal(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.show(R.string.please_input_phonenumber);
            return false;
        } else if (phoneNumber.length() != 11) {
            ToastUtils.show(R.string.please_input_phonenumber_correctly);
            return false;
        }
        return true;
    }

    /**
     * 检查输入密码的合法性
     */
    public static boolean checkPasswordLegal(String password) {
        if (TextUtils.isEmpty(password)) {
            ToastUtils.show(R.string.please_input_pwd);
            return false;
        } else if (password.length() >= 30) {
            ToastUtils.show(R.string.pwd_must_less_than_30);
            return false;
        }
        return true;
    }

//    /**
//     * 隐藏软键盘
//     */
//    public static void dismissInputMethod(Activity context) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        //isOpen若返回true，则表示输入法打开
//        boolean isOpen = (imm != null) && imm.isActive();
//        LogUtil.d(TAG, "dismissInputMethod : " + isOpen);
//        if (isOpen) {
//            imm.hideSoftInputFromWindow(Objects.requireNonNull(context.getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }
}
