package com.example.eduempoweryd.chapters;

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

import com.example.eduempoweryd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateNameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateNameFragment extends Fragment {

    EditText newUsername;
    TextView userName;
    TextView userEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.chapter_fragment_update_name, container, false);
        newUsername = view.findViewById(R.id.EVEditUsername);
        userName = view.findViewById(R.id.userName3);
        userEmail = view.findViewById(R.id.userEmail3);

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

        Button btn = view.findViewById(R.id.btnConfirmUserame);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt_newUsername = newUsername.getText().toString();
                if(!txt_newUsername.isEmpty()){
                    updateUsername(uid, txt_newUsername);
                }
                //FirebaseDatabase.getInstance().getReference("Students").child(txt_oldUsername).removeValue();
                navigateToAccountFragment();
            }
        });

        return view;
    }


    private void updateUsername(String uid, String newUsername) {
        HashMap map = new HashMap();
        map.put("Username", newUsername);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Students").child(uid);
        ref.updateChildren(map);

//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    //Log.d("TAG", "Beginning in onDataChange");
//                    //Get existing data
//                    StudentInfo info = snapshot.getValue(StudentInfo.class);
//
//                    // Create a new HashMap with the updated username and other existing data
//                    HashMap newMap = new HashMap();
//                    newMap.put("DateOfBirth", info.getDateOfBirth());
//                    newMap.put("Education", info.getEducation());
//                    newMap.put("Email", info.getEmail());
//                    newMap.put("Gender", info.getGender());
//                    newMap.put("Phone", info.getPhone());
//                    newMap.put("Password", info.getPassword());
//                    newMap.put("Username", info.getUsername());
//
//                    // Update the node with the new data
//                    FirebaseDatabase.getInstance().getReference("Students").child(newUsername).updateChildren(newMap);
//                    //Log.d("TAG", "Ending in onDataChange");
//
//                    // Remove the old node
//                    ref.removeValue();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getContext(), "Error happening", Toast.LENGTH_SHORT).show();
//            }
//        });

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