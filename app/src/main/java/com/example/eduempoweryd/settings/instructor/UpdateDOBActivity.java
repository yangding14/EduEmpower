package com.example.eduempoweryd.settings.instructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDOBActivity extends AppCompatActivity {

    private Button buttonConfirm;
    private TextView userName;
    private TextView userEmail;
    private EditText editDOB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_dobactivity);

        userEmail = findViewById(R.id.userEmail4);
        userName = findViewById(R.id.userName4);
        editDOB = findViewById(R.id.EditDOB);


        // Set profile data: name and email
        String username = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        userName.setText(username);
        userEmail.setText(email);

        // Show DOB from database
        // Later need to change hardcoded UID to FirebaseAuth.getInstance().getUID();
        String uid = "BlL8dpswezc7ibhUWYjSNx9FeWC3";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Instructors");
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

        //Handle show data from database
        buttonConfirm = findViewById(R.id.btnConfirmDOB);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   finish();
            }
        });
    }
}