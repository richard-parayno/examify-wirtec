package com.wirtec.rparayno.examify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity{

    private EditText username;
    private EditText password;
    private LoginButton fbBtn;
    private CallbackManager callBackManager;

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        removeTitleBar();
        //initialize resources
        initResources();
        loginWithFacebook();
    }

    private void initResources() {

        callBackManager = CallbackManager.Factory.create();
        username = (EditText) findViewById(R.id.editName);
        password = (EditText) findViewById(R.id.editPassword);
        fbBtn = (LoginButton) findViewById(R.id.fb_loginBtn);

        login = (Button) findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent classIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(classIntent);
            }
        });
    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
    }

    private void loginWithFacebook(){
        LoginManager.getInstance().registerCallback(callBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent fbLoginIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(fbLoginIntent);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callBackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
