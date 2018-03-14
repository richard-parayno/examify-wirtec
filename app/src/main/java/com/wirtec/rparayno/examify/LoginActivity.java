package com.wirtec.rparayno.examify;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.Login;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.appevents.AppEventsLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity{

    private EditText username;
    private EditText password;
    private LoginButton fbBtn;
    private final int REQUEST_CODE_INTERNET = 1;
    private CallbackManager callBackManager;
    private ProgressDialog nDialog;

    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        LoginManager.getInstance().logOut();
        // remove title
        removeTitleBar();
        // check login
        boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
        // request permissions
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                showExplanation("Need Permission", "We need access to your storage so I can play your music!");

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_CODE_INTERNET);

            }

        } else {
            // Permission has already been granted
        }
        //initialize resources
        initResources();
        loginWithFacebook();
    }

    private void initResources() {
        username = (EditText) findViewById(R.id.editName);
        password = (EditText) findViewById(R.id.editPassword);
        fbBtn = (LoginButton) findViewById(R.id.fb_loginBtn);

        fbBtn.setReadPermissions(Arrays.asList("public_profile", "email", "user_friends"));

        login = (Button) findViewById(R.id.loginButton);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent classIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(classIntent);
            }
        });

        callBackManager = CallbackManager.Factory.create();
    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
    }

    /** private void getData(JSONObject object){
        try{
            URL profile_picture = new URL("https://graph.facebook.com/" + object.getString("id") +"/picture?width=250&height=250");

            Picasso.with(this).load(profile_picture.toString()).into(imgAvatar);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    } **/

    private void loginWithFacebook(){
        LoginManager.getInstance().registerCallback(callBackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                nDialog = new ProgressDialog(LoginActivity.this);
                nDialog.setMessage("Retrieving Data...");
                nDialog.show();

                String accessToken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Intent fbLoginIntent = new Intent(LoginActivity.this, MainActivity.class);
                        Bundle passToIntent = new Bundle();
                        try {
                            passToIntent.putString("id", object.get("id").toString());
                            passToIntent.putString("first_name", object.get("first_name").toString());
                            passToIntent.putString("link", object.get("link").toString());
                            Log.d("STATUS", "bundle size: " + passToIntent.size());
                            Log.d("STATUS", "id: " + passToIntent.getString("id"));
                            Log.d("STATUS", "name: " + passToIntent.getString("first_name"));
                            Log.d("STATUS", "link: " + passToIntent.getString("link"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        fbLoginIntent.putExtras(passToIntent);
                        startActivity(fbLoginIntent);

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,link");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    private void showExplanation(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(LoginActivity.this,
                                new String[]{Manifest.permission.INTERNET},
                                REQUEST_CODE_INTERNET);
                    }
                });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callBackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
