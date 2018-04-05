package com.wirtec.rparayno.examify.VersusActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.wirtec.rparayno.examify.R;

public class VersusActivity extends AppCompatActivity {
    private TextView currentUserName, currentUserRank,
                        enemyUserName, enemyUserRank;

    private ProfilePictureView currentUserImage, enemyUserImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitleBar();
        initResources();
    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_versus);
    }

    private void initResources() {
        currentUserName = (TextView) findViewById(R.id.currentUserName);
        currentUserRank = (TextView) findViewById(R.id.currentUserRank);
        enemyUserName = (TextView) findViewById(R.id.enemyUserName);
        enemyUserRank = (TextView) findViewById(R.id.enemyUserRank);

        currentUserImage = (ProfilePictureView) findViewById(R.id.currentUserImage);
        enemyUserImage = (ProfilePictureView) findViewById(R.id.enemyUserImage);
    }

    //TODO: write splash screen code for 5 seconds
    private void splash() {

    }


}
