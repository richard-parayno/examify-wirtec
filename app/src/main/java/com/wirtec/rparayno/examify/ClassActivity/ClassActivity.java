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
import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.R;
import com.wirtec.rparayno.examify.ViewClickListener;

import java.util.ArrayList;

public class ClassActivity extends AppCompatActivity {
    private TextView className;
    private TextView classNameSub;
    private ImageButton backButton;

    private ArrayList<ClassCard> classList;
    private RecyclerView recyclerView;
    private ClassAdapter2 cAdapter;

    private DatabaseReference mDatabase;
    private ArrayList<String> mClassList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        removeTitleBar();

        initResources();

        intentChecker();

        getClassNames();

        prepareDummy();
    }

    private void getClassNames(){

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Courses").child("Topics");

        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String value = dataSnapshot.getValue(String.class);
                mClassList.add(value);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

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
        classList = new ArrayList<>();
        cAdapter = new ClassAdapter2(classList, new ViewClickListener() {
            @Override
            public void onViewClick(View v, int position) {

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
            ClassCard classCard = new ClassCard(mClassList.get(i), 1);
            classList.add(classCard);
        }

        cAdapter.notifyDataSetChanged();
    }
}
