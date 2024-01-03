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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateEmailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateEmailFragment extends Fragment {

    TextView userName;
    EditText email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_email, container, false);
        userName = view.findViewById(R.id.userName5);
        email = view.findViewById(R.id.EVEditEmail);

        Button btn = view.findViewById(R.id.btnConfirmEmail);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_username = userName.getText().toString();
                String txt_email = email.getText().toString();
                if(!txt_email.isEmpty()){
                    updateData(txt_username, txt_email);
                }
                navigateToAccountFragment();
            }
        });

        return view;
    }

    private void updateData(String userName, String email) {
        HashMap map = new HashMap();
        map.put("Email", email);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
        reference.child(userName).updateChildren(map);
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