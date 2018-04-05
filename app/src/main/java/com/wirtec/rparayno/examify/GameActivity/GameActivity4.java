package com.wirtec.rparayno.examify.GameActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameActivity4 extends AppCompatActivity {
    private TextView qTextView, timeLeft, currentUserScore, enemyUserScore;
    private Button choice1Btn, choice2Btn, choice3Btn, choice4Btn, answer;

    private DatabaseReference mDatabaseCourses;
    private ArrayList<Questions> mQuestionList = new ArrayList<>();

    //QuestionArrayLists
    private ArrayList<String> questionTexts = new ArrayList<>();
    private ArrayList<String> choice1Texts = new ArrayList<>();
    private ArrayList<String> choice2Texts = new ArrayList<>();
    private ArrayList<String> choice3Texts = new ArrayList<>();
    private ArrayList<String> choice4Texts = new ArrayList<>();
    private ArrayList<String> answerTexts = new ArrayList<>();

    //scoreCounter
    private int scoreCounter = 0;

    private String className, topicName;

    private String qAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        removeTitleBar();

        initResources();

        getQuestionInfo();

        Bundle scoreBundle = getIntent().getExtras();

        int x = scoreBundle.getInt("scoreCounter");

        scoreCounter = x;

        currentUserScore.setText("Score: " + scoreCounter);

        choice1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice1Btn.getText().toString().equals(qAnswer)){
                    scoreCounter += 10;
                    Bundle scoreBundle = new Bundle();
                    scoreBundle.putInt("scoreCounter", scoreCounter);
                    Intent game4 = new Intent(GameActivity4.this, GameActivity5.class);
                    game4.putExtras(scoreBundle);
                    startActivity(game4);
                }
                else{
                    Bundle scoreBundle = new Bundle();
                    scoreBundle.putInt("scoreCounter", scoreCounter);
                    Intent game4 = new Intent(GameActivity4.this, GameActivity5.class);
                    game4.putExtras(scoreBundle);
                    startActivity(game4);
                }
            }
        });

        choice2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice2Btn.getText().toString().equals(qAnswer)){
                    scoreCounter += 10;
                    Bundle scoreBundle = new Bundle();
                    scoreBundle.putInt("scoreCounter", scoreCounter);
                    Intent game4 = new Intent(GameActivity4.this, GameActivity5.class);
                    game4.putExtras(scoreBundle);
                    startActivity(game4);
                }
                else{
                    Bundle scoreBundle = new Bundle();
                    scoreBundle.putInt("scoreCounter", scoreCounter);
                    Intent game4 = new Intent(GameActivity4.this, GameActivity5.class);
                    game4.putExtras(scoreBundle);
                    startActivity(game4);
                }
            }
        });

        choice3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice3Btn.getText().toString().equals(qAnswer)){
                    scoreCounter += 10;
                    Bundle scoreBundle = new Bundle();
                    scoreBundle.putInt("scoreCounter", scoreCounter);
                    Intent game4 = new Intent(GameActivity4.this, GameActivity5.class);
                    game4.putExtras(scoreBundle);
                    startActivity(game4);
                }
                else{
                    Bundle scoreBundle = new Bundle();
                    scoreBundle.putInt("scoreCounter", scoreCounter);
                    Intent game4 = new Intent(GameActivity4.this, GameActivity5.class);
                    game4.putExtras(scoreBundle);
                    startActivity(game4);
                }
            }
        });

        choice4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice4Btn.getText().toString().equals(qAnswer)){
                    scoreCounter += 10;
                    Bundle scoreBundle = new Bundle();
                    scoreBundle.putInt("scoreCounter", scoreCounter);
                    Intent game4 = new Intent(GameActivity4.this, GameActivity5.class);
                    game4.putExtras(scoreBundle);
                    startActivity(game4);
                }
                else{
                    Bundle scoreBundle = new Bundle();
                    scoreBundle.putInt("scoreCounter", scoreCounter);
                    Intent game4 = new Intent(GameActivity4.this, GameActivity5.class);
                    game4.putExtras(scoreBundle);
                    startActivity(game4);
                }
            }
        });

    }

    private void getQuestionInfo(){

        mDatabaseCourses = FirebaseDatabase.getInstance().getReference().child("Courses").child("GREATWK").child("Topics").child("V for Vendetta").child("Questions");

        mDatabaseCourses.addValueEventListener(new ValueEventListener() {
            /** finish logic **/
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot questionSnapshot : dataSnapshot.getChildren()){
                    String qText = (String) questionSnapshot.child("qText").getValue();
                    questionTexts.add(qText);
                    String choice_1 = (String) questionSnapshot.child("Choice_1").getValue();
                    choice1Texts.add(choice_1);
                    String choice_2 = (String) questionSnapshot.child("Choice_2").getValue();
                    choice2Texts.add(choice_2);
                    String choice_3 = (String) questionSnapshot.child("Choice_3").getValue();
                    choice3Texts.add(choice_3);
                    String choice_4 = (String) questionSnapshot.child("Choice_4").getValue();
                    choice4Texts.add(choice_4);
                    String answer = (String) questionSnapshot.child("Answer").getValue();
                    answerTexts.add(answer);

                    Log.d("qList:", mQuestionList.toString());

                }

                qTextView.setText(questionTexts.get(3));
                choice1Btn.setText(choice1Texts.get(3));
                choice2Btn.setText(choice2Texts.get(3));
                choice3Btn.setText(choice3Texts.get(3));
                choice4Btn.setText(choice4Texts.get(3));
                qAnswer = answerTexts.get(3);

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

        setContentView(R.layout.activity_game);

        timeLeft = (TextView) findViewById(R.id.timeLeft);
        currentUserScore = (TextView) findViewById(R.id.currentUserScore);
        enemyUserScore = (TextView) findViewById(R.id.enemyUserScore);

        qTextView = (TextView) findViewById(R.id.qTextView);
        choice1Btn = (Button) findViewById(R.id.choice1Btn);
        choice2Btn = (Button) findViewById(R.id.choice2Btn);
        choice3Btn = (Button) findViewById(R.id.choice3Btn);
        choice4Btn = (Button) findViewById(R.id.choice4Btn);

        Bundle gBundle = getIntent().getExtras();

        className = gBundle.getString("className");
        topicName = gBundle.getString("topicName");

        /* Log.d("className:", className);
        Log.d("topicName:", topicName); */


    }


}
