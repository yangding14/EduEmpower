package com.example.eduempoweryd.flashcard;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.eduempoweryd.R;
import com.github.mikephil.charting.charts.PieChart;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieData;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentActivity extends Activity {

    private List<Flashcard> flashcardsList;
    private int currentCardIndex = 0;
    private int easyCount = 0;
    private int goodCount = 0;
    private TextView questionTextView;
    private TextView answerTextView;
    private Button easyButton;
    private Button goodButton;
    private Button showAnswerButton;
    private TextView congratsTextView;
    private Button againButton;
    private PieChart pieChart;
    private LinearLayout congratsAndChart;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.flashcard_activity_student);

        questionTextView = findViewById(R.id.tv_question);
        answerTextView = findViewById(R.id.tv_answer);
        easyButton = findViewById(R.id.btn_easy);
        goodButton = findViewById(R.id.btn_good);
        showAnswerButton = findViewById(R.id.btn_show_answer);
        congratsTextView = findViewById(R.id.tv_congrats);
        againButton = findViewById(R.id.btn_again);
        pieChart = findViewById(R.id.pieChart);
        congratsAndChart = findViewById(R.id.congrats_and_chart);
        flashcardsList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("flashcards");

        // Firebase Realtime Database listener
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                flashcardsList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Flashcard flashcard = snapshot.getValue(Flashcard.class);
                    flashcardsList.add(flashcard);
                }
                if (!flashcardsList.isEmpty()) {
                    setupFlashcardView();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Log.e("StudentActivity", "Error fetching data", databaseError.toException());
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerTextView.setVisibility(View.VISIBLE);
                easyButton.setVisibility(View.VISIBLE);
                goodButton.setVisibility(View.VISIBLE);
                showAnswerButton.setVisibility(View.GONE);
            }
        });

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                easyCount++;
                showNextCard();
            }
        });

        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodCount++;
                showNextCard();
            }
        });

        againButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFlashcards();
            }
        });

    }

    private void setupFlashcardView() {
        if (flashcardsList != null && !flashcardsList.isEmpty()) {
            Flashcard currentFlashcard = flashcardsList.get(currentCardIndex);
            questionTextView.setText(currentFlashcard.getQuestion());
            answerTextView.setText(currentFlashcard.getAnswer());

            // Initially hide the answer and buttons until the user asks to show the answer
            answerTextView.setVisibility(View.GONE);
            easyButton.setVisibility(View.GONE);
            goodButton.setVisibility(View.GONE);
            showAnswerButton.setVisibility(View.VISIBLE);
        } else {
            questionTextView.setText("No flashcards available.");
            showAnswerButton.setEnabled(false);
        }
    }

    private void showNextCard() {
        if (flashcardsList != null && !flashcardsList.isEmpty() && currentCardIndex < flashcardsList.size() - 1) {
            currentCardIndex++;
            setupFlashcardView();
        } else {
            questionTextView.setVisibility(View.GONE);
            answerTextView.setVisibility(View.GONE);
            easyButton.setVisibility(View.GONE);
            goodButton.setVisibility(View.GONE);
            showAnswerButton.setVisibility(View.GONE);
            congratsAndChart.setVisibility(View.VISIBLE);
            congratsTextView.setVisibility(View.VISIBLE);
            againButton.setVisibility(View.VISIBLE);
            showStatistics();
        }
    }

    private void resetFlashcards() {
        currentCardIndex = 0;
        easyCount = 0;
        goodCount = 0;

        setupFlashcardView();
        congratsTextView.setVisibility(View.GONE);
        questionTextView.setVisibility(View.VISIBLE);
        showAnswerButton.setVisibility(View.VISIBLE);
        againButton.setVisibility(View.GONE);
        pieChart.setVisibility(View.GONE);
        congratsAndChart.setVisibility(View.GONE);
    }

    private void showStatistics() {
        pieChart.setVisibility(View.VISIBLE);

        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);

        List<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(easyCount, "Easy"));
        entries.add(new PieEntry(goodCount, "Good"));

        PieDataSet set = new PieDataSet(entries, "");
        set.setColors(new int[] {
                ContextCompat.getColor(this, R.color.pie_easy),
                ContextCompat.getColor(this, R.color.pie_good)
        });

        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // Refresh the pie chart

        // Optional: Add animation
        pieChart.animateXY(1400, 1400);
    }
}
