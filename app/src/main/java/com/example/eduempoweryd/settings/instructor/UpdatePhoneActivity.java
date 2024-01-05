package com.example.eduempoweryd.settings.instructor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdatePhoneActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userEmail;
    private Button buttonConfirm;
    private EditText editPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phone);

        //Initialise
        userName = findViewById(R.id.userName5);
        userEmail = findViewById(R.id.userEmail5);
        buttonConfirm = findViewById(R.id.btnConfirmPhone);
        editPhone = findViewById(R.id.EditPhone);
        // After merging, need to change hardcoded UID to FirebaseAuth.getInstance().getUID();
        String uid = "BlL8dpswezc7ibhUWYjSNx9FeWC3";

        //Show profile data
        String name = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        userName.setText(name);
        userEmail.setText(email);

        //Button handle event
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_phone = editPhone.getText().toString();
                HashMap map = new HashMap();
                map.put("Phone", txt_phone);

                if(!txt_phone.isEmpty()){
                    FirebaseDatabase.getInstance().getReference("Instructors")
                            .child(uid).updateChildren(map);
                    startActivity(new Intent(UpdatePhoneActivity.this, UserSettingsActivity.class));
                    finish();
                }else{
                    finish();
                }
            }
        });
    }
}