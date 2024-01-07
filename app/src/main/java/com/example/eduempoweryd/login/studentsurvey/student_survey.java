package com.example.eduempoweryd.login.studentsurvey;

import static androidx.databinding.adapters.ViewBindingAdapter.setClickListener;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.login.studentregister.student_register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class student_survey extends AppCompatActivity {

    boolean isChecked;
    Button buttonDone;
    List<String> selectedInterests = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr_student_survey);
        buttonDone = findViewById(R.id.btnDone);

        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: ");
                storeInterestsToDatabase(selectedInterests);
            }
        });

        for (int i = 0; i <= 17; i++) {
            int layoutId = getResources().getIdentifier("linearChip" + i, "id", getPackageName());
            LinearLayout clickableLayout = findViewById(layoutId);
            int iconImageViewId = getResources().getIdentifier("imagePlus" + i, "id", getPackageName());
            ImageView iconImageView = findViewById(iconImageViewId);
            int textId = getResources().getIdentifier("txtLabel" + i, "id", getPackageName());
            TextView text = findViewById(textId);



            clickableLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toggle the state
                    isChecked = !isChecked;

                    // Update the icon and background color
                    if (isChecked) {
                        iconImageView.setImageResource(R.drawable.img_checkmark); // Change to your tick sign image
                        clickableLayout.setBackgroundDrawable((getResources().getDrawable(R.drawable.rectangle_bg_cyan_700_border_gray_900_radius_10))); // Change to your desired color
                        text.setTextAppearance(student_survey.this, R.style.txtAssistantromansemibold17_1);

                        selectedInterests.add(text.getText().toString());
                    } else {
                        iconImageView.setImageResource(R.drawable.img_plus); // Change back to your plus sign image
                        clickableLayout.setBackgroundDrawable((getResources().getDrawable(R.drawable.rectangle_bg_gray_50_border_gray_500_radius_10)));
                        text.setTextAppearance(student_survey.this, R.style.txtAssistantromansemibold17);// Change back to your original color

                        selectedInterests.remove(text.getText().toString());
                    }
                }
            });
        }

    }

    private void storeInterestsToDatabase(List<String> interests) {
        SharedPreferences preferences = getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "null");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Students").child(uid).child("interests");

        // Use setValue on the specific reference
        databaseReference.setValue(interests)
                .addOnSuccessListener(aVoid -> {
                    startActivity(new Intent(student_survey.this, com.example.eduempoweryd.course.MainActivity.class));
                })
                .addOnFailureListener(e -> {
                    // Show error message
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

}
