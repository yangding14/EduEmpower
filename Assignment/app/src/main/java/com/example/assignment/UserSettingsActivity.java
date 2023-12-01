package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserSettingsActivity extends AppCompatActivity {
    private ImageButton ProfileButton;
    private ImageButton homeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usersettings);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.Account);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Home) {
                    return true;
                } else if (item.getItemId() == R.id.Course) {
                    startActivity(new Intent(getApplicationContext(), InstructorCourseList.class));
                    return true;
                } else if (item.getItemId() == R.id.Account) {
                    startActivity(new Intent(getApplicationContext(), UserSettingsActivity.class));
                    return true;
                } else
                    return false;
            }


        });
    }

    public void open_InstructorCourseList(){
        Intent intent = new Intent(this , InstructorCourseList.class);
        startActivity(intent);
    }
}
