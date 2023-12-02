package com.example.mad2.loginpage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import com.example.mad2.R;
import com.example.mad2.databinding.LoginPageBinding;
import com.example.mad2.instructorregister.instructor_register;
import com.example.mad2.pageone.page_one;
import com.example.mad2.pagetwo.page_two;
import com.example.mad2.registerpage.register_page;
import com.example.mad2.studentregister.student_register;

public class login_page extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoginPageBinding binding = DataBindingUtil.setContentView(this, R.layout.login_page);
        LoginPageVM viewModel = new LoginPageVM();
        binding.setLoginPageVM(viewModel);
        binding.setLifecycleOwner(this);
        Spinner spinner = (Spinner) findViewById(R.id.spinnerstud);
// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.user_types,
                R.layout.spinner_list
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(R.layout.spinner_list);
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

        TextView b1;

        b1 = findViewById(R.id.txtForgotpassword);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // inflate the layout of the popup window
                        LayoutInflater inflater = (LayoutInflater)
                                getSystemService(LAYOUT_INFLATER_SERVICE);
                        View popupView = inflater.inflate(R.layout.forgot_password, null);

                        // create the popup window
                        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                        boolean focusable = true; // lets taps outside the popup also dismiss it
                        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                        // show the popup window
                        // which view you pass in doesn't matter, it is only used for the window tolken
                        popupWindow.setAnimationStyle(R.style.popup_window_animation);
                        popupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);

                        TextView txtSend = popupView.findViewById(R.id.txtSend);
                        txtSend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                popupWindow.dismiss();
                            }
                        });

                    }
                }
        );
        AppCompatButton b2 = findViewById(R.id.btnLogInOne);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence text = "Login successfully!";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(login_page.this, text, duration);
                        toast.show();
                        Intent i = new Intent(login_page.this, login_page.class);
                        startActivity(i);
                    }
                }
        );
        TextView b3;
        b3 = findViewById(R.id.txtSignUp);
        b3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(login_page.this, register_page.class);
                        startActivity(i);
                    }
                }
        );
    }

}
