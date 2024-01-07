package com.example.eduempoweryd.course;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StCourseViewActivity extends AppCompatActivity {
    private TextView courseTitleTextView, courseDescTextView;
    private Button btnStartStudy, btnRevision, btnForum;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_st_course_view);

        courseTitleTextView = findViewById(R.id.TVCourseTitle);
        courseDescTextView = findViewById(R.id.TVDesc);

        btnStartStudy = findViewById(R.id.btnStartStudy);
        btnRevision = findViewById(R.id.btnRevision);
        btnForum = findViewById(R.id.btnForum);
        setupButton();


        // Reference to the Firebase "Edit Course" section where instructor data is stored
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Edit Course");

        // Attach a ValueEventListener to retrieve data from Firebase
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if data exists
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Retrieve data for each course
                        String courseTitle = snapshot.child("courseTitle").getValue(String.class);
                        String courseDesc = snapshot.child("courseDesc").getValue(String.class);
                        // Update the TextViews in the student view with the retrieved data
                        courseTitleTextView.setText(courseTitle);
                        courseDescTextView.setText(courseDesc);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void setupButton() {
        btnStartStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StCourseViewActivity.this, com.example.eduempoweryd.videoview.MainActivity.class));
            }
        });

        btnRevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StCourseViewActivity.this, com.example.eduempoweryd.flashcard.StudentActivity.class));
            }
        });

        btnForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StCourseViewActivity.this, com.example.eduempoweryd.forum.AddComment.class));
            }
        });
    }
    public void back() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
