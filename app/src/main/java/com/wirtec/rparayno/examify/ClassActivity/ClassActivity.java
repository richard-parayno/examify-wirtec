package com.wirtec.rparayno.examify.ClassActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.GameActivity.GameActivity;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {
    private TextView className;
    private TextView classNameSub;
    private ImageButton backButton;

    private ArrayList<ClassCard> topicList;
    private RecyclerView recyclerView;
    private ClassAdapter2 cAdapter;

    private DatabaseReference mDatabaseTopics;
    private ArrayList<String> mClassList = new ArrayList<>();

    private static final int SELECTED_TOPIC_CODE = 1;

    private static final String SELECTED_TOPIC_KEY = "topicName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        removeTitleBar();

        initResources();

        intentChecker();

        getTopicNames();
    }

    private void getTopicNames(){

        mDatabaseTopics = FirebaseDatabase.getInstance().getReference().child("Courses").child("WIR-TEC").child("Topics");

        mDatabaseTopics.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot topicSnapshot : dataSnapshot.getChildren()){
                    Topics mTopic = topicSnapshot.getValue(Topics.class);
                    Log.d("TopicName:", mTopic.getTopicName());
                    mClassList.add(mTopic.getTopicName());
                    Log.d("TopicNameSize:", mTopic.getTopicName());
                    ClassCard topicCard = new ClassCard(mTopic.getTopicName(), 1);
                    topicList.add(topicCard);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_class);
    }

    private void initResources() {
        className = (TextView) findViewById(R.id.userName);
        classNameSub = (TextView) findViewById(R.id.userNameSub);

        backButton = (ImageButton) findViewById(R.id.backButton);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        topicList = new ArrayList<>();
        cAdapter = new ClassAdapter2(topicList, new ViewClickListener() {

            @Override
            public void onViewClick(View v, int position) {
                Intent selectedTopic = new Intent(ClassActivity.this, GameActivity.class);

                Log.d("ButtonPressed:", topicList.get(position).getClassName());
                Log.d("GoingTo:", GameActivity.class.getName());

                Bundle classBundle = new Bundle();
                classBundle.putString(SELECTED_TOPIC_KEY, topicList.get(position).getClassName());
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

    private void intentChecker() {
        Intent classIntent = getIntent();
        if (classIntent.getExtras() != null) {
            Bundle bundle = classIntent.getExtras();
            className.setText(bundle.getString("className"));
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo: finish bundle logic
                    Intent editedIntent = new Intent();
                    Bundle editedBundle = new Bundle();
                    editedIntent.putExtras(editedBundle);
                    Log.d("STATUS", "activity preparation -- going back with content");
                    finish();
                    Log.d("STATUS", "activity finished");
                }
            });
        } else {
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo: finish bundle logic
                    Intent editedIntent = new Intent();
                    Bundle editedBundle = new Bundle();
                    editedIntent.putExtras(editedBundle);
                    Log.d("STATUS", "activity preparation -- going back with no content");
                    finish();
                    Log.d("STATUS", "activity finished");
                }
            });
        }



    }

    private void prepareDummy() {

        for(int i = 0; i < mClassList.size(); i++) {
            ClassCard classCard = new ClassCard("Dummy", 1);
            topicList.add(classCard);
        }

        cAdapter.notifyDataSetChanged();
    }
}
