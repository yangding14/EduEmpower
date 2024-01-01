package com.example.eduempoweryd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class QuizActivityStudent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.st_quiz);
    }

    public void quizBack(View v){
        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Check if there are fragments in the back stack
        if (fragmentManager.getBackStackEntryCount() > 0) {
            // Pop the back stack to navigate back to the previous fragment
            fragmentManager.popBackStack();
        } else {
            Log.d("QuizActivityStudent", "Nothing on back stack");
        }
    }
}
