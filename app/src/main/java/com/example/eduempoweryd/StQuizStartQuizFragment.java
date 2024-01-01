package com.example.eduempoweryd;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class StQuizStartQuizFragment extends Fragment {
    private Button btnStartQuiz;
    //TODO: Change this to the actual quiz
    DatabaseReference db = FirebaseDatabase.getInstance().getReference("quizzes").child("quiz1");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_st_quiz_start_quiz, container, false);

        btnStartQuiz = view.findViewById(R.id.startQuizButton);
        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String attemptKey = storeAttempt();
                switchFragment(attemptKey);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void switchFragment(String attemptKey) {
        Bundle bundle = new Bundle();
        bundle.putString("attemptKey", attemptKey);

        StQuizInsideQuizFragment nextFrag = new StQuizInsideQuizFragment();
        nextFrag.setArguments(bundle);

        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Begin the transaction
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentStQuizContainerView, nextFrag)
                .addToBackStack(null)
                .commit();
    }

    public String storeAttempt() {
        Attempt attempt = new Attempt();
        DatabaseReference newAttemptRef = db.child("attempts").push(); // Generate a new key for the attempt

        attempt.setAttemptDate(new Date().toString());

        // Listen for the value once to get the number of questions
        db.child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long questionSize = dataSnapshot.getChildrenCount();
                attempt.setQuestionSize((int) questionSize);

                // Set the values under the attempt key
                newAttemptRef.setValue(attempt);

                // Log for testing
                Log.d("StQuizStartQuizFragment", "Attempt stored successfully");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Log.e("StQuizStartQuizFragment", "Error storing attempt: " + databaseError.getMessage());
            }
        });

        return newAttemptRef.getKey();
    }


}
