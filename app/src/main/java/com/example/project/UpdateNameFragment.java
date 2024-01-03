package com.example.project;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateNameFragment extends Fragment {

    EditText newUsername;
    TextView oldUsername;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_name, container, false);
        newUsername = view.findViewById(R.id.EVEditUsername);
        oldUsername = view.findViewById(R.id.userName3);

        Button btn = view.findViewById(R.id.btnConfirmUserame);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_newUsername = newUsername.getText().toString();
                String txt_oldUsername = oldUsername.getText().toString();
                if(!txt_newUsername.isEmpty()){
                    updateUsername(txt_oldUsername, txt_newUsername);
                }
                navigateToAccountFragment();
            }
        });

        return view;
    }

    private void updateUsername(String oldUsername, String newUsername) {
        HashMap map = new HashMap();
        map.put("Username", newUsername);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Students");
        ref.child(oldUsername).updateChildren(map);
        ref.child(oldUsername).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                StudentInfo info = snapshot.getValue(StudentInfo.class);
                HashMap newMap = new HashMap();
                newMap.put("DateOfBirth", info.getDateOfBirth());
                newMap.put("Education", info.getEducation());
                newMap.put("Email", info.getEmail());
                newMap.put("Gender", info.getGender());
                newMap.put("Phone", info.getPhone());
                newMap.put("Password", info.getPassword());
                newMap.put("Username", info.getUsername());

                FirebaseDatabase.getInstance().getReference("Students").child(newUsername).updateChildren(newMap);
                FirebaseDatabase.getInstance().getReference("Students").child(oldUsername).removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error happening", Toast.LENGTH_SHORT).show();
            }
        });

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