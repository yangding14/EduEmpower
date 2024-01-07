package com.example.eduempoweryd.settings.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Build;
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

import java.util.HashMap;

public class St_UpdateUsernameActivity extends AppCompatActivity {

    private TextView userEmail;
    private TextView userName;
    private ImageButton backButton;
    private Button confirmButton;
    private EditText editUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_update_username);
        backButton = findViewById(R.id.UsernameBackButton);
        confirmButton = findViewById(R.id.UsernameButton);
        userEmail = findViewById(R.id.STEmailUsername);
        userName = findViewById(R.id.STUsernameUsername);
        editUsername = findViewById(R.id.ETUpdateUsername);

        SharedPreferences preferences = getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "");

        //Update user profile
        updateProfile(userName, userEmail);

        //Retrieve data for ET
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String username = String.valueOf(snapshot.child("Username").getValue());
                    editUsername.setText(username);
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = editUsername.getText().toString();
                HashMap map = new HashMap();
                map.put("Username", txt_username);

                if(!txt_username.isEmpty()){
                    FirebaseDatabase.getInstance().getReference("Students")
                            .child(uid).updateChildren(map);
//                    Intent i = new Intent(St_UpdateEmailActivity.this, MainActivity.class);
//                    i.putExtra("Navigation", "account_fragment");
//                    startActivity(i);
                    finish();
                } else{
                    finish();
                }
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