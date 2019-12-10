package com.billog.dayshock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.billog.dayshock.active.MainHomeActivity;
import com.billog.dayshock.presenter.ILoginPresenter;
import com.billog.dayshock.presenter.LoginPresenter;
import com.billog.dayshock.view.ILoginView;

public class MainActivity extends AppCompatActivity implements ILoginView {

    EditText edt_email, edt_password;
    Button btnLogin;

    ILoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);

        loginPresenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" ============ main acitivyt");
                Log.e("BBBBBBB"," MAIN ACITI");
                loginPresenter.onLogin(edt_email.getText().toString(), edt_password.getText().toString());
            }
        });

    }

    @Override
    public void onLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        // Login Success return page
        // startActivity(new Intent(this, MainHomeActivity.class));
    }

    @Override
    public void onLoginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}