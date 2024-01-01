package com.example.eduempoweryd;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class InQuizAddQuestionFragment extends Fragment {
    private Button btnAddSelection, btnSaveChangesAddQuestion;
    private EditText newQuestion, correctAnswer, answer1, answer2;
    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbRef = db.getReference("quizzes").child("quiz1").child("questions");
    Question question = new Question();

    private LinearLayout linearAnswersContainer;
    private List<EditText> answerEditTextList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_in_quiz_add_question, container, false);
        newQuestion = view.findViewById(R.id.newQuestion);
        correctAnswer = view.findViewById(R.id.correctAnswer);
        linearAnswersContainer = view.findViewById(R.id.linearAnswersContainer);

        fetchDataFromPrevPage(newQuestion, correctAnswer, linearAnswersContainer);

        btnAddSelection = view.findViewById(R.id.btnAddSelection);
        btnAddSelection.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addSelection();
            }
        });

        btnSaveChangesAddQuestion = view.findViewById(R.id.btnSaveChangesAddQuestion);
        btnSaveChangesAddQuestion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                storeDataToFirebase();
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

    private void addSelection(){
        float scale = getResources().getDisplayMetrics().density;
        EditText answerEditText = new EditText(requireContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Set margins in pixels (convert dp to pixels)
        layoutParams.setMargins(0, 0, 0, dp(10));
        answerEditText.setLayoutParams(layoutParams);

        // Set styling for EditText
        answerEditText.setHint("Answer " + (answerEditTextList.size() + 1));
        answerEditText.setBackground(getResources().getDrawable(R.drawable.rounded_rectangle_white));
        answerEditText.setPadding(dp(30), dp(18), dp(30), dp(18));
        linearAnswersContainer.addView(answerEditText);
        answerEditTextList.add(answerEditText);
    }

    private int dp(int value){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    private void storeDataToFirebase(){
        Log.d("InQuizAddQuestionFrag", "Save Changes button pressed");
        Bundle bundle = this.getArguments();

        if(bundle != null && bundle.getString("questionId") != null){
            storeDataToFirebase_updateData();
        }else{
            storeDataToFirebase_addData();
        }
    }

    private void storeDataToFirebase_updateData(){
        Log.d("InQuizAddQuestionFrag", "Save Changes button pressed - update data");
        Bundle bundle = this.getArguments();

        // Code here executes on main thread after user presses button
        String questionText = newQuestion.getText().toString();
        String correctAnswerText = correctAnswer.getText().toString();

        // Get answers from the EditText views
        List<String> answers = new ArrayList<>();
        for (EditText answerEditText : answerEditTextList) {
            answers.add(answerEditText.getText().toString());
        }

        //check whether the edittext fields are empty
        if (TextUtils.isEmpty(questionText) || TextUtils.isEmpty(correctAnswerText)){
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_LONG).show();
        }else if(answers.size() == 0 || TextUtils.isEmpty(answers.get(0))){
            Toast.makeText(getActivity(), "Please add at least one answer", Toast.LENGTH_LONG).show();
        }else{
            //add the question to the database
            Log.d("InQuizAddQuestionFrag", "Storing data to firebase");

            List<String> options = new ArrayList<>();
            options.add(correctAnswerText);
            options.addAll(answers);

            //set data in our object class
            question.setQuestion(questionText);
            question.setOptions(options);

            // Use setValue on the specific reference
            dbRef.child(bundle.getString("questionId")).setValue(question)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getActivity(), "Question updated successfully", Toast.LENGTH_LONG).show();
                        Log.d("InQuizAddQuestionFrag", "Question updated successfully");
                        switchFragment();
                    })
                    .addOnFailureListener(e -> {
                        // Show error message
                        Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        }
    }

    private void storeDataToFirebase_addData(){
        Log.d("InQuizAddQuestionFrag", "Save Changes button pressed - add new data");

        // Code here executes on main thread after user presses button
        String questionText = newQuestion.getText().toString();
        String correctAnswerText = correctAnswer.getText().toString();

        // Get answers from the EditText views
        List<String> answers = new ArrayList<>();
        for (EditText answerEditText : answerEditTextList) {
            answers.add(answerEditText.getText().toString());
        }

        //check whether the edittext fields are empty
        if (TextUtils.isEmpty(questionText) || TextUtils.isEmpty(correctAnswerText)){
            Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_LONG).show();
        }else if(answers.size() == 0 || TextUtils.isEmpty(answers.get(0))){
            Toast.makeText(getActivity(), "Please add at least one answer", Toast.LENGTH_LONG).show();
        }else{
            //add the question to the database
            Log.d("InQuizAddQuestionFrag", "Storing data to firebase");

            List<String> options = new ArrayList<>();
            options.add(correctAnswerText);
            options.addAll(answers);

            //set data in our object class
            question.setQuestion(questionText);
            question.setOptions(options);

            // Use setValue on the specific reference
            dbRef.push().setValue(question)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(getActivity(), "Question added successfully", Toast.LENGTH_LONG).show();
                        Log.d("InQuizAddQuestionFrag", "Question added successfully");
                        switchFragment();
                    })
                    .addOnFailureListener(e -> {
                        // Show error message
                        Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        }
    }

    public void switchFragment() {
        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.popBackStack();
    }

    public void fetchDataFromPrevPage(EditText newQuestion, EditText correctAnswer, LinearLayout linearAnswersContainer){
        Bundle bundle = this.getArguments();

        if (bundle != null && bundle.getString("questionId") != null) {
            String questionId = bundle.getString("questionId");
            String question = bundle.getString("question");
            ArrayList<String> options = bundle.getStringArrayList("options");

            Log.d("InQuizAddQuestionFrag", "Question ID: " + questionId);
            Log.d("InQuizAddQuestionFrag", "Question: " + question);
            Log.d("InQuizAddQuestionFrag", "Options: " + options.toString());

            newQuestion.setText(question);
            correctAnswer.setText(options.get(0));

            for (int i = 1; i < options.size(); i++) {
                EditText answerEditText = new EditText(requireContext());

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

                // Set margins in pixels (convert dp to pixels)
                layoutParams.setMargins(0, 0, 0, dp(10));
                answerEditText.setLayoutParams(layoutParams);

                // Set styling for EditText
                answerEditText.setHint("Answer " + (answerEditTextList.size() + 1));
                answerEditText.setBackground(getResources().getDrawable(R.drawable.rounded_rectangle_white));
                answerEditText.setPadding(dp(30), dp(18), dp(30), dp(18));
                answerEditText.setText(options.get(i));
                linearAnswersContainer.addView(answerEditText);
                answerEditTextList.add(answerEditText);
            }
        }
    }
}