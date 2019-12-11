package com.billog.dayshock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.billog.dayshock.active.MainHomeActivity;
import com.billog.dayshock.presenter.ILoginPresenter;
import com.billog.dayshock.presenter.LoginPresenter;
import com.billog.dayshock.view.ILoginView;

public class MyMainActivity extends AppCompatActivity implements ILoginView {

    EditText edt_id, edt_pwd;
    Button btn_login;

    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_id = findViewById(R.id.login_id);
        edt_pwd = findViewById(R.id.login_pwd);
        btn_login = findViewById(R.id.btn_login);

        loginPresenter = new LoginPresenter(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLogin(edt_id.getText().toString(), edt_pwd.getText().toString());
            }
        });

    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // Login Success return page
        startActivity(new Intent(this, MainHomeActivity.class));
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
