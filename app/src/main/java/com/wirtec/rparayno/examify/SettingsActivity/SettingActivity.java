package com.wirtec.rparayno.examify.SettingsActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.wirtec.rparayno.examify.LoginActivity;
import com.wirtec.rparayno.examify.R;

public class SettingActivity extends AppCompatActivity {
    private String[] settingsArray = {"Logout"};
    private ListView listView;
    private ImageButton backButton;
    private Button fbLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        removeTitleBar();
        initResources();

    }

    private void removeTitleBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting);
    }

    private void initResources() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.listview_setting, settingsArray);

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        fbLogoutBtn = (Button) findViewById(R.id.fb_LogoutBtn);
        fbLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                Intent logoutIntent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                finish();
            }
        });


        backButton = (ImageButton) findViewById(R.id.backButton);
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
    }
}
