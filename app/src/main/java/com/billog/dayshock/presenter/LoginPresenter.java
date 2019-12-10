package com.billog.dayshock.presenter;

import android.util.Log;

import com.billog.dayshock.model.User;
import com.billog.dayshock.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String email, String password) {
        User user = new User(email, password);
        int loginCode = user.isValidData();

        loginView.onLoginSuccess("444");

        if (loginCode == 0){
            loginView.onLoginError("You must enter your email1111");
        } else if (loginCode == 1){
            loginView.onLoginError("You must enter valid email2222");
        } else if (loginCode == 2){
            loginView.onLoginError("Password length must be greater than 63333");
        } else {
            loginView.onLoginSuccess("Login Success");
        }
    }
}
