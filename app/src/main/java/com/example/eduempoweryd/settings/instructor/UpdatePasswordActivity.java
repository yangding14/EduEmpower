package com.example.eduempoweryd.settings.instructor;

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
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdatePasswordActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userEmail;
    private Button buttonConfirm;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_in_activity_update_password);

        SharedPreferences preferences = getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");
        //Initialise
        userName = findViewById(R.id.userName7);
        userEmail = findViewById(R.id.userEmail7);
        buttonConfirm = findViewById(R.id.btnConfirmPassword);
        editPassword = findViewById(R.id.EditPassword2);

        //Show profile data
        String name = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        userName.setText(name);
        userEmail.setText(email);

        //Button handle event
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_password = editPassword.getText().toString();
                HashMap map = new HashMap();
                map.put("Password", txt_password);

                if(!txt_password.isEmpty()){
                    FirebaseDatabase.getInstance().getReference("Instructors")
                            .child(uid).updateChildren(map);
                    startActivity(new Intent(UpdatePasswordActivity.this, UserSettingsActivity.class));
                    finish();
                }else{
                    finish();
                }
            }
        });

        ImageButton backbutton = findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), com.example.eduempoweryd.settings.instructor.UserSettingsActivity.class));
            }
        });

    }
}