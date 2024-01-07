package com.example.eduempoweryd.settings.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eduempoweryd.MainActivity;
import com.example.eduempoweryd.R;
import com.example.eduempoweryd.settings.instructor.UpdateEmailActivity;
import com.example.eduempoweryd.settings.instructor.UserSettingsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class St_UpdateEmailActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userEmail;
    private EditText editEmail;
    private ImageView backButton;
    private Button confirmButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_st_update_email);
        backButton = findViewById(R.id.EmailBackButton);
        confirmButton = findViewById(R.id.EmailButton);
        userEmail = findViewById(R.id.STEmailEmail);
        userName = findViewById(R.id.STUsernameEmail);
        editEmail = findViewById(R.id.ETUpdateEmail);

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
                    String email = String.valueOf(snapshot.child("Email").getValue());
                    editEmail.setText(email);
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
                String txt_email = editEmail.getText().toString();
                HashMap map = new HashMap();
                map.put("Email", txt_email);

                if(!txt_email.isEmpty()){
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