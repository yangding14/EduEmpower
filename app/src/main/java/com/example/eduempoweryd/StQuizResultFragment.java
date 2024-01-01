package com.example.eduempoweryd;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StQuizResultFragment extends Fragment {
    private TextView bigResult, completionsValue, questionsValue, correctValue, wrongValue;
    private Button btnReviewAnswer, btnBackToCourse;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_st_quiz_result, container, false);

        bigResult = view.findViewById(R.id.bigResult);
        completionsValue = view.findViewById(R.id.completionValue);
        questionsValue = view.findViewById(R.id.questionsValue);
        correctValue = view.findViewById(R.id.correctValue);
        wrongValue = view.findViewById(R.id.wrongValue);

        btnReviewAnswer = view.findViewById(R.id.btnReviewAnswer);
        btnBackToCourse = view.findViewById(R.id.btnBackToCourse);

        displayResult();
        assignButtonFunction();

        // Inflate the layout for this fragment
        return view;
    }

    private void displayResult(){
        Bundle bundle = this.getArguments();
        String attemptKey = bundle.getString("attemptKey");

        db.child("quizzes").child("quiz1").child("attempts").child(attemptKey)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int questionSize = (int) snapshot.child("questionSize").getValue(Integer.class);
                int attemptSize = (int) snapshot.child("user_attempts").getChildrenCount();
                int correctSize = 0;

                for (DataSnapshot attemptSnapshot : snapshot.child("user_attempts").getChildren()) {
                    // Get the values from the snapshot
                    Boolean result = attemptSnapshot.child("result").getValue(Boolean.class);
                    if (result) {
                        correctSize++;
                    }
                }

                bigResult.setText(correctSize + "/" + questionSize);
                completionsValue.setText((int)((double)attemptSize/(double)questionSize*100) + "%");
                questionsValue.setText(questionSize + "");
                correctValue.setText(correctSize + "");
                wrongValue.setText((questionSize - correctSize) + "");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive any error or we are not able to get the data.
                Log.e("StQuizResultFragment", "Fail to get data.");
            }
        });
    }

    private void assignButtonFunction(){
        btnReviewAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragmentReviewAnswer();
            }
        });


        btnBackToCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchFragmentBackToCourse();
            }
        });
    }

    private void switchFragmentReviewAnswer() {
        StQuizReviewFragment nextFrag = new StQuizReviewFragment();

        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Begin the transaction
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentStQuizContainerView, nextFrag)
                .addToBackStack(null)
                .commit();
    }

    private void switchFragmentBackToCourse() {

    }

}
