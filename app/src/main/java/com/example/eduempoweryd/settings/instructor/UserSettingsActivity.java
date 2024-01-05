package com.example.eduempoweryd.settings.instructor;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.eduempoweryd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserSettingsActivity extends AppCompatActivity {
    private ImageButton ProfileButton;
    private ImageButton homeButton;
    private Button updateName;
    private Button updateEmail;
    private Button updatePhone;
    private Button updateDOB;
    private Button updateGender;
    private Button updatePassword;
    private Button updateEducation;

    private TextView userName;
    private TextView userEmail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_in_usersettings);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.Account);

        //Initialise buttons
        updateDOB = findViewById(R.id.buttonDOB);
        updateName = findViewById(R.id.buttonUsername);
        updateEducation = findViewById(R.id.buttonEducation);
        updateGender = findViewById(R.id.buttonGender);
        updatePassword = findViewById(R.id.buttonPassword);
        updateEmail = findViewById(R.id.buttonEmail);
        updatePhone = findViewById(R.id.buttonPhone);
        userName = findViewById(R.id.us_username);
        userEmail = findViewById(R.id.us_email);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.Home) {
                    return true;
                } else if (item.getItemId() == R.id.Course) {
                    startActivity(new Intent(getApplicationContext(), InstructorCourseList.class));
                    return true;
                } else if (item.getItemId() == R.id.Account) {
                    startActivity(new Intent(getApplicationContext(), UserSettingsActivity.class));
                    return true;
                } else
                    return false;
            }

        });

        // Show profile username and email
        // Later need to change hardcoded UID to FirebaseAuth.getInstance().getUID();
        String uid = "BlL8dpswezc7ibhUWYjSNx9FeWC3";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Instructors");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String username = String.valueOf(snapshot.child("Username").getValue());
                    String email = String.valueOf(snapshot.child("Email").getValue());
                    userName.setText(username);
                    userEmail.setText(email);
                }
            }
        });

        updatePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSettingsActivity.this, UpdatePhoneActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText()).toString();
                startActivity(i);
            }
        });

        updateEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSettingsActivity.this, UpdateEmailActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText()).toString();
                startActivity(i);
            }
        });

        updatePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSettingsActivity.this, UpdatePasswordActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText()).toString();
                startActivity(i);
            }
        });

        updateGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSettingsActivity.this, UpdateGenderActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText()).toString();
                startActivity(i);
            }
        });

        updateEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSettingsActivity.this, UpdateEducationActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText()).toString();
                startActivity(i);
            }
        });

        updateName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSettingsActivity.this, UpdateUsernameActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText()).toString();
                startActivity(i);
            }
        });

        updateDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UserSettingsActivity.this, UpdateDOBActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText()).toString();
                startActivity(i);
            }
        });
    }

    public void open_InstructorCourseList(){
        Intent intent = new Intent(this , InstructorCourseList.class);
        startActivity(intent);
    }
}
