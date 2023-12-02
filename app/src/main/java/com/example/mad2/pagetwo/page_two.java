package com.example.mad2.pagetwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mad2.R;
import com.example.mad2.databinding.PageTwoBinding;
import com.example.mad2.loginpage.login_page;
import com.example.mad2.registerpage.register_page;
import com.example.mad2.studentregister.student_register;

public class page_two extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PageTwoBinding binding = DataBindingUtil.setContentView(this, R.layout.page_two);
        PageTwoVM viewModel = new PageTwoVM();
        binding.setPageTwoVM(viewModel);
        binding.setLifecycleOwner(this);

        AppCompatButton b1 = findViewById(R.id.btnNext2);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(page_two.this, register_page.class);
                        startActivity(i);
                    }
                }
        );

        TextView b2 = findViewById(R.id.txtSkip2);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(page_two.this, register_page.class);
                        startActivity(i);
                    }
                }
        );
    }
}
