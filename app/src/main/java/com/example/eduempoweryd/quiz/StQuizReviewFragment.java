package com.example.eduempoweryd.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StQuizReviewFragment extends Fragment {
    private QuestionReviewAdapter questionReviewAdapter;
    private List<QuestionAttempt> attempts = new ArrayList<>();
    DatabaseReference db = FirebaseDatabase.getInstance().getReference("quizzes");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quiz_fragment_st_quiz_review, container, false);

        // Set up recycler view for questions list
        RecyclerView recyclerView = view.findViewById(R.id.rvReviewQuestionsList);
        // Create an instance of the QuestionReviewAdapter
        questionReviewAdapter = new QuestionReviewAdapter(this.getContext(), attempts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(questionReviewAdapter);

        populateAttempts();

        // Inflate the layout for this fragment
        return view;
    }

    public void populateAttempts(){
        Bundle bundle = this.getArguments();
        String attemptKey = bundle.getString("attemptKey");

        db.child("quiz1").child("attempts").child(attemptKey).child("user_attempts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                attempts.clear(); // Clear existing questions

                for (DataSnapshot questionSnapshot : snapshot.getChildren()) {
                    // Get the values from the snapshot
                    String question = questionSnapshot.child("question").getValue(String.class);
                    String chosenAnswer = questionSnapshot.child("chosenAnswer").getValue(String.class);
                    String correctAnswer = questionSnapshot.child("correctAnswer").getValue(String.class);
                    Boolean result = questionSnapshot.child("result").getValue(Boolean.class);

                    // Create a QuestionAttempt object and add it to the attempts list
                    QuestionAttempt questionAttempt = new QuestionAttempt();
                    questionAttempt.setQuestion(question);
                    questionAttempt.setChosenAnswer(chosenAnswer);
                    questionAttempt.setCorrectAnswer(correctAnswer);
                    questionAttempt.setResult(result);

                    attempts.add(questionAttempt);
                }

                questionReviewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getActivity(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
