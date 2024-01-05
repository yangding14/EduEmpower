package com.example.mad2.loginpage;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;
import com.example.mad2.R;
import com.example.mad2.databinding.LoginPageBinding;
import com.example.mad2.registerpage.register_page;
import com.example.mad2.studentsurvey.student_survey;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login_page extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Spinner role;
    FirebaseAuth auth;

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

        email = findViewById(R.id.usernameinput);
        password = findViewById(R.id.etInputFieldT);
        role = findViewById(R.id.spinnerstud);
        auth = FirebaseAuth.getInstance();


        AppCompatButton b2 = findViewById(R.id.btnLogInOne);
        b2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String txt_email = email.getText().toString();
                        String txt_role = spinner.getSelectedItem().toString();
                        String txt_password = password.getText().toString();
                        Log.d("Email", txt_email);
                        Log.d("Password", txt_password);
                        Log.d("Role", txt_role);
                        if(txt_role.equals("Students")){
                            if(!txt_email.isEmpty()){
                                studentLogin(txt_email, txt_password);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                            }
                        } else if (txt_role.equals("Instructors")){
                            if(!txt_email.isEmpty()){
                                instructorLogin(txt_email, txt_password);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                            }
                        } else{
                            Toast.makeText(getApplicationContext(), "Please enter your role", Toast.LENGTH_SHORT).show();
                        }
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

    private void instructorLogin(String email,String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                // After merging, change navigated location to homepage
                startActivity(new Intent(login_page.this, student_survey.class));
                finish();
            }
        });
    }

    private void studentLogin(String email, String password) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(getApplicationContext(), "Login successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login_page.this, student_survey.class));
                finish();
            }
        });
    }


}
