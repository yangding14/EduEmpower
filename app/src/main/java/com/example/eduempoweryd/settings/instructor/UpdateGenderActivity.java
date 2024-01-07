package com.example.eduempoweryd.settings.instructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eduempoweryd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateGenderActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userEmail;
    private EditText editGender;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_in_activity_update_gender);

        SharedPreferences preferences = getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");

        userName = findViewById(R.id.userName3);
        userEmail = findViewById(R.id.userEmail3);
        editGender = findViewById(R.id.EditGender);
//        buttonConfirm = findViewById(R.id.btnConfirmGender);

        // Show profile data
        String name = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        userEmail.setText(email);
        userName.setText(name);

        // Get data (Gender) from database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Instructors");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String gender = String.valueOf(snapshot.child("Gender").getValue());
                    editGender.setText(gender);
                }
            }
        });

        //Button handling event
//        buttonConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        ImageButton backbutton = findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.eduempoweryd.settings.instructor.UserSettingsActivity.class));
            }
        });

    }


}