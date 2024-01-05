package com.example.eduempoweryd.login.pagetwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.login.databinding.PageTwoBinding;
import com.example.eduempoweryd.login.loginpage.login_page;
import com.example.eduempoweryd.login.registerpage.register_page;
import com.example.eduempoweryd.login.studentregister.student_register;

public class page_two extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PageTwoBinding binding = DataBindingUtil.setContentView(this, R.layout.lr_page_two);
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
