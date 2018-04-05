package com.wirtec.rparayno.examify.GameActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.R;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private TextView qTextView;
    private Button choice1Btn, choice2Btn, choice3Btn, choice4Btn;

    private DatabaseReference mDatabaseCourses;
    private ArrayList<String> mClassList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        removeTitleBar();

        initResources();
    }

    private void getQuestionInfo(){

        mDatabaseCourses = FirebaseDatabase.getInstance().getReference().child("Courses").child("WIR-TEC").child("Topics").child("MusicPlayer");

        mDatabaseCourses.addValueEventListener(new ValueEventListener() {
            /** finish logic **/
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot coursesSnapshot : dataSnapshot.getChildren()){
                    String qText = (String) coursesSnapshot.child("qText").getValue();
                    String choice_1 = (String) coursesSnapshot.child("Choice_1").getValue();
                    String choice_2 = (String) coursesSnapshot.child("Choice_2").getValue();
                    String choice_3 = (String) coursesSnapshot.child("Choice_3").getValue();
                    String choice_4 = (String) coursesSnapshot.child("Choice_4").getValue();
                    Log.d("qText:", qText);
                    Log.d("choice_1:", choice_1);
                    Log.d("choice_2:", choice_2);
                    Log.d("choice_3:", choice_3);
                    Log.d("choice_4:", choice_4);
                    /* mClassList.add(courseName);
                    Log.d("ClassListSize:", mClassList.toString());
                    ClassCard classCard = new ClassCard(courseName, 1); */
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

    private void initResources(){

        qTextView = (TextView) findViewById(R.id.qTextView);
        choice1Btn = (Button) findViewById(R.id.choice1Btn);
        choice2Btn = (Button) findViewById(R.id.choice2Btn);
        choice3Btn = (Button) findViewById(R.id.choice3Btn);
        choice4Btn = (Button) findViewById(R.id.choice4Btn);

        setContentView(R.layout.activity_game);
    }
}
