package com.example.eduempoweryd.quiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
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

public class StQuizInsideQuizFragment extends Fragment {
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference("quizzes").child("quiz1");
    public static List<Question> questions = new ArrayList<>();
    private QuestionAttempt attempt = new QuestionAttempt();
    private LinearLayout answerList;
    private List<TextView> optionTextViewList = new ArrayList<>();
    TextView questionText, questionNumber, timer;
    private int currentQuestion = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.quiz_fragment_st_quiz_inside_quiz, container, false);
        awaitPopulateQuestion(() -> {
            displayQuestion();
            displayOptions();
        });

        // Inflate the layout for this fragment
        return view;
    }

    public interface OnQuestionsPopulatedListener {
        void onQuestionsPopulated();
    }

    public void awaitPopulateQuestion(OnQuestionsPopulatedListener listener) {
        Log.d("awaitPopulateQuestion", "Populating questions");
        // calling add value event listener method for getting the values from the database.
        db.child("questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                questions.clear(); // Clear existing questions
                Log.d("awaitPopulateQuestion", "Snapshot: " + snapshot.toString());

                for (DataSnapshot questionSnapshot : snapshot.getChildren()) {
                    // Get the values from the snapshot
                    String questionId = questionSnapshot.getKey();
                    String questionText = questionSnapshot.child("question").getValue(String.class);
                    Log.d("awaitPopulateQuestion", "questionSnapshot: " + questionSnapshot.toString());

                    // Get the options, which is a list of strings
                    List<String> options = new ArrayList<>();
                    for (DataSnapshot optionSnapshot : questionSnapshot.child("options").getChildren()) {
                        String option = optionSnapshot.getValue(String.class);
                        options.add(option);
                    }

                    // Create a Question object and add it to the questions list
                    Question question = new Question();
                    question.setQuestionId(questionId);
                    question.setQuestion(questionText);
                    question.setOptions(options);

                    questions.add(question);
                }


                Log.d("awaitPopulateQuestion", "Questions: " + questions.get(0).getQuestion() + " " + questions.get(0).getOptions().toString());

                // Notify the listener that questions are populated
                if (listener != null) {
                    listener.onQuestionsPopulated();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive any error or we are not able to get the data.
                Log.e("StQuizQuestionsList", "Fail to get data.");
            }
        });
    }

    public void displayQuestion() {
        // Get the question
        Question question = questions.get(currentQuestion);
        Log.d("DisplayQuestion", "Question: " + question.toString());
        Log.d("DisplayQuestion", "Question: " + questions.get(0).getQuestion() + " " + questions.get(0).getOptions().toString());

        // Get the timer
        timer = getView().findViewById(R.id.timer);
        // Timer start from 60 and start to countdown
        new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(millisUntilFinished / 1000 + "");
            }

            @Override
            public void onFinish() {
                //TODO: Add code to skip to next question
                timer.setText("Time's up!");
            }
        }.start();


        // Display the question number
        questionNumber = getView().findViewById(R.id.questionNumber);
        questionNumber.setText("Question " + (currentQuestion + 1) + "/" + questions.size());

        // Display the question
        questionText = getView().findViewById(R.id.questionText);
        questionText.setText(question.getQuestion());
    }

    public void displayOptions(){
        // Get the options
        Question question = questions.get(currentQuestion);
        List<String> originalOptions = question.getOptions();

        // Create a copy of the options
        List<String> options = new ArrayList<>(originalOptions);

        // shuffle the options
        java.util.Collections.shuffle(options);

        // Display the options
        answerList = getView().findViewById(R.id.answerList);

        // Clear previous options
        answerList.removeAllViews();
        optionTextViewList.clear();

        Log.d("InQuizAddQuestionFrag", "Options: " + options.toString());

        for (int i = 0; i < options.size(); i++) {
            TextView optionTextView = new TextView(requireContext());

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );

            // Set margins in pixels (convert dp to pixels)
            layoutParams.setMargins(0, 0, 0, dp(10));
            optionTextView.setLayoutParams(layoutParams);

            // Set styling for EditText
            optionTextView.setBackground(getResources().getDrawable(R.drawable.rounded_rectangle_white));
            optionTextView.setPadding(dp(30), dp(18), dp(30), dp(18));
            optionTextView.setText(options.get(i));
            optionTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18); // 18sp
            optionTextView.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.open_sans));
            optionTextView.setTextColor(getResources().getColor(R.color.black));

            optionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("InQuizAddQuestionFrag", "Clicked on option");
                    clickOnAnswer(v);
                }
            });
            answerList.addView(optionTextView);
            optionTextViewList.add(optionTextView);

            Log.d("DisplayOptions", "optionTextViewList: " + optionTextViewList.toString());
            Log.d("DisplayOptions", "answerList: " + answerList.toString());
        }
    }

    public void clickOnAnswer(View v){
        TextView clickedTextView = (TextView) v;

        // Get the question
        Question question = questions.get(currentQuestion);

        // Get the answer
        String answer = clickedTextView.getText().toString();

        // Get the correct answer
        String correctAnswer = question.getOptions().get(0);

        // Check if the answer is correct
        boolean result = answer.equals(correctAnswer);
        Log.d("InQuizAddQuestionFrag", "question: " + question.getQuestion());

        // Add the question, answer and result to the attempt
        attempt.setQuestion(question.getQuestion());
        attempt.setChosenAnswer(answer);
        attempt.setCorrectAnswer(correctAnswer);
        attempt.setResult(result);

        // Store the attempt in the database
        storeAttemptToFirebase(attempt);

        // Check if the current question is the last question
        if (currentQuestion == questions.size() - 1) {
            // End the quiz
            endQuiz();
        } else {
            // Increment the current question
            currentQuestion++;

            // Display the next question
            displayQuestion();
            displayOptions();
        }
    }

    private void endQuiz(){
        switchFragment();
    }

    private int dp(int value){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    public void switchFragment() {
        Bundle bundle = this.getArguments();

        // Pass the attempt key to the next fragment
        StQuizResultFragment nextFrag = new StQuizResultFragment();
        nextFrag.setArguments(bundle);

        // Get the FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Begin the transaction
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentStQuizContainerView, nextFrag)
                .addToBackStack(null)
                .commit();
    }

    private void storeAttemptToFirebase(QuestionAttempt attempt){
        Bundle bundle = this.getArguments();
        String attemptKey = bundle.getString("attemptKey");

        // Use setValue on the specific reference
        db.child("attempts").child(attemptKey).child("user_attempts").push().setValue(attempt)
                .addOnSuccessListener(aVoid -> {
                    Log.d("StQuizInsideQuizFrag", "Attempt stored successfully");
                })
                .addOnFailureListener(e -> {
                    // Show error message
                    Toast.makeText(getActivity(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });

    }

}
