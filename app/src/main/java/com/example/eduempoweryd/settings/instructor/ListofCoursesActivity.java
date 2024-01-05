package com.example.eduempoweryd.settings.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListofCoursesActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofcourse);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.Course);

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

    public void open_UserSettingsActivty(){
        Intent intent = new Intent(this , UserSettingsActivity.class);
        startActivity(intent);
    }
    public void open_InstructorCourseList(){
        Intent intent = new Intent(this , InstructorCourseList.class);
        startActivity(intent);
    }
}
