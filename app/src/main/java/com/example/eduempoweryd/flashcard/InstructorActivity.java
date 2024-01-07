package com.example.eduempoweryd.flashcard;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.widget.Toast;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.course.InCourseViewActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InstructorActivity extends Activity {

    private ArrayList<Flashcard> flashcardsList = new ArrayList<>();
    private FlashcardAdapter adapter;
    private ListView listView;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard_activity_instructor);

        listView = findViewById(R.id.lv_flashcards);
        adapter = new FlashcardAdapter();
        listView.setAdapter(adapter);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("flashcards").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                flashcardsList.clear(); // Clear the old list
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Flashcard flashcard = snapshot.getValue(Flashcard.class);
                    flashcardsList.add(flashcard);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(InstructorActivity.this, "Failed to load flashcards.", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnAddFlashcard = findViewById(R.id.btn_add_flashcard);
        Button btnSaveFlashcards = findViewById(R.id.btn_save_flashcards);

        btnAddFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flashcardsList.add(new Flashcard("", ""));
                adapter.notifyDataSetChanged();
            }
        });

        btnSaveFlashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSaveConfirmation();
            }
        });

        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private class FlashcardAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return flashcardsList.size();
        }

        @Override
        public Flashcard getItem(int position) {
            return flashcardsList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(InstructorActivity.this).inflate(R.layout.flashcard_list_item_flashcard, parent, false);
            }

            EditText etQuestion = convertView.findViewById(R.id.et_question);
            EditText etAnswer = convertView.findViewById(R.id.et_answer);
            ImageButton btnDelete = convertView.findViewById(R.id.btn_delete);

            Flashcard flashcard = getItem(position);
            etQuestion.setText(flashcard.getQuestion());
            etAnswer.setText(flashcard.getAnswer());

            etQuestion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) {
                        flashcard.setQuestion(etQuestion.getText().toString());
                    }
                }
            });

            etAnswer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean hasFocus) {
                    if (!hasFocus) {
                        flashcard.setAnswer(etAnswer.getText().toString());
                    }
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    flashcardsList.remove(position);
                    notifyDataSetChanged();
                }
            });

            return convertView;
        }
    }

    private void saveFlashcards() {
        mDatabase.child("flashcards").setValue(flashcardsList);
        Toast.makeText(this, "Flashcards saved successfully!", Toast.LENGTH_SHORT).show();
    }

    private void showSaveConfirmation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(InstructorActivity.this);
        builder.setTitle("Save Flashcards");
        builder.setMessage("Are you sure you want to save your changes?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                saveFlashcards();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}