package edu.gatech.mkeohane.dash;

import android.app.Activity;
import android.content.Intent;
import android.os.strictmode.NonSdkApiUsedViolation;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.google.firebase.auth.FirebaseAuth;

public class ToDoActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseAuth fire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        fire = FirebaseAuth.getInstance();



        android.support.v7.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if(savedInstanceState == null) {
            Intent startIntent = new Intent(getApplicationContext(), RemindersActivity.class);
            startActivity(startIntent);
            navigationView.setCheckedItem(R.id.nav_schedule);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_schedule:
                Intent startIntent = new Intent(getApplicationContext(), DailyActivity.class);
                startActivity(startIntent);
                break;
            case R.id.nav_mood:
                Intent startIntent2 = new Intent(getApplicationContext(), GoalsActivity.class);
                startActivity(startIntent2);
                break;
            case R.id.nav_notifications:
                Intent startIntent3 = new Intent(getApplicationContext(), RemindersActivity.class);
                startActivity(startIntent3);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    public void onBackPress() {
        if(drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}