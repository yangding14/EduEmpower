package com.example.eduempoweryd.videoview;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.eduempoweryd.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentVideoFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    DatabaseReference database;
    MediaController mediaController;

    ArrayList<chapterlist> chapterlists = new ArrayList<>();
    int[] images = {R.drawable.play, R.drawable.file, R.drawable.question, };



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.videoview_fragment_video, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView pdfpreview = view.findViewById(R.id.pdfpreview);

        VideoView videoView = view.findViewById(R.id.videoViewIn);
        MediaController mediaController = new MediaController(requireContext());
        videoView.setMediaController(mediaController);

        RecyclerView recyclerview = view.findViewById(R.id.chapter_recycleview);
        chapteradapter chapteradapter = new chapteradapter(this.getContext(), chapterlists, videoView,pdfpreview);
        recyclerview.setAdapter(chapteradapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this.getContext()));


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chapters");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chapterlists.clear(); // Clear existing data before adding new data

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String position = snapshot.child("position").getValue(String.class);
                    String key = snapshot.getKey();
                    String filetype = snapshot.child("file").getValue(String.class);



                    if (!filetype.equals(null) && !filetype.trim().isEmpty()) {
                        if (getfiletype(Uri.parse(filetype)).equals("mp4")) {
                            chapterlists.add(new chapterlist(position, name, images[0], key));
                        } else if (getfiletype(Uri.parse(filetype)).equals("pdf")) {
                            chapterlists.add(new chapterlist(position, name, images[1], key));
                        }
                    } else {
                        chapterlists.add(new chapterlist(position, name, images[2], key));
                    }

                }
                chapteradapter.notifyDataSetChanged();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });

    }

    private String getfiletype(Uri fileuri) {

        List<String> pathSegments = fileuri.getPathSegments();
        // Get the last segment which contains the file name
        String fileName = pathSegments.get(pathSegments.size() - 1);

        // Find the last occurrence of '.' to get the file extension
        int dotIndex = fileName.lastIndexOf('.');

        // Get the substring after the last dot to get the file extension
        String fileExtension = fileName.substring(dotIndex + 1);

        if(fileExtension.equals("mp4")) {
            return "mp4";
        }
        else{return "pdf";}
    }
}