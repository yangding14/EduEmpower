package com.example.eduempoweryd.login.pageone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.databinding.LrPageOneBinding;
import com.example.eduempoweryd.databinding.LrPageTwoBinding;
import com.example.eduempoweryd.login.pagetwo.PageTwoVM;
import com.example.eduempoweryd.login.pagetwo.page_two;
import com.example.eduempoweryd.login.registerpage.register_page;

public class page_one extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LrPageOneBinding binding = DataBindingUtil.setContentView(this, R.layout.lr_page_one);
        PageOneVM viewModel = new PageOneVM();
        binding.setPageOneVM(viewModel);
        binding.setLifecycleOwner(this);

        Button b1;

        b1 = findViewById(R.id.btnNext);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(page_one.this, page_two.class);
                        startActivity(i);
                    }
                }
        );

    }

    public void skip(View v){
        Intent i = new Intent(page_one.this, register_page.class);
        startActivity(i);
    }

}
