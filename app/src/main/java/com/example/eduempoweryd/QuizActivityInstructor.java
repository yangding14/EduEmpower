package com.example.eduempoweryd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class QuizActivityInstructor extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_quiz);
    }

    public void quizBack(View v){
        Log.d("QuizActivityInstructor", "Back button pressed");
        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Check if there are fragments in the back stack
        if (fragmentManager.getBackStackEntryCount() > 0) {
            // Pop the back stack to navigate back to the previous fragment
            fragmentManager.popBackStack();
        } else {
            Log.d("QuizActivityInstructor", "Nothing on back stack");
        }
    }
}
