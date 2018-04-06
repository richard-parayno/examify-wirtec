package com.wirtec.rparayno.examify.ResultActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wirtec.rparayno.examify.ClassActivity.ClassActivity;
import com.wirtec.rparayno.examify.ClassFragment.ClassFragment;
import com.wirtec.rparayno.examify.MainActivity;
import com.wirtec.rparayno.examify.R;

import java.util.Calendar;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    private TextView currentUserName, currentUserRank, currentUserScore,
                        gameStatus;

    private ProfilePictureView currentUserImage;

    private Button nextActivity;

    private int x;

    private String name, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        removeTitleBar();
        initResources();

        Bundle scoreBundle = getIntent().getExtras();

        name = scoreBundle.getString("first_name");
        x = scoreBundle.getInt("scoreCounter");
        id = scoreBundle.getString("id");

        currentUserName.setText(name);
        currentUserScore.setText("Score: " + x);
        gameStatus.setText("Game Completed!");
        currentUserRank.setText("Royal Inquisitor");

        Date currentTime = Calendar.getInstance().getTime();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mRef = database.getReference().child("Scores").child(name);
        mRef.child("Score").push().setValue(x);
        mRef.child("Score").push().setValue(currentTime);

    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
    }

    private void initResources() {
        currentUserImage = (ProfilePictureView) findViewById(R.id.currentUserImage);
        currentUserImage.setProfileId(id);

        currentUserName = (TextView) findViewById(R.id.currentUserName);
        currentUserRank = (TextView) findViewById(R.id.currentUserRank);
        currentUserScore = (TextView) findViewById(R.id.currentUserScore);
        gameStatus = (TextView) findViewById(R.id.gameStatus);

        nextActivity = (Button) findViewById(R.id.nextActivity);
        nextActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle passBack = new Bundle();
                passBack.putString("first_name", name);
                passBack.putString("link", id);

                Intent homeIntent = new Intent(ResultActivity.this, MainActivity.class);
                homeIntent.putExtras(passBack);
                startActivity(homeIntent);
            }
        });

    }
}
