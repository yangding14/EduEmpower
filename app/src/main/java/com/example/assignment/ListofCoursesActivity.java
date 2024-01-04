package com.example.assignment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListofCoursesActivity extends AppCompatActivity {

    private Dialog dialog;
    private ImageButton currentImageButtonToDelete;
    private Button ShowDialog;
    private SearchView searchView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listofcourse);

        ImageButton chemistry = findViewById(R.id.ScAndMath1);

        chemistry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChemistry();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.Course);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Home) {
                    return true;
                } else if (item.getItemId() == R.id.Course) {
                    startActivity(new Intent(getApplicationContext(), InCourseList.class));
                    return true;
                } else if (item.getItemId() == R.id.Account) {
                    startActivity(new Intent(getApplicationContext(), UserSettingsActivity.class));
                    return true;
                } else
                    return false;
            }


        });


        // Find your ImageButtons by their IDs
        ImageButton imageButton1 = findViewById(R.id.btndelete1);
        ImageButton imageButton2 = findViewById(R.id.btndelete2);
        ImageButton imageButton3 = findViewById(R.id.btndelete3);
        ImageButton imageButton4 = findViewById(R.id.btndelete4);
        ImageButton imageButton5 = findViewById(R.id.btndelete5);
        ImageButton imageButton6 = findViewById(R.id.btndelete6);

        // Initialize your dialog
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_delete_popup);

        // Assuming the buttons in your dialog have IDs btn_delete and btn_cancel
        Button deleteButton = dialog.findViewById(R.id.btn_delete);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel);

        // Set OnClickListener for each ImageButton to trigger the delete confirmation dialog
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageButtonToDelete = imageButton1;
                showDialogForDeletion();
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageButtonToDelete = imageButton2;
                showDialogForDeletion();
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageButtonToDelete = imageButton3;
                showDialogForDeletion();
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageButtonToDelete = imageButton4;
                showDialogForDeletion();
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageButtonToDelete = imageButton5;
                showDialogForDeletion();
            }
        });

        imageButton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageButtonToDelete = imageButton6;
                showDialogForDeletion();
            }
        });

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
    }

    private void showDialogForDeletion() {
        dialog.show();
    }

    public void openChemistry() {
        Intent intent = new Intent(this, InCourseViewActivity.class);
        startActivity(intent);
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

    public void open_UserSettingsActivty() {
        Intent intent = new Intent(this, UserSettingsActivity.class);
        startActivity(intent);
    }

    public void open_InstructorCourseList() {
        Intent intent = new Intent(this, InCourseList.class);
        startActivity(intent);
    }
}
