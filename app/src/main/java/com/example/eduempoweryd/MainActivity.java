package com.example.eduempoweryd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // TODO: change back to landing_page.class
        String uid = "5bgKr3QuD6d3guVcHcD2FuNKc8q2";

        SharedPreferences pref = getSharedPreferences("system", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("uid", uid);
        editor.apply();

        // navigate to student
//        Intent intent = new Intent(this, com.example.eduempoweryd.course.MainActivity.class);

        // navigate to instructor
        Intent intent = new Intent(this, com.example.eduempoweryd.course.InCourseList.class);


//        Intent intent = new Intent(MainActivity.this, com.example.eduempoweryd.login.MainActivity.class);
        startActivity(intent);
        finish();
    }
}
