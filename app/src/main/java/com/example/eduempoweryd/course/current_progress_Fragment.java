package com.example.eduempoweryd.course;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class current_progress_Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<progress_chapterlist> progressChapterlists = new ArrayList<>();
    int [] images= {R.drawable.checked, R.drawable.checked, R.drawable.checked, R.drawable.checked, R.drawable.checked,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    int[] image = {R.drawable.checked, R.drawable.pending};

    public current_progress_Fragment() {
        // Required empty public constructor
    }

    public static current_progress_Fragment newInstance(String param1, String param2) {
        current_progress_Fragment fragment = new current_progress_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.course_fragment_current_progress_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerview = view.findViewById(R.id.Progress_RecycleView);
        progress_chapteradapter progress_chapteradapter = new progress_chapteradapter(this.getContext() ,  progressChapterlists );
        recyclerview.setAdapter(progress_chapteradapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chapters");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressChapterlists.clear(); // Clear existing data before adding new data
                int i = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String position = snapshot.child("position").getValue(String.class);
                    String key = snapshot.getKey();
                    String filetype = snapshot.child("file").getValue(String.class);

                    if(images[i]!=0){
                        progressChapterlists.add(new progress_chapterlist(position, name, image[0] ,key));
                    }else {progressChapterlists.add(new progress_chapterlist(position, name, image[1] ,key ));}

                    i++;

                    Collections.sort(progressChapterlists, new Comparator<progress_chapterlist>() {
                        @Override
                        public int compare(progress_chapterlist chapter1, progress_chapterlist chapter2) {
                            // Parse "position" as integers and compare
                            int position1 = Integer.parseInt(chapter1.getPosition());
                            int position2 = Integer.parseInt(chapter2.getPosition());

                            // Compare the parsed integers
                            return Integer.compare(position1, position2);
                        }
                    });

                }




                progress_chapteradapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });

        ImageButton backbutton = view.findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new HomeFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });
    }

}







