package com.example.eduempoweryd;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // if want skip login, uncomment this line and specify role (student or instructor) and comment the intent line below (line 17 and 18)
//        skiplogin("instructor");

        Intent intent = new Intent(MainActivity.this, com.example.eduempoweryd.login.MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void skiplogin(String role){
        if(role.equals("student")){
            String uid = "KW9dQocc6mUJtUkssl73sO07oDr2";

            SharedPreferences pref = getSharedPreferences("system", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("uid", uid);
            editor.putString("role", "student");
            editor.apply();

            Intent intent = new Intent(this, com.example.eduempoweryd.course.MainActivity.class);
            startActivity(intent);
            finish();
        } else if(role.equals("instructor")){
            String uid = "5bgKr3QuD6d3guVcHcD2FuNKc8q2";

            SharedPreferences pref = getSharedPreferences("system", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("uid", uid);
            editor.putString("role", "instructor");
            editor.apply();

            Intent intent = new Intent(this, com.example.eduempoweryd.course.ListofCoursesActivity.class);
            startActivity(intent);
            finish();
        }

    }
}
