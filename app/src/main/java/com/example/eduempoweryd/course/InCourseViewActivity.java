package com.example.eduempoweryd.course;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InCourseViewActivity extends AppCompatActivity {

    private TextView courseTitleTextView, courseDescTextView;
    private ImageView courseImageView;
    private Button editCourse, btnPreviewChapters, btnEditChapters, btnEditFlashcard, btnAddDiscussion, btnEditDiscussion, btnEnrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_in_course_view);

        editCourse = findViewById(R.id.btnEditCourse);
        btnPreviewChapters = findViewById(R.id.btnPreviewChapters);
        btnEditChapters = findViewById(R.id.btnEditChapters);
        btnEditFlashcard = findViewById(R.id.btnEditFlashcard);
        btnAddDiscussion = findViewById(R.id.btnAddDiscussion);
        btnEditDiscussion = findViewById(R.id.btnEditDiscussion);
        btnEnrol = findViewById(R.id.btnEnrol);

        setupButton();

        editCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEditCourse();
            }
        });


        courseTitleTextView = findViewById(R.id.TVCourseTitle);
        courseDescTextView = findViewById(R.id.TVDesc);

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

        courseImageView = findViewById(R.id.ImageCourse);

        // Reference to the Firebase "Image" section where instructor data is stored
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Image");

        // Attach a ValueEventListener to retrieve data from Firebase
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Check if data exists
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        // Retrieve image
                        String imageUrl = snapshot.child("imageUrl").getValue(String.class); // Retrieve image URL

                        // Load the image using Glide or Picasso (you may need to add the corresponding libraries)
                        if (imageUrl != null && !imageUrl.isEmpty()) {
                            Glide.with(InCourseViewActivity.this)
                                    .load(imageUrl)
                                    .into(courseImageView);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void openEditCourse() {
        Intent intent = new Intent(this, InEditCourseActivity.class);
        startActivity(intent);
    }

    private void setupButton(){
        btnPreviewChapters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InCourseViewActivity.this, com.example.eduempoweryd.videoview.MainActivity.class));
            }
        });

        btnEditChapters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InCourseViewActivity.this, com.example.eduempoweryd.videoview.MainActivity.class));
            }
        });

        btnEditFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InCourseViewActivity.this, com.example.eduempoweryd.flashcard.InstructorActivity.class));
            }
        });

        btnAddDiscussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InCourseViewActivity.this, com.example.eduempoweryd.forum.AddComment.class));
            }
        });

        btnEditDiscussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InCourseViewActivity.this, com.example.eduempoweryd.forum.AddComment.class));
            }
        });

        btnEnrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Handle enrolment
                Log.e("Enrol", "Enrol");
            }
        });
    }
    public void back() {
        Intent intent = new Intent(this, ListofCoursesActivity.class);
        startActivity(intent);
    }

}
