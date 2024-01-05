package com.example.eduempoweryd.forum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.eduempoweryd.forum.instructors.InViewForum;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create an Intent to launch thed landing_page activity
        Intent intent = new Intent(this, InViewForum.class);

        // Start the landing_page activity
        startActivity(intent);
    }
}