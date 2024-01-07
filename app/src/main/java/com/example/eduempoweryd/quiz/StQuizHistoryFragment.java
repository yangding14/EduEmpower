package com.example.eduempoweryd.quiz;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StQuizHistoryFragment extends Fragment {
    List<String> date = new ArrayList<>();
    List<String> score = new ArrayList<>();
    List<String> attemptId = new ArrayList<>();
    private Button reattemptButton, editQuestion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quiz_fragment_st_quiz_history, container, false);
        fetchData();
        reattemptButton = view.findViewById(R.id.reattemptButton);
        editQuestion = view.findViewById(R.id.editQuestion);
        setupButton();

        // Inflate the layout for this fragment
        return view;
    }

    private void fetchData(){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("quizzes").child("quiz1").child("attempts");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                date.clear();
                score.clear();
                attemptId.clear();

                // Loop through the data and add to the lists
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    date.add(convertDate(dataSnapshot.child("attemptDate").getValue(String.class)));

                    int tempScore = 0;
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.child("user_attempts").getChildren()){
                        if (dataSnapshot1.child("result").getValue(Boolean.class)){
                            tempScore++;
                        }
                    }
                    score.add(String.valueOf(tempScore) + "/" + String.valueOf(dataSnapshot.child("user_attempts").getChildrenCount()));
                    attemptId.add(dataSnapshot.getKey());
                }

                // Set up view
                setupView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setupView(){
        // Assuming you have a LinearLayout in your XML layout file with the id llQuizHistoryContainer
        LinearLayout quizHistoryContainer = getView().findViewById(R.id.questionList);

        // Loop through the data lists and create views for each quiz history entry
        for (int i = 0; i < date.size(); i++) {
            // Inflate the layout for a single quiz history entry
            View historyEntryView = getLayoutInflater().inflate(R.layout.quiz_history_entry, quizHistoryContainer, false);

            // Find the TextViews in the inflated layout
            TextView attemptDate = historyEntryView.findViewById(R.id.attemptDate);
            TextView attemptScore = historyEntryView.findViewById(R.id.attemptScore);
            TextView attemptNumber = historyEntryView.findViewById(R.id.attemptNumber);

            // Set the data to the TextViews
            attemptDate.setText(date.get(i));
            attemptScore.setText(score.get(i));
            attemptNumber.setText("Attempt " + String.valueOf(i + 1));

            // Set an OnClickListener for the ConstraintLayout
            final int position = i;  // final variable to be used inside onClick
            historyEntryView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the click event for the clicked ConstraintLayout
                    onQuizHistoryEntryClick(position);
                }
            });

            // Add the history entry view to the container
            quizHistoryContainer.addView(historyEntryView);
        }
    }

    // Handle the click event for the clicked ConstraintLayout
    private void onQuizHistoryEntryClick(int position) {
        // Use the position to get relevant data or perform other actions
        String clickedAttemptId = attemptId.get(position);

        // Example: Switching to a new fragment
        switchFragment(clickedAttemptId);
    }

    private void switchFragment(String attemptId){
        // Create new fragment and transaction
        Fragment newFragment = new StQuizResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("attemptKey", attemptId);
        newFragment.setArguments(bundle);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragmentStQuizContainerView, newFragment).addToBackStack(null).commit();
    }

    private String convertDate(String date){
        // convert date from this format: "Mon Jan 01 18:16:27 GMT+08:00 2024" to this format: "01 Jan 2024"
        String[] dateSplit = date.split(" ");
        String day = dateSplit[2];
        String month = dateSplit[1];
        String year = dateSplit[5];

        return day + " " + month + " " + year;
    }

    private void setupButton(){
        SharedPreferences preferences = getActivity().getSharedPreferences("system", MODE_PRIVATE);
        String role = preferences.getString("role", "null");

        if(role.equals("student")){editQuestion.setVisibility(View.GONE);
            reattemptButton.setText("Reattempt Quiz");
        }else if(role.equals("instructor")){
            reattemptButton.setText("Edit Questions");
        }

        reattemptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(role.equals("student")){
                    Fragment newFragment = new StQuizStartQuizFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentStQuizContainerView, newFragment).addToBackStack(null).commit();
                }else if(role.equals("instructor")){

                    Fragment newFragment = new InQuizQuestionsListFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentInQuizContainerView, newFragment).addToBackStack(null).commit();
                }
            }
        });



    }
}
