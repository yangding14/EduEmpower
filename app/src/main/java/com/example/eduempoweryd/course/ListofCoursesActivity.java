package com.example.eduempoweryd.course;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.bumptech.glide.Glide;
import com.example.eduempoweryd.quiz.Question;
import com.example.eduempoweryd.settings.instructor.UserSettingsActivity;
import com.example.eduempoweryd.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListofCoursesActivity extends AppCompatActivity {

    private Dialog dialog;
    private ImageButton currentImageButtonToDelete;
    private Button ShowDialog;
    private SearchView searchView;
    List<Course> courses = new ArrayList<>();
    LinearLayout coursesContainer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_listofcourse);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.Course);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Course) {
                    return true;
                } else if (item.getItemId() == R.id.Account) {
                    startActivity(new Intent(getApplicationContext(), UserSettingsActivity.class));
                    return true;
                } else
                    return false;
            }


        });


        // Get the SearchView
        searchView = findViewById(R.id.searchView);

        // Set the search query hint
        searchView.setQueryHint("Search courses");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String query = newText.toLowerCase();
                filterImageButtonsByTag(query);
                return true;
            }
        });

        coursesContainer = findViewById(R.id.coursesContainer);

        renderListOfCourses();
    }

    private void renderListOfCourses(){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Course");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            private String courseId;
            private String courseTitle;
            private String courseDesc;
            private String category;
            private String uri;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    courses.add(new Course(dataSnapshot.getKey(), dataSnapshot.child("courseTitle").getValue(String.class), dataSnapshot.child("courseDesc").getValue(String.class), dataSnapshot.child("category").getValue(String.class), dataSnapshot.child("uri").getValue(String.class)));
                    Log.d("StQuizQuestionsList", "Success to get data.");
                    Log.d("StQuizQuestionsList", "Value is: " + dataSnapshot.getValue());
                }

                Log.d("StQuizQuestionsList", "courses size: " + courses.size());
                for (Course course : courses) {
                    View courseView = getLayoutInflater().inflate(R.layout.course_one_course, coursesContainer, false);

                    ImageView imageView = courseView.findViewById(R.id.image);
                    // Use Glide to load the image from the URI
                    Glide.with(getApplicationContext())
                            .load(course.getUri())
                            .into(imageView);

                    TextView courseTitleTextView = courseView.findViewById(R.id.courseTitle);
                    courseTitleTextView.setText(course.getCourseTitle());

                    // Set OnClickListener for each courseView
                    courseView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Handle the click event for the courseView
                            // For example, you can open a new activity with details of the selected course
                            SharedPreferences sharedPreferences = getSharedPreferences("system", MODE_PRIVATE);
                            sharedPreferences.edit().putString("courseId", course.getCourseId()).apply();

                            Intent intent = new Intent(ListofCoursesActivity.this, InEditCourseActivity.class);
                            intent.putExtra("courseId", course.getCourseId()); // Pass any relevant data
                            startActivity(intent);
                        }
                    });

                    coursesContainer.addView(courseView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive any error or we are not able to get the data.
                Log.e("StQuizQuestionsList", "Fail to get data.");
            }
        });



//        courses.add(new Course("1", "Course 1", "Course 1 Description", "science", null));
//        courses.add(new Course("2", "Course 2", "Course 2 Description", "math", null));
//
//        for (Course course : courses) {
//            View courseView = getLayoutInflater().inflate(R.layout.course_one_course, coursesContainer, false);

//            ImageView imageView = courseView.findViewById(R.id.IVCI);
//
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    openGalleryForImageView(imageView);
//                }
//            });

//            coursesContainer.addView(courseView);
//        }
    }



    private Uri selectedImageUri = null;
    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView selectedImageView;

    private void setupDeleteDialog(){
        // Initialize your dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.course_custom_delete_popup);

        // Assuming the buttons in your dialog have IDs btn_delete and btn_cancel
        Button deleteButton = dialog.findViewById(R.id.btn_delete);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentImageButtonToDelete != null) {
                    ViewGroup parent = (ViewGroup) currentImageButtonToDelete.getParent();
                    if (parent != null) {
                        parent.removeView(currentImageButtonToDelete);
                        Toast.makeText(ListofCoursesActivity.this, "Course Deleted", Toast.LENGTH_SHORT).show();

                        // Find the ID of the associated ScAndMath ImageButton dynamically based on the btndelete clicked
                        String btnDeleteId = getResources().getResourceEntryName(currentImageButtonToDelete.getId());
                        String associatedScAndMathButtonId = "ScAndMath" + btnDeleteId.substring(btnDeleteId.length() - 1);
                        int resID = getResources().getIdentifier(associatedScAndMathButtonId, "id", getPackageName());

                        ImageButton associatedScAndMathButton = findViewById(resID);
                        if (associatedScAndMathButton != null) {
                            associatedScAndMathButton.setVisibility(View.GONE); // or remove it from its parent


                        }
                    }
                }
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListofCoursesActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void showDialogForDeletion() {
        dialog.show();
    }

    private void filterImageButtonsByTag(String query) {
        for (int i = 1; i <= 6; i++) {
            ImageButton imageButton = findViewById(getResources().getIdentifier("ScAndMath" + i, "id", getPackageName()));
            if (imageButton != null) {
                String tag = imageButton.getTag().toString().toLowerCase();
                if (tag.contains(query)) {
                    imageButton.setVisibility(View.VISIBLE); // Show the ImageButton if the tag contains the query
                } else {
                    imageButton.setVisibility(View.GONE); // Hide the ImageButton if the tag doesn't match the query
                }
            }
        }
    }

    public void addNewCourse(View view) {

        startActivity(new Intent(getApplicationContext(), InEditCourseActivity.class));

//        LinearLayout linearLayout = findViewById(R.id.coursesContainer);
//
//        LayoutInflater inflater = LayoutInflater.from(this);
//
//        View courseView = inflater.inflate(R.layout.course_add_course, null);
//
//        ImageView imageView = courseView.findViewById(R.id.IVCI);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGalleryForImageView(imageView);
//            }
//        });
//
//        linearLayout.addView(courseView);
    }

    private void openGalleryForImageView(ImageView imageView) {
        selectedImageView = imageView;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                if (selectedImageView != null) {
                    selectedImageView.setImageBitmap(bitmap);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onSaveChangesClicked(View view) {
//        // Handle the "Save Changes" button click
//        EditText editText = findViewById(R.id.editTextCourseName);
//        editTextContent = editText.getText().toString();
//
//        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        if (selectedImageUri != null) {
//            String imageUriString = selectedImageUri.toString();
//            editor.putString("courseText", editTextContent);
//            editor.putString("courseImageUri", imageUriString);
//            editor.apply();
//        }
    }

}
