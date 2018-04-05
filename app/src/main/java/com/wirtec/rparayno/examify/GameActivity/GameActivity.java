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
import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    private TextView qTextView;
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

    private String className, topicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        removeTitleBar();

        initResources();

        getQuestionInfo();
    }

    private void getQuestionInfo(){

        mDatabaseCourses = FirebaseDatabase.getInstance().getReference().child("Courses").child("WIR-TEC").child("Topics").child("Music Player").child("Questions");

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
                        String answer = (String) questionSnapshot.child("answer").getValue();
                        answerTexts.add(answer);

                        Log.d("qList:", mQuestionList.toString());

                    }

                qTextView.setText(questionTexts.get(0));
                choice1Btn.setText(choice1Texts.get(0));
                choice2Btn.setText(choice2Texts.get(0));
                choice3Btn.setText(choice3Texts.get(0));
                choice4Btn.setText(choice4Texts.get(0));



                /* Questions question = dataSnapshot.getValue(Questions.class);

                String qText = question.getqText();
                String choice_1 = question.getChoice_1();
                String choice_2 = question.getChoice_2();
                String choice_3 = question.getChoice_3();
                String choice_4 = question.getChoice_4();

                    Log.d("qText:", qText);
                    Log.d("choice_1:", choice_1);
                    Log.d("choice_2:", choice_2);
                    Log.d("choice_3:", choice_3);
                    Log.d("choice_4:", choice_4);
                    Log.d("answer:", question.getAnswer());
                    qTextView.setText(question.getqText());
                    choice1Btn.setText(choice_1);
                    choice2Btn.setText(choice_2);
                    choice3Btn.setText(choice_3);
                    choice4Btn.setText(choice_4);
                    mClassList.add(courseName);
                    Log.d("ClassListSize:", mClassList.toString());
                    ClassCard classCard = new ClassCard(courseName, 1); */
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
