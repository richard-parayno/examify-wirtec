package com.wirtec.rparayno.examify;



import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.wirtec.rparayno.examify.ClassFragment.ClassFragment;
import com.wirtec.rparayno.examify.FeedFragment.FeedFragment;
import com.wirtec.rparayno.examify.ProfileFragment.ProfileFragment;

public class MainActivity extends AppCompatActivity
        implements ClassFragment.OnFragmentInteractionListener,
        FeedFragment.OnFragmentInteractionListener,
        ProfileFragment.OnFragmentInteractionListener {

    private BottomNavigationView navigation;

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
        setContentView(R.layout.activity_main);
    }

    private void initResources() {

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setSelectedItemId(R.id.nav_classes);

        final Bundle oldBundle = getIntent().getExtras();
        Log.d("STATUS", "pre-argument bundle size: " + oldBundle.size());
        Log.d("STATUS", "id: " + oldBundle.getString("id"));
        Log.d("STATUS", "name: " + oldBundle.getString("first_name"));
        Log.d("STATUS", "link: " + oldBundle.getString("link"));


        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_feed:
                        FeedFragment fFragment = new FeedFragment();
                        fFragment.setArguments(oldBundle);
                        loadFragment(fFragment);
                        return true;
                    case R.id.nav_classes:
                        ClassFragment cFragment = new ClassFragment();
                        cFragment.setArguments(oldBundle);
                        loadFragment(cFragment);
                        return true;

                    case R.id.nav_profile:
                        ProfileFragment pFragment = new ProfileFragment();
                        pFragment.setArguments(oldBundle);
                        loadFragment(pFragment);
                        return true;

                }
                return false;
            }
        });

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) navigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());

        ClassFragment cFragment = new ClassFragment();
        cFragment.setArguments(oldBundle);
        loadFragment(cFragment);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
