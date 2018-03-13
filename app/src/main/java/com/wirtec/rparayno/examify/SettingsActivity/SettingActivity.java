package com.wirtec.rparayno.examify.SettingsActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wirtec.rparayno.examify.R;

public class SettingActivity extends AppCompatActivity {
    private String[] settingsArray = {"Logout"};
    private ListView listView;

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
    }
}
