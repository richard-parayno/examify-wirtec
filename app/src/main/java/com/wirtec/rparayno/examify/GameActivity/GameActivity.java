package com.wirtec.rparayno.examify.GameActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.wirtec.rparayno.examify.R;

public class GameActivity extends AppCompatActivity {

    private TextView qTextView;
    private Button choice1Btn, choice2Btn, choice3Btn, choice4Btn;

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
        setContentView(R.layout.activity_class);
    }

    private void initResources(){

        qTextView = (TextView) findViewById(R.id.qTextView);
        choice1Btn = (Button) findViewById(R.id.choice1Btn);
        choice2Btn = (Button) findViewById(R.id.choice2Btn);
        choice3Btn = (Button) findViewById(R.id.choice3Btn);
        choice4Btn = (Button) findViewById(R.id.choice4Btn);

    }
}
