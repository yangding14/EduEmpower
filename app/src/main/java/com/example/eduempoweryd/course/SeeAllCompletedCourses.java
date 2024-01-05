package com.example.eduempoweryd.course;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeeAllCompletedCourses extends AppCompatActivity {
    private ImageView courseImageView;
    private TextView courseTitleTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.see_all_complete_course);

        Button retakeCourse = findViewById(R.id.Retake);

        retakeCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSubject();
            }
        });

        ImageButton back = findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackButtonClick();
            }
        });

        courseTitleTextView = findViewById(R.id.TV1);

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

                        // Update the TextViews in the student view with the retrieved data
                        courseTitleTextView.setText(courseTitle);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        courseImageView = findViewById(R.id.imageView15);

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
                            Glide.with(SeeAllCompletedCourses.this)
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

    public void openSubject() {
        Intent intent = new Intent(this, StCourseViewActivity.class);
        startActivity(intent);
    }

    public void onBackButtonClick() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
