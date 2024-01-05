package com.example.eduempoweryd.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InCourseList extends AppCompatActivity {

    private ImageButton CoursesButton;
    private ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_course_list);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.Course);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Home) {
                    return true;
                } else if (item.getItemId() == R.id.Course) {
                    return true;

                } else if (item.getItemId() == R.id.Account) {
                    startActivity(new Intent(getApplicationContext(), UserSettingsActivity.class));
                    return true;
                } else
                    return false;
            }
        });

        ImageButton course1 = findViewById(R.id.Course1);
        course1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InCourseList.this , ListofCoursesActivity.class);
                startActivity(intent);
            }

            });
    }
}
