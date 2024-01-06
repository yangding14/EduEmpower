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

public class UpdateEmailActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userEmail;
    private Button buttonConfirm;
    private EditText editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_in_activity_update_email);

        SharedPreferences preferences = getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");

        //Initialise
        userName = findViewById(R.id.userName2);
        userEmail = findViewById(R.id.userEmail2);
        buttonConfirm = findViewById(R.id.btnConfirmEmail);
        editEmail = findViewById(R.id.EditUsername);

        //Show profile data
        String name = getIntent().getStringExtra("Username");
        String email = getIntent().getStringExtra("Email");
        userEmail.setText(email);
        userName.setText(name);

        //Show email
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Instructors");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String email = String.valueOf(snapshot.child("Email").getValue());
                    editEmail.setText(email);
                }
            }
        });

        //Handle button event
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_email = editEmail.getText().toString();
                HashMap map = new HashMap();
                map.put("Email", txt_email);

                if(!txt_email.isEmpty()){
                    FirebaseDatabase.getInstance().getReference("Instructors")
                            .child(uid).updateChildren(map);
                    startActivity(new Intent(UpdateEmailActivity.this, UserSettingsActivity.class));
                    finish();
                } else{
                    finish();
                }
            }
        });
    }
}