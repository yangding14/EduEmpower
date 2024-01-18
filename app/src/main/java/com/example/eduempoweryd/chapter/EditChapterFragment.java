package com.example.eduempoweryd.chapter;

import static android.content.Context.MODE_PRIVATE;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.example.eduempoweryd.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EditChapterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<editchapterlist> editchapterlist = new ArrayList<>();
    EditText chapterText;
    VideoView videoView;

    public EditChapterFragment() {
        // Required empty public constructor
    }




    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditChapterFragment.
     */

    // TODO: Rename and change types and number of parameters
    public static EditChapterFragment newInstance(String param1, String param2) {
        EditChapterFragment fragment = new EditChapterFragment();
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
        return inflater.inflate(R.layout.videoview_fragment_editchapter, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerview = view.findViewById(R.id.editchapter_recycleview);
        editchapteradapter editchapteradapter = new editchapteradapter(this.getContext(), editchapterlist , getActivity().getSupportFragmentManager(), this.getActivity().getSharedPreferences("system", MODE_PRIVATE));
        recyclerview.setAdapter(editchapteradapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chapters");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                editchapterlist.clear(); // Clear existing data before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String position = snapshot.child("position").getValue(String.class);
                    String filetype = snapshot.child("file").getValue(String.class);
                    String key = snapshot.getKey();

                    editchapterlist.add(new editchapterlist(position,name,filetype,key));


                }

                Collections.sort(editchapterlist, new Comparator<editchapterlist>() {
                    @Override
                    public int compare(editchapterlist chapter1, editchapterlist chapter2) {
                        // Parse "position" as integers and compare
                        int position1 = Integer.parseInt(chapter1.getPosition());
                        int position2 = Integer.parseInt(chapter2.getPosition());

                        // Compare the parsed integers
                        return Integer.compare(position1, position2);
                    }
                });

                // Notify any adapter about the data change
                editchapteradapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });


        Button button = view.findViewById(R.id.add_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FileTypeFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

        Button button2 = view.findViewById(R.id.edit_button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new editchapter2fragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

        ImageButton backbutton = view.findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new VideoFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

    }




//    private void setUpEditChapterList(){
//        String[] chapterno = getResources().getStringArray(R.array.chapterno);
//        String[] chaptername = getResources().getStringArray(R.array.chaptername);
//        String [] chaptertype = getResources().getStringArray(R.array.chaptertype);
//        for(int i = 0 ; i<chapterno.length; i++){
//            editchapterlist.add(new editchapterlist(chapterno[i],chaptername[i],chaptertype[i]) ) ;
//        }
    }
