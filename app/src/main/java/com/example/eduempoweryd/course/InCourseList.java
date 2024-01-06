package com.example.eduempoweryd.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.settings.instructor.UserSettingsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InCourseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(InCourseList.this , ListofCoursesActivity.class);
        startActivity(intent);
    }
}
