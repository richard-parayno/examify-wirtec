package com.wirtec.rparayno.examify.ModeSelectActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.wirtec.rparayno.examify.ClassActivity.ClassActivity;
import com.wirtec.rparayno.examify.ClassActivity.ClassAdapter2;
import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.GameActivity.GameActivity;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;

public class ModeSelectActivity extends AppCompatActivity {
    private TextView className;
    private TextView classNameSub;
    private ImageButton backButton;

    private ArrayList<ClassCard> modeList;
    private RecyclerView recyclerView;
    private ClassAdapter2 cAdapter;

    private DatabaseReference mDatabaseTopics;
    private ArrayList<String> mModeList = new ArrayList<>();

    private String modeSelect = "";
    private Bundle bundle;

    private static final int SELECTED_MODE_CODE = 1;

    private static final String SELECTED_MODE_KEY = "modeName";

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
        setContentView(R.layout.activity_mode_select);
    }

    private void initResources() {
        className = (TextView) findViewById(R.id.userName);
        classNameSub = (TextView) findViewById(R.id.userNameSub);

        backButton = (ImageButton) findViewById(R.id.backButton);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        modeList = new ArrayList<>();
        cAdapter = new ClassAdapter2(modeList, new ViewClickListener() {

            @Override
            public void onViewClick(View v, int position) {
                Intent selectedTopic = new Intent(ModeSelectActivity.this, GameActivity.class);

                Log.d("ButtonPressed:", modeList.get(position).getClassName());
                Log.d("GoingTo:", GameActivity.class.getName());

                Bundle classBundle = new Bundle();
                classBundle.putString(SELECTED_MODE_KEY, modeList.get(position).getClassName());
                selectedTopic.putExtras(classBundle);

                selectedTopic.setFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
                startActivity(selectedTopic);
                finish();
            }
        });

        RecyclerView.LayoutManager cLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(cLayoutManager);
        recyclerView.setAdapter(cAdapter);
    }

    private void prepareDummy() {

        for(int i = 0; i < mModeList.size(); i++) {
            ClassCard classCard = new ClassCard("Time Attack", 1);
            modeList.add(classCard);
        }

        cAdapter.notifyDataSetChanged();
    }
}
