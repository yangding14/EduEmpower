package com.example.eduempoweryd.settings.student;

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

public class St_UpdateDOBActivity extends AppCompatActivity {

    private ImageButton backButton;
    private Button returnButton;
    private TextView userName;
    private  TextView userEmail;
    private EditText editDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_update_dobactivity);

        SharedPreferences preferences = getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");

        backButton = findViewById(R.id.DobBackButton);
        returnButton = findViewById(R.id.DobButton);
        userEmail = findViewById(R.id.STEmailDOB);
        userName = findViewById(R.id.STUsernameDOB);
        editDOB = findViewById(R.id.ETUpdateDOB);

        //Update user profile
        updateProfile(userName, userEmail);

        //Retrieve data for ET
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String dob = String.valueOf(snapshot.child("DateOfBirth").getValue());
                    editDOB.setText(dob);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateProfile(TextView userName, TextView userEmail) {
        String name = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        userName.setText(name);
        userEmail.setText(email);
    }
}