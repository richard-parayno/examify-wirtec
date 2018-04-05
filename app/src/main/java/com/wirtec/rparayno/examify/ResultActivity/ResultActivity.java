package com.wirtec.rparayno.examify.ResultActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.wirtec.rparayno.examify.R;

public class ResultActivity extends AppCompatActivity {
    private TextView currentUserName, currentUserRank, currentUserScore,
                        gameStatus;

    private ProfilePictureView currentUserImage;

    private Button nextActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitleBar();
        initResources();

        Bundle scoreBundle = getIntent().getExtras();

        int x = scoreBundle.getInt("scoreCounter");

        gameStatus.setText("Score: " + x);

    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
    }

    private void initResources() {
        currentUserImage = (ProfilePictureView) findViewById(R.id.currentUserImage);

        currentUserName = (TextView) findViewById(R.id.currentUserName);
        currentUserRank = (TextView) findViewById(R.id.currentUserRank);
        currentUserScore = (TextView) findViewById(R.id.currentUserScore);
        gameStatus = (TextView) findViewById(R.id.gameStatus);

        nextActivity = (Button) findViewById(R.id.nextActivity);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
