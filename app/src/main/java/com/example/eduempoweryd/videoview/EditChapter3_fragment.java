package com.example.eduempoweryd.videoview;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.eduempoweryd.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditChapter3_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditChapter3_fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<editchapterlist> list = new ArrayList<>();
    EditText ChapterText;
    Button save, chooseVideo;
    VideoView videoView;
    int position;
    MediaController mediaController;

    Uri video;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Chapters");
    ProgressDialog progressDialog;

    public EditChapter3_fragment() {
        // Required empty public constructor
    }

    public EditChapter3_fragment(ArrayList<editchapterlist> editChapterarrayList, int position) {
        this.position = position;
        this.list = editChapterarrayList;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditChapter3_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditChapter3_fragment newInstance(String param1, String param2) {
        EditChapter3_fragment fragment = new EditChapter3_fragment();
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
        return inflater.inflate(R.layout.videoview_fragment_edit_chapter3, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mediaController = new MediaController(view.getContext());

        videoView = view.findViewById(R.id.videoView);
        ChapterText = view.findViewById(R.id.ChaptereditText);

        String ChapterName = list.get(position).getName();
        String File = list.get(position).getFileType();

        videoView.setMediaController(mediaController);
        ChapterText.setText(ChapterName);
        Uri videouri = Uri.parse(File);
        videoView.setVideoURI(videouri);
        videoView.start();


        ImageButton backbutton = view.findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new EditChapterFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });

        Button save_button = view.findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isChapterNameChanged()) {
                    Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No Changes Found", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Button select_button = getView().findViewById(R.id.select_button);
        select_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 101);

                progressDialog = new ProgressDialog(requireContext());

                if (video != null) {
                    // save the selected video in Firebase storage
                    final StorageReference reference = FirebaseStorage.getInstance().getReference("Files/" + System.currentTimeMillis() + "." + getfiletype(video));
                    reference.putFile(video).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!uriTask.isSuccessful()) ;
                            // get the link of video
                            String downloadUri = uriTask.getResult().toString();
                            DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Chapters");
                            databaseReference.child(list.get(position).getKey()).child("file").setValue(downloadUri);
                            // Video uploaded successfully
                            // Dismiss dialog
                            progressDialog.dismiss();
                            Toast.makeText(requireContext(), "Video Uploaded!!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error, Image not uploaded
                            progressDialog.dismiss();
                            Toast.makeText(requireContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        // Progress Listener for loading
                        // percentage on the dialog box
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            // show the progress bar
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
                } else if (video == null) {
                    Toast.makeText(requireContext(), "Please upload video! ", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }


    public void FetchData(){

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear(); // Clear existing data before adding new data
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String position = snapshot.child("position").getValue(String.class);
                    String filetype = snapshot.child("file").getValue(String.class);
                    String key = snapshot.getKey();

                    list.add(new editchapterlist(position, name, filetype, key));
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
            }
        });
    }

    public void onActivityResult(int requestCode , int resultCode , @Nullable Intent data){
        super.onActivityResult(requestCode , resultCode , data);

        if(requestCode==101 && resultCode == RESULT_OK){

            video=data.getData();
            videoView.setVideoURI(video);
            MediaController mediaController1 = new MediaController(requireContext());
            videoView.setMediaController(mediaController1);
            mediaController1.setAnchorView(videoView);
        }
    }

    private String getfiletype(Uri videouri) {
        ContentResolver r = requireContext().getContentResolver();
        // get the file type ,in this case its mp4
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        if(mimeTypeMap.getExtensionFromMimeType(r.getType(videouri)).equals("mp4")) {
            return mimeTypeMap.getExtensionFromMimeType(r.getType(videouri));
        }
        else{return "pdf";}
    }

    private boolean isChapterNameChanged() {
        String ChapterName = list.get(position).getName();
        String File = list.get(position).getFileType();

        if (!ChapterName.equals(ChapterText.getText().toString())){
                databaseReference.child(list.get(position).getKey()).child("name").setValue(ChapterText.getText().toString());
        return true;
        } else {
        return false;
        }
        }


        }


