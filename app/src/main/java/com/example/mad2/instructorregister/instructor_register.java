package com.example.mad2.instructorregister;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.mad2.R;
import com.example.mad2.loginpage.login_page;
import com.example.mad2.registerpage.register_page;
import com.example.mad2.studentregister.student_register;
import com.example.mad2.studentsurvey.student_survey;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class instructor_register extends AppCompatActivity {

    private EditText username;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText dob;
    private Spinner gender;
    private EditText qualification;
    private EditText institute;
    private EditText marks;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructor_register);
        EditText SelectDate = findViewById(R.id.SelectDate);

        SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        instructor_register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                SelectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinnergender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender,
                R.layout.spinner_register
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(R.layout.spinner_register);
// Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Change the text color for the first item
                if (position == 0) {
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.GRAY);
                } else {
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        username = findViewById(R.id.usernameinput);
        email = findViewById(R.id.emailinput);
        phone = findViewById(R.id.phoneinput);
        password = findViewById(R.id.pass2input);
        dob = findViewById(R.id.SelectDate);
        gender = findViewById(R.id.spinnergender);
        qualification = findViewById(R.id.qualiinput);
        institute = findViewById(R.id.insinput);
        marks = findViewById(R.id.marksinput);

        AppCompatButton b1 = findViewById(R.id.btnSignUp);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        CharSequence text = "Your account is registered successfully!";
//                        int duration = Toast.LENGTH_SHORT;
//                        Toast toast = Toast.makeText(instructor_register.this, text, duration);
//                        toast.show();
//                        Intent i = new Intent(instructor_register.this, student_survey.class);
//                        startActivity(i);
                        String txt_email = email.getText().toString();
                        String txt_phone = phone.getText().toString();
                        String txt_username = username.getText().toString();
                        String txt_password = password.getText().toString();
                        String txt_dob = dob.getText().toString();
                        String txt_gender = gender.getSelectedItem().toString();
                        String txt_qualification = qualification.getText().toString();
                        String txt_institute = institute.getText().toString();
                        String txt_marks = marks.getText().toString();

                        if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                            Toast.makeText(getApplicationContext(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                        } else if (txt_password.length() < 6){
                            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_SHORT).show();
                        } else{
                            registerUser(txt_username, txt_email, txt_phone, txt_password, txt_dob, txt_gender, txt_qualification, txt_institute, txt_marks);
                        }

                    }
                }
        );
    }

    private void registerUser(String txtUsername, String txtEmail, String txtPhone, String txtPassword, String txtDob, String txtGender, String txtQualification, String txtInstitute, String txtMarks) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(txtEmail, txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    HashMap <String, Object> map = new HashMap<>();
                    map.put("Username", txtUsername);
                    map.put("Email", txtEmail);
                    map.put("Phone", txtPhone);
                    map.put("Password", txtPassword);
                    map.put("DateOfBirth", txtDob);
                    map.put("Gender", txtGender);
                    map.put("Qualification", txtQualification);
                    map.put("Institute", txtInstitute);
                    map.put("Marks", txtMarks);

                    FirebaseDatabase.getInstance().getReference().child("Instructors").child(auth.getUid())
                            .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(getApplicationContext(), "Register successfully", Toast.LENGTH_SHORT).show();
                                        // After merging, need to change student_survey.class to the home page
                                        startActivity(new Intent(instructor_register.this, student_survey.class));
                                    }else {
                                        Toast.makeText(getApplicationContext(), "Something wrong", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });


    }
}
