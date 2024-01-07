package com.example.eduempoweryd.course;

import static androidx.databinding.DataBindingUtil.setContentView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.List;

public class SeeAllCompletedCourses extends AppCompatActivity {
    private ImageView courseImageView;
    private TextView courseTitleTextView;
    private List<Course> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_see_all_complete_course);

        setupView();

//
//        retakeCourse.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openSubject();
//            }
//        });

        ImageButton back = findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackButtonClick();
            }
        });

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
//        // Reference to the Firebase "Image" section where instructor data is stored
//        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Image");
//
//        // Attach a ValueEventListener to retrieve data from Firebase
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                // Check if data exists
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        // Retrieve image
//                        String imageUrl = snapshot.child("imageUrl").getValue(String.class); // Retrieve image URL
//
//                        // Load the image using Glide or Picasso (you may need to add the corresponding libraries)
//                        if (imageUrl != null && !imageUrl.isEmpty()) {
//                            Glide.with(SeeAllCompletedCourses.this)
//                                    .load(imageUrl)
//                                    .into(courseImageView);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        // Reference to the Firebase "Image" section where instructor data is stored
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Course");

        // Attach a ValueEventListener to retrieve data from Firebase
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    courses.add(new Course(dataSnapshot.getKey(), dataSnapshot.child("courseTitle").getValue(String.class), dataSnapshot.child("courseDesc").getValue(String.class), dataSnapshot.child("category").getValue(String.class), dataSnapshot.child("uri").getValue(String.class)));
                    Log.d("StQuizQuestionsList", "Success to get data.");
                    Log.d("StQuizQuestionsList", "Value is: " + dataSnapshot.getValue());
                }

                Log.d("StQuizQuestionsList", "courses size: " + courses.size());
                for (Course course : courses) {
                    LinearLayout linearLayout = findViewById(R.id.completedCourseContainer);
                    View courseView = getLayoutInflater().inflate(R.layout.course_completed_course_single, linearLayout, false);

                    ImageView imageView = courseView.findViewById(R.id.image);
                    // Use Glide to load the image from the URI
                    Glide.with(getApplicationContext())
                            .load(course.getUri())
                            .into(imageView);

                    TextView courseTitleTextView = courseView.findViewById(R.id.title);
                    courseTitleTextView.setText(course.getCourseTitle());

                    TextView courseDescTextView = courseView.findViewById(R.id.description);
                    courseDescTextView.setText(course.getCourseDesc());

                    // Set OnClickListener for each courseView
                    courseView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle the click event for the courseView
                            // For example, you can open a new activity with details of the selected course
                            SharedPreferences sharedPreferences = getSharedPreferences("system", MODE_PRIVATE);
                            sharedPreferences.edit().putString("courseId", course.getCourseId()).apply();

                            Intent intent = new Intent(getApplicationContext(), StCourseViewActivity.class);
                            startActivity(intent);
                        }
                    });

                    linearLayout.addView(courseView);
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

    public void setupView(){


    }
}
