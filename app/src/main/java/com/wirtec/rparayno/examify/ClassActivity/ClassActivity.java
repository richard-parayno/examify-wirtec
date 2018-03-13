package com.wirtec.rparayno.examify.ClassActivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wirtec.rparayno.examify.ClassFragment.ClassAdapter;
import com.wirtec.rparayno.examify.ClassFragment.ClassCard;
import com.wirtec.rparayno.examify.ClassFragment.ClassFragment;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        removeTitleBar();

        initResources();

        intentChecker();

        prepareDummy();
    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_class);
    }

    private void initResources() {
        className = (TextView) findViewById(R.id.className);
        classNameSub = (TextView) findViewById(R.id.classNameSub);

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
        Intent prevIntent = getIntent();
        if (prevIntent.getExtras() != null) {
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
        ClassCard classCard = new ClassCard("WIR-TEC", 1);
        classList.add(classCard);

        classCard = new ClassCard("HUMALIT", 1);
        classList.add(classCard);

        classCard = new ClassCard("GREATWK", 1);
        classList.add(classCard);

        classCard = new ClassCard("ITMATH2", 1);
        classList.add(classCard);

        classCard = new ClassCard("DASTAPP", 1);
        classList.add(classCard);

        classCard = new ClassCard("HUMALIT", 1);
        classList.add(classCard);

        classCard = new ClassCard("GREATWK", 1);
        classList.add(classCard);

        classCard = new ClassCard("ITMATH2", 1);
        classList.add(classCard);

        classCard = new ClassCard("DASTAPP", 1);
        classList.add(classCard);

        cAdapter.notifyDataSetChanged();
    }
}
