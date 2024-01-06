package com.example.eduempoweryd.settings.instructor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.eduempoweryd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdateEducationActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userEmail;
    private EditText editQualification;
    private EditText editInstitute;
    private EditText editMarks;
    private Button buttonConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_in_activity_update_education);

        SharedPreferences preferences = getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");

        //Initialise
        userName = findViewById(R.id.userName8);
        userEmail = findViewById(R.id.userEmail8);
        buttonConfirm = findViewById(R.id.btnConfirmEducation);
        editQualification = findViewById(R.id.EditQualification);
        editInstitute = findViewById(R.id.EditInstitute);
        editMarks = findViewById(R.id.EditMarks);

        //Show profile data
        String name = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        userName.setText(name);
        userEmail.setText(email);

        //Show education details
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Instructors");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String qualification = String.valueOf(snapshot.child("Qualification").getValue());
                    String institute = String.valueOf(snapshot.child("Institute").getValue());
                    String marks = String.valueOf(snapshot.child("Marks").getValue());
                    editQualification.setText(qualification);
                    editInstitute.setText(institute);
                    editMarks.setText(marks);
                }
            }
        });

        //Button handle event
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_qualification = editQualification.getText().toString();
                String txt_institute = editInstitute.getText().toString();
                String txt_marks = editMarks.getText().toString();

                HashMap map = new HashMap();
                map.put("Qualification", txt_qualification);
                map.put("Institute", txt_institute);
                map.put("Marks", txt_marks);

                if(!txt_qualification.isEmpty() || !txt_institute.isEmpty() || !txt_marks.isEmpty()){
                    FirebaseDatabase.getInstance().getReference("Instructors")
                            .child(uid).updateChildren(map);
                    startActivity(new Intent(UpdateEducationActivity.this, UserSettingsActivity.class));
                    finish();
                }else{
                    finish();
                }
            }
        });
    }
}