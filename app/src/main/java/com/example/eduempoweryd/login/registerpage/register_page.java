package com.example.mad2.registerpage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mad2.R;
import com.example.mad2.databinding.RegisterPageBinding;
import com.example.mad2.instructorregister.instructor_register;
import com.example.mad2.loginpage.login_page;
import com.example.mad2.studentregister.student_register;

public class register_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RegisterPageBinding binding = DataBindingUtil.setContentView(this, R.layout.register_page);
        RegisterPageVM viewModel = new RegisterPageVM();
        binding.setRegisterPageVM(viewModel);
        binding.setLifecycleOwner(this);

        TextView b1 = findViewById(R.id.txtSignIn);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(register_page.this, login_page.class);
                        startActivity(i);
                    }
                }
        );

        View b2 = findViewById(R.id.viewStud);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(register_page.this, student_register.class);
                        startActivity(i);
                    }
                }
        );

        View b3 = findViewById(R.id.viewIns);
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(register_page.this, instructor_register.class);
                        startActivity(i);
                    }
                }
        );
    }
}
