package com.example.eduempoweryd.quiz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;

import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuizActivityStudent extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_st_quiz);
    }

    public void quizBack(View v){
        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Check if there are fragments in the back stack
        if (fragmentManager.getBackStackEntryCount() > 0) {
            // Pop the back stack to navigate back to the previous fragment
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }
}
