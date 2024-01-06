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

import android.util.Log;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UploadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UploadFragment extends Fragment {

    MediaController mediaController;
    Uri video;
    Button selectVideo , uploadVideo;
    VideoView videoView ;
    EditText Chapter ;
    ProgressDialog progressDialog;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public UploadFragment() {
    }
    public static UploadFragment newInstance(String param1, String param2) {
        UploadFragment fragment = new UploadFragment();
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
        return inflater.inflate(R.layout.videoview_fragment_upload, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        videoView= view.findViewById(R.id.videoView);
        uploadVideo=view.findViewById(R.id.upload_button);
        selectVideo= view.findViewById(R.id.select_button);
        mediaController=new MediaController(requireContext());
        videoView.setMediaController(mediaController);
        videoView.start();

            selectVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("video/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 101);
                }
            });

        ImageButton backbutton = view.findViewById(R.id.btn_cs_back);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new FileTypeFragment();
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.frameLayout, fragment).commit();
            }
        });



        Button Upload_button = view.findViewById(R.id.upload_button);
        Upload_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Chapter=view.findViewById(R.id.ChaptereditText);
                String chapterText = Chapter.getText().toString();

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
                                UploadData("Chapters" , chapterText , downloadUri);
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
                    }else if (video==null){
                    Toast.makeText(requireContext(), "Please upload video! ", Toast.LENGTH_SHORT).show();
                }


                }

            });
        };




    @Override
    public void onActivityResult(int requestCode , int resultCode , @Nullable Intent data){
        super.onActivityResult(requestCode , resultCode , data);

        if(requestCode==101 && resultCode == RESULT_OK){
            video=data.getData();
            videoView.setVideoURI(video);

            MediaController mediaController1 = new MediaController(requireContext());
            videoView.setMediaController(mediaController1);
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

    public void UploadData(String parentnode , String chapter_data , String Videolink ){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(parentnode);
        int passout ;
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long numberOfKeys = dataSnapshot.getChildrenCount();
                String string_position = String.valueOf(numberOfKeys+1);

                if (string_position.length() == 1){
                    string_position = "0" + string_position;
                }

                // Now, numberOfKeys contains the count of child nodes under "Chapters"
                // We get the String value of numberofKeys
                HashMap<String, String> Videomap = new HashMap<>();
                Videomap.put("file", Videolink);
                Videomap.put("name" , chapter_data);
                Videomap.put("position",string_position);
                chapterlist data = new chapterlist(string_position,chapter_data,Videolink);
//                HashMap<String, Object> dataMap = new HashMap<>();
//                dataMap.put("", data);

                databaseReference.child("Chapter " + String.valueOf(numberOfKeys+1) ).setValue(Videomap);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle the error
                Log.e("Firebase", "Error getting number of keys", databaseError.toException());
            }
        });


    }


}
