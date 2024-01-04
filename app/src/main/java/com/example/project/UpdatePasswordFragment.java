package com.example.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UpdatePasswordFragment extends Fragment {

    EditText password;
    TextView userName;
    TextView userEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_update_password, container, false);
        password = view.findViewById(R.id.EVEditConfirmPassword);
        userName = view.findViewById(R.id.userName4);
        userEmail = view.findViewById(R.id.userEmail4);

        // Show profile username and email
        // Later need to change hardcoded UID to FirebaseAuth.getInstance().getUID();
        String uid = "KW9dQocc6mUJtUkssl73sO07oDr2";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String username = String.valueOf(snapshot.child("Username").getValue());
                    String email = String.valueOf(snapshot.child("Email").getValue());
                    userName.setText(username);
                    userEmail.setText(email);
                }
            }
        });

        Button btn = view.findViewById(R.id.btnConfirmPassword);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_password = password.getText().toString();
                if(!txt_password.isEmpty()){
                    updatePassword(uid, txt_password);
                }
                navigateToAccountFragment();
            }
        });

        return view;
    }

    private void updatePassword(String uid, String txtPassword) {
        HashMap map = new HashMap();
        map.put("Password", txtPassword);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Students");
        ref.child(uid).updateChildren(map);
    }

    public void navigateToAccountFragment(){
        Fragment fragment = new AccountFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
        btm.setVisibility(View.VISIBLE);
        ft.replace(R.id.frameLayout, fragment).commit();
    }
}